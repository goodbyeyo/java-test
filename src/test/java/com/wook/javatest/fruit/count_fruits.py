# -*- coding: utf-8 -*-
import sys
import tensorflow as tf

def count_fruits(image_path, model_path):
    # 이미지 파일 불러오기
    image = tf.keras.preprocessing.image.load_img(image_path, target_size=(100, 100))
    input_arr = tf.keras.preprocessing.image.img_to_array(image)
    input_arr = tf.expand_dims(input_arr, 0)

    # 모델 불러오기 및 예측
    model = tf.keras.models.load_model(model_path)
    predictions = model.predict(input_arr)
    fruit_count = decode_predictions(predictions)

    return fruit_count

def decode_predictions(predictions):
    # 가장 높은 확률을 가진 과일 인덱스를 찾습니다.
    fruit_index = np.argmax(predictions)

    # 이 예제에서는 과일의 종류를 구분하지 않고 개수만 확인하므로, 1을 반환합니다.
    return 1

if __name__ == "__main__":
    image_path = sys.argv[1]
    model_path = sys.argv[2]
    count = count_fruits(image_path, model_path)
    print(count)