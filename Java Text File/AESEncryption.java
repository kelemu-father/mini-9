package com.mycompany.aesencryption;

import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AESEncryption {

    public static void main(String[] args) throws Exception {
        // Read plaintext from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        // Generate AES key
        SecretKey key = generateAESKey();

        // Encrypt message
        byte[] encryptedBytes = encryptAES(plaintext, key);

        // Decrypt message
        String decryptedText = decryptAES(encryptedBytes, key);

        // Print key, encrypted message, and decrypted message
        System.out.println("Key: " + bytesToHex(key.getEncoded()));
        System.out.println("Encrypted message: " + bytesToHex(encryptedBytes));
        System.out.println("Decrypted message: " + decryptedText);
    }

    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES key size
        return keyGen.generateKey();
    }

    public static byte[] encryptAES(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decryptAES(byte[] encryptedBytes, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    // Utility method to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));//formats the byte as a two-digit hexadecimal number with leading zeros if necessary.
        }
        return result.toString();
    }
}
