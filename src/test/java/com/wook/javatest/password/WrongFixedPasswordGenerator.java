package com.wook.javatest.password;

public class WrongFixedPasswordGenerator implements PasswordGenerator{

    @Override
    public String generatorPassword() {
        return "wrong";
    }
}
