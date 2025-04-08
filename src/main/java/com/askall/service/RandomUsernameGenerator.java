package com.askall.service;
import java.util.Random;

public class RandomUsernameGenerator {
    public static void main(String[] args) {
        String username = generateRandomUsername(10); // 10 karakter uzunluğunda rastgele kullanıcı adı
        System.out.println(username);
    }

    public static String generateRandomUsername(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }
}
