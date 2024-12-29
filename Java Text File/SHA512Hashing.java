package com.mycompany.sha512hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA512Hashing {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Read plaintext from user
        Scanner scanner = new Scanner(System.in);
        String plaintext = "";

        // Continue prompting user until correct plaintext is entered
        while (true) {
            System.out.print("Enter plaintext to hash: ");
            plaintext = scanner.nextLine();
            if (!plaintext.isEmpty()) {
                break; // Exit loop if non-empty input is provided
            }
            System.out.println("Plaintext cannot be empty. Please try again.");
        }

        // Compute SHA-512 hash
        String hashedText = hashSHA512(plaintext);

        // Print hashed message
        System.out.println("Hashed message (SHA-512): " + hashedText);

        // Read another input for verification
        String anotherPlaintext = "";
        boolean matchFound = false;
        while (!matchFound) {
            System.out.print("Enter another plaintext to verify: ");
            anotherPlaintext = scanner.nextLine();
            if (verifySHA512(anotherPlaintext, hashedText)) {
                matchFound = true;
                break;
            }
            System.out.println("Hash does not match! Please try again.");
        }

        // Verify if the hash matches
        System.out.println("Hash matches! The input is verified.");
    }

    public static String hashSHA512(String plaintext) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hashBytes = digest.digest(plaintext.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder result = new StringBuilder();
        for (byte b : hashBytes) {
            result.append(String.format("%02X", b));//formats the byte as a two-digit hexadecimal number with leading zeros if necessary.
        }
        return result.toString();
    }

    public static boolean verifySHA512(String plaintext, String expectedHash) throws NoSuchAlgorithmException {
        String computedHash = hashSHA512(plaintext);
        return computedHash.equals(expectedHash);
    }
}
