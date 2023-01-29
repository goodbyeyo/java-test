package com.wook.javatest.counter;

public class RaceConditionDemo {

    public static void main(String[] args) {
        // 멀티쓰레드 환경에서 하나의 자원을 공유하게되면 원치 않는 결과가 발생리
        // 상태를 유지하게 설계하면 안된다 -> Thread safe 하지 않음 -> synchronized 동기화 처리 필요
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
