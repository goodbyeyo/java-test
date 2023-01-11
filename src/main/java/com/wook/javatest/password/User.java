package com.wook.javatest.password;

public class User {

    private String password;

    public void initPassword(PasswordGenerator passwordGenerator) {
        // AS-IS
        // as_is();

        // TO-BE
        String password = passwordGenerator.generatorPassword();
        if (password.length() >= 8 && password.length() <= 12) {
            this.password = password;
        }

    }

    private void as_is() {
        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
        String randomPassword = randomPasswordGenerator.generatePassword();
        if (randomPassword.length() >= 8 && randomPassword.length() <= 12) {
            this.password = randomPassword;
        }
    }

    public String getPassword() {
        return password;
    }
}

