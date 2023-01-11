package com.wook.javatest.password;

// 메서드 하나를 가진 인터페이스 -> 어노테이션과 람다를 이용해서 구현체를 생략할수 있다
@FunctionalInterface
public interface PasswordGenerator {
    String generatorPassword();
}
