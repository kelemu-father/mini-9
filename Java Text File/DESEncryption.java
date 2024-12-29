package com.mycompany.desencryption;

import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class DESEncryption {

    public static void main(String[] args) throws Exception {
        // Read plaintext from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        // Generate DES key
        SecretKey key = generateDESKey();

        // Encrypt message
        byte[] encryptedBytes = encryptDES(plaintext, key);

        // Decrypt message
        String decryptedText = decryptDES(encryptedBytes, key);

        // Print key, encrypted message, and decrypted message
        System.out.println("Key: " + bytesToHex(key.getEncoded()));
        System.out.println("Encrypted message: " + bytesToHex(encryptedBytes));
        System.out.println("Decrypted message: " + decryptedText);
    }

    public static SecretKey generateDESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        return keyGen.generateKey();
    }

    public static byte[] encryptDES(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decryptDES(byte[] encryptedBytes, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
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
