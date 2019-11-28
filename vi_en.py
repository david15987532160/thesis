from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
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
from tensor2tensor.data_generators import problem
from tensor2tensor.data_generators import text_encoder
from tensor2tensor.data_generators import translate

# For English-Vietnamese the IWSLT'15 corpus
# from https://nlp.stanford.edu/projects/nmt/ is used.
# The original dataset has 133K parallel sentences.
_ENVI_TRAIN_DATASETS = [[
    "https://github.com/stefan-it/nmt-en-vi/raw/master/data/train-en-vi.tgz",  # pylint: disable=line-too-long
    ("train.vi", "train.en")
]]

# For development 1,553 parallel sentences are used.
_ENVI_TEST_DATASETS = [[
    "https://github.com/stefan-it/nmt-en-vi/raw/master/data/dev-2012-en-vi.tgz",  # pylint: disable=line-too-long
    ("tst2012.vi", "tst2012.en")
]]

@registry.register_problem
class TranslateVienIwslt32k(translate.TranslateProblem):
  """Problem spec for IWSLT'15 Vi-En translation."""

  @property
  def approx_vocab_size(self):
    return 2**15  # 32768

  def source_data_files(self, dataset_split):
    train = dataset_split == problem.DatasetSplit.TRAIN
    return _ENVI_TRAIN_DATASETS if train else _ENVI_TEST_DATASETS

vi_en_problem = TranslateVienIwslt32k()

PROBLEM = "translate_vien_iwslt32k"
MODEL = "transformer" 
HPARAMS = "transformer_base"

# INSERT YOUR OWN DIRECTORY IN THE QUOTATION
DATA_DIR = os.path.expanduser("./vi_en/data")
TRAIN_DIR = os.path.expanduser("./vi_en/train")

tf.gfile.MakeDirs(DATA_DIR)
tf.gfile.MakeDirs(TRAIN_DIR)


#After training the model, re-run the environment but run this code in first, then predict.
tfe = tf.contrib.eager
tfe.enable_eager_execution()
Modes = tf.estimator.ModeKeys

# Get the encoders from the problem
encoders = vi_en_problem.feature_encoders(DATA_DIR)

ckpt_path = tf.train.latest_checkpoint(os.path.join(TRAIN_DIR))
# print(ckpt_path)

def main(argv):
    try:
      opts, args = getopt.getopt(argv,"hs:",["help", "source="])

      if len(opts) == 0:
        print("vi-en_decode.py -s <source_sentence>")

        sys.exit()

      for opt, arg in opts:
        if opt in ("-h", "--help"):
            print("vi-en_decode.py -s <source_sentence>")

            sys.exit()
        elif opt in ("-s", "--source"):
            source = urllib.parse.unquote(arg)

            if source != "":
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
                hparams = trainer_lib.create_hparams(HPARAMS, data_dir=DATA_DIR, problem_name=vi_en_problem )
                translate_model = registry.model(MODEL)(hparams, Modes.PREDICT)

                
                output = translate(source)

                # print("Inputs: %s" % source)
                # print("Outputs: %s" % output)
                print(output.capitalize())

                # file_input = open("translation/outputs.en","a")
                # file_input.write("Input: " + source + "\n")
                # file_input.write("Ouput: " + output + "\n" + "\n")
                # file_input.close()

        else:
            print("vi-en_decode.py -s <source_sentence>!!!!")

            sys.exit()
    except getopt.GetoptError as err:
        print(err)
        
        sys.exit(2)

if __name__ == "__main__":
   main(sys.argv[1:])
