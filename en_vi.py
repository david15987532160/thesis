import os
import sys
import getopt
import argparse
import urllib.parse
import tensorflow as tf
import numpy as np

from tensor2tensor import models
from tensor2tensor import problems
from tensor2tensor.layers import common_layers
from tensor2tensor.utils import trainer_lib
from tensor2tensor.utils import t2t_model
from tensor2tensor.utils import registry
from tensor2tensor.utils import metrics
    

PROBLEM = "translate_envi_iwslt32k"
MODEL = "transformer" 
HPARAMS = "transformer_base"

# INSERT YOUR OWN DIRECTORY IN THE QUOTATION
DATA_DIR = os.path.expanduser("./data")
TRAIN_DIR = os.path.expanduser("./train")

tf.gfile.MakeDirs(DATA_DIR)
tf.gfile.MakeDirs(TRAIN_DIR)


#After training the model, re-run the environment but run this code in first, then predict.
tfe = tf.contrib.eager
tfe.enable_eager_execution()
Modes = tf.estimator.ModeKeys

envi_problem = problems.problem(PROBLEM)

# Copy the vocab file locally so we can encode inputs and decode model outputs
vocab_name = "vocab.translate_envi_iwslt32k.32768.subwords"
vocab_file = os.path.join(DATA_DIR, vocab_name)

# Get the encoders from the problem
encoders = envi_problem.feature_encoders(DATA_DIR)

ckpt_path = tf.train.latest_checkpoint(os.path.join(TRAIN_DIR))
# print(ckpt_path)

def main(argv):
    try:
      opts, args = getopt.getopt(argv,"hs:",["help", "source="])

      if len(opts) == 0:
        print("test.py -s <source_sentence>")

        sys.exit()

      for opt, arg in opts:
        if opt in ("-h", "--help"):
            print("test.py -s <source_sentence>")

            sys.exit()
        elif opt in ("-s", "--source"):
            source = urllib.parse.unquote(arg)

            if source != "":
            #     print("chúng tôi đã từng nghèo".capitalize())
            # else: print("Unknown...")
                def translate(inputs):
                    encoded_inputs = encode(inputs)
                    with tfe.restore_variables_on_create(ckpt_path):
                        model_output = translate_model.infer(encoded_inputs)["outputs"]
                    return decode(model_output)

                def encode(input_str, output_str=None):
                    """Input str to features dict, ready for inference"""
                    inputs = encoders["inputs"].encode(input_str) + [1]  # add EOS id
                    batch_inputs = tf.reshape(inputs, [1, -1, 1])  # Make it 3D.
                    return {"inputs": batch_inputs}

                def decode(integers):
                    """List of ints to str"""
                    integers = list(np.squeeze(integers))
                    if 1 in integers:
                        integers = integers[:integers.index(1)]
                    return encoders["inputs"].decode(np.squeeze(integers))

                #Predict 
                hparams = trainer_lib.create_hparams(HPARAMS, data_dir=DATA_DIR, problem_name=PROBLEM )
                translate_model = registry.model(MODEL)(hparams, Modes.PREDICT)

                inputs = source
                # ref = "Tôi là người Việt Nam" ## this just a reference for evaluate the quality of the traduction
                outputs = translate(inputs)

                # print("Inputs: %s" % inputs)
                # print("Outputs: %s" % outputs)
                print(outputs.capitalize())

                file_input = open("translation/outputs.vi","a")
                file_input.write("\n" + outputs.capitalize())
                file_input.close()

                # file_output = open("reference.vi","w+")
                # file_output.write(ref)
                # file_output.close()
        else:
            print("test.py -s <source_sentence>!!!!")

            sys.exit()
    except getopt.GetoptError as err:
        print(err)
        
        sys.exit(2)


if __name__ == "__main__":
   main(sys.argv[1:])