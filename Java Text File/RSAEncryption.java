package com.mycompany.rsaencryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.Cipher;

public class RSAEncryption {

    public static void main(String[] args) throws Exception {
        // Generate RSA key pair
        KeyPair keyPair = generateRSAKeyPair();

        // Read message to encrypt from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        // Encrypt message
        byte[] encryptedBytes = encryptRSA(plaintext, keyPair.getPublic());

        // Decrypt message
        String decryptedBytes = decryptRSA(encryptedBytes, keyPair.getPrivate());

        // Print encrypted and decrypted messages
        System.out.println("Encrypted message: " + bytesToHex(encryptedBytes));
        System.out.println("Decrypted message: " + decryptedBytes);

        // Print public and private keys
        System.out.println("Public key: " + keyPair.getPublic());
        System.out.println("Private key: " + keyPair.getPrivate());
    }

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] encryptRSA(String plaintext, java.security.PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decryptRSA(byte[] encryptedBytes, java.security.PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
         byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    // Utility method to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b)); //formats the byte as a two-digit hexadecimal number with leading zeros if necessary.
        }
        return result.toString();
    }
}


