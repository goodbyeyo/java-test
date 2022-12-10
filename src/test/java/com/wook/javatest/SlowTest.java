package com.wook.javatest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메서드에 사용
@Retention(RetentionPolicy.RUNTIME) // 설정을 Runtime 까지 유지
@Test   // Jupiter 가 제공하는 테스트
@Tag("slow")
public @interface SlowTest {
}
