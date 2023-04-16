# -*- coding: utf-8 -*-
import os
import sys
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense
from tensorflow.keras.preprocessing.image import ImageDataGenerator

def train_model(train_dir, validation_dir, model_path):
    # 데이터 전처리
    train_datagen = ImageDataGenerator(rescale=1./255)
    validation_datagen = ImageDataGenerator(rescale=1./255)

    target_size = (100, 100)
    batch_size = 32

    train_generator = train_datagen.flow_from_directory(
        train_dir,
        target_size=target_size,
        batch_size=batch_size,
        class_mode='categorical'
    )

    validation_generator = validation_datagen.flow_from_directory(
        validation_dir,
        target_size=target_size,
        batch_size=batch_size,
        class_mode='categorical'
    )

    num_classes = len(train_generator.class_indices)

    # 모델 구성
    model = Sequential([
        Conv2D(32, kernel_size=(3, 3), activation='relu', input_shape=(100, 100, 3)),
        MaxPooling2D(pool_size=(2, 2)),
        Conv2D(64, kernel_size=(3, 3), activation='relu'),
        MaxPooling2D(pool_size=(2, 2)),
        Flatten(),
        Dense(128, activation='relu'),
        Dense(num_classes, activation='softmax')
    ])

    # 모델 컴파일
    model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

    # 모델 학습
    model.fit(train_generator, epochs=10, validation_data=validation_generator)

    # 모델 저장
    model.save(model_path)

if __name__ == "__main__":
    train_dir = "fruits-360/Training" # 실제 훈련 데이터셋 경로로 변경
    validation_dir = "fruits-360/Test" # 실제 검증 데이터셋 경로로 변경
    model_path = "your_model_path/model.h5" # 실제 모델 저장 경로로 변경

    train_model(train_dir, validation_dir, model_path)
