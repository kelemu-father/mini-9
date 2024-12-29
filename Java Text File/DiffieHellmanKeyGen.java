package com.mycompany.diffiehellmankeygen;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellmanKeyGen {

    public static void main(String[] args) {
        // Read prime number p and primitive root g from user
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter prime number p: ");
        BigInteger p = scanner.nextBigInteger();

        System.out.print("Enter primitive root g: ");
        BigInteger g = scanner.nextBigInteger();

        System.out.println("Prime number p: " + p);
        System.out.println("Primitive root g: " + g);

        // Generate private keys for Alice and Bob
        BigInteger a = generatePrivateKey();
        BigInteger b = generatePrivateKey();

        System.out.println("Private key for Alice (a): " + a);
        System.out.println("Private key for Bob (b): " + b);

        // Calculate public keys for Alice and Bob
        BigInteger A = g.modPow(a, p);
        BigInteger B = g.modPow(b, p);

        System.out.println("Public key for Alice (A): " + A);
        System.out.println("Public key for Bob (B): " + B);

        // Key exchange
        BigInteger s1 = B.modPow(a, p); // Alice computes shared secret
        BigInteger s2 = A.modPow(b, p); // Bob computes shared secret

        System.out.println("Shared secret computed by Alice: " + s1);
        System.out.println("Shared secret computed by Bob: " + s2);

        // Verification
        System.out.println("Are the shared secrets equal? " + s1.equals(s2));
        
        scanner.close();
    }

    public static BigInteger generatePrivateKey() {
        // Generate a random 512-bit integer as a private key
        return new BigInteger(512, new SecureRandom());
    }
}

