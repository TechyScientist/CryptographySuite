package com.johnnyconsole.cryptographysuite.ejb.impl.publickey;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey.RSAStatelessLocal;

import javax.ejb.Stateless;
import java.math.BigInteger;

@Stateless
public class RSAStateless implements RSAStatelessLocal {
    private String error;

    private String expMod(long a, long b, long n) {
        return BigInteger.valueOf(a).modPow(BigInteger.valueOf(b), BigInteger.valueOf(n)).toString();

    }

    @Override
    public long[] generateKeypair(long p, long q, long e) {
        if(isNotPrime(p)) {
            error = "p-not-prime";
            return null;
        }
        if(isNotPrime(q)) {
            error = "q-not-prime";
            return null;
        }

        long n = p * q, t = (p - 1) * (q - 1);
        if (gcd(e, t) == 1) {
            long[] solution = ModularSolver(e, t);
            if(solution != null) return new long[] {n, e, solution[0], t};
            error = "inverse-error";
            return null;
        }
        error = "e-incorrect";
        return null;
    }

    @Override
    public String encrypt(String message, long key, long modulus) {
        StringBuilder ciphertext = new StringBuilder();

        for(long p: message.toCharArray()) {
            ciphertext.append(expMod(p, key, modulus)).append(' ');
        }

        return ciphertext.deleteCharAt(ciphertext.length() - 1).toString();
    }

    @Override
    public String decrypt(String message, long key, long modulus) {
        StringBuilder plaintext = new StringBuilder();

        String[] blocks = message.split(" ");

        for (String block : blocks) {
            plaintext.append((expMod(Long.parseLong(block), key, modulus))).append(' ');
        }
        return plaintext.toString();
    }

    public String error() {
        return error;
    }

    private long gcd(long a, long b) {
        return ExtendedEuclidean(a, b)[0];
    }

    private long[] ExtendedEuclidean(long a, long b) {
        if(b == 0) return new long[] {a, 1, 0};
        else {
            long[] res = ExtendedEuclidean(b, Math.floorMod(a, b));
            return new long[] {res[0], res[2], res[1] - Math.floorDiv(a, b) * res[2]};
        }
    }

    private long[] ModularSolver(long a, long n) {
        long[] res = ExtendedEuclidean(a, n);
        long d = res[0], x = res[1];
        if(d != 0) {
            long[] answers = new long[(int)d];
            long x0 = Math.floorMod(x,  n);
            for(int i = 0; i < d; i++) {
                answers[i] = Math.floorMod(x0 + i * Math.floorDiv(n, d), n);
            }
            return answers;
        }
        return null;
    }

    private boolean isNotPrime(long l) {
        for(int i = 2; i < Math.sqrt(l); i++) {
            if(l % i == 0) return true;
        }
        return false;
    }

}
