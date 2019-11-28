import os
import sys
import getopt
import argparse
import urllib.parse
import tensorflow as tf
import numpy as np
import re

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
DATA_DIR = os.path.expanduser("./en_vi/data")
TRAIN_DIR = os.path.expanduser("./en_vi/train")

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
                
                def listToString(list):
                    temp = ''

                    for sentence in list:
                        temp += sentence

                    return temp
                    # return (temp.join(list))

                #Predict 
                hparams = trainer_lib.create_hparams(HPARAMS, data_dir=DATA_DIR, problem_name=PROBLEM )
                translate_model = registry.model(MODEL)(hparams, Modes.PREDICT)

                inputs = source
                # outputs = translate(inputs)
                # ref = 'Làm sao tôi có thể trình bày trong 10 phút về sợi dây liên kết những người phụ nữ qua ba thế hệ , về việc làm thế nào những sợi dây mạnh mẽ đáng kinh ngạc ấy đã níu chặt lấy cuộc sống của một cô bé bốn tuổi co quắp với đứa em gái nhỏ của cô bé , với mẹ và bà trong suốt năm ngày đêm trên con thuyền nhỏ lênh đênh trên Biển Đông hơn 30 năm trước , những sợi dây liên kết đã níu lấy cuộc đời cô bé ấy và không bao giờ rời đi -- cô bé ấy giờ sống ở San Francisco và đang nói chuyện với các bạn hôm nay? Câu chuyện này chưa kết thúc. Nó là một trò chơi ghép hình vẫn đang được xếp. Hãy để tôi kể cho các bạn về vài mảnh ghép nhé. Hãy tưởng tượng mảnh đầu tiên : một người đàn ông đốt cháy sự nghiệp cả đời mình. Ông là nhà thơ , nhà viết kịch , một người mà cả cuộc đời chênh vênh trên tia hi vọng duy nhất rằng đất nước ông sẽ độc lập tự do. Hãy tưởng tượng ông , một người cộng sản tiến vào Sài Gòn , đối diện sự thật rằng cả cuộc đời ông đã phí hoài. Ngôn từ , qua bao năm tháng là bạn đồng hành với ông , giờ quay ra chế giễu ông. Ông rút lui vào yên lặng. Ông qua đời , bị lịch sử quật ngã. Ông là ông của tôi. Tôi chưa bao giờ gặp ông ngoài đời. Nhưng cuộc đời ta nhiều hơn những gì ta lưu trong kí ức nhiều. Bà tôi chưa bao giờ cho phép tôi quên cuộc đời của ông.'

                # print("Inputs: %s" % inputs)
                # print("Outputs: %s" % outputs)
                # print(outputs.capitalize())

                output = []
                inputs = re.split('[.?] +', inputs)
                for i in inputs:
                    output.append(translate(i))
                print(listToString(output))
                

                # file_input = open("translation/outputs.vi","w+")
                # for line in output:
                #     file_input.write(line)
                # file_input.close()

                # file_input = open("translation/ref.vi","w+")
                # file_input.write(ref)
                # file_input.close()

        else:
            print("test.py -s <source_sentence>!!!!")

            sys.exit()
    except getopt.GetoptError as err:
        print(err)
        
        sys.exit(2)


if __name__ == "__main__":
   main(sys.argv[1:])
