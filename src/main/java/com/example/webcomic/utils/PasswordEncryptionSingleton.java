package com.example.webcomic.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncryptionSingleton {
    private PasswordEncryptionSingleton() {}

    public static PasswordEncryptionSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public static class SingletonHelper {
        private static final PasswordEncryptionSingleton INSTANCE = new PasswordEncryptionSingleton();
    }

    public String encrypt(String pass) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(pass, salt);
    }

    public boolean checkHashed(String pass){
        return pass.startsWith("$2a$10$");
    }

    public boolean compare(String rawPass, String encodePass){
        return BCrypt.checkpw(rawPass, encodePass);
    }

}
