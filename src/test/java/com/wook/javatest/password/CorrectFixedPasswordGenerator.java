package com.wook.javatest.password;

public class CorrectFixedPasswordGenerator implements PasswordGenerator{

    @Override
    public String generatorPassword() {
        return "password";
    }
}
