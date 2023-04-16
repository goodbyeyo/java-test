package com.wook.javatest.fruit;

import org.python.util.PythonInterpreter;

public class FruitCounter {
    public static void main (String[] args){
        String imageFilePath = "User/wook/study/fruit.jpg";
        String modelFilePath = "User/wook/study/model.h5";

        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.execfile("/Users/wook/Study/java-test/src/test/java/com/wook/javatest/fruit/count_fruits.py");
            int fruitCount = interpreter.eval("count_fruits('" + imageFilePath + "', '" + modelFilePath + "')").asInt();
            System.out.println("과일 개수: " + fruitCount);
        }
    }
}