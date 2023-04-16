# -*- coding: utf-8 -*-
import sys
import tensorflow as tf

def count_fruits(image_path, model_path):
    # 이미지 파일 불러오기
    image = tf.keras.preprocessing.image.load_img(image_path, target_size=(224, 224))
    input_arr = tf.keras.preprocessing.image.img_to_array(image)
    input_arr = tf.expand_dims(input_arr, 0)

    # 모델 불러오기 및 예측
    model = tf.keras.models.load_model(model_path)
    predictions = model.predict(input_arr)
    fruit_count = decode_predictions(predictions)

    return fruit_count

def decode_predictions(predictions):
    # 예측 결과를 해석 반올림
    fruit_count = int(round(predictions[0][0]))
    return fruit_count

if __name__ == "__main__":
    image_path = sys.argv[1]
    model_path = sys.argv[2]
    count = count_fruits(image_path, model_path)
    print(count)