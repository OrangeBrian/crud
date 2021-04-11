package com.orange.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptingPassword {
    public static void main(String[] args) {
        String password = "123";
        System.out.println(password);
        System.out.println(encryptingPassword(password));
    }

    public static String encryptingPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
