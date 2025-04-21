package com.johnnyconsole.cryptographysuite.ejb.impl.publickey;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey.RSAStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class RSAStateless implements RSAStatelessLocal {
    private String error;

    @Override
    public long[] generateKeypair(long p, long q, long e) {
        if(!isPrime(p)) {
            error = "p-not-prime";
            return null;
        }
        if(!isPrime(q)) {
            error = "q-not-prime";
            return null;
        }

        long n = p * q, t = (p - 1) * (q - 1);
        if (gcd(e, t) == 1) {
            long[] solution = ModularSolver(e, 1, t);
            if(solution != null) return new long[] {n, e, Math.floorMod(solution[1], t)};
            error = "inverse-error";
            return null;
        }
        error = "e-incorrect";
        return null;
    }

    @Override
    public String encrypt(String message, long key) {
        //TODO: Implement RSA encryption function
        return "";
    }

    @Override
    public String decrypt(String message, long key) {
        //TODO: Implement RSA decryption function
        return "";
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

    private long[] ModularSolver(long a, long b, long n) {
        long[] res = ExtendedEuclidean(a, n);
        long d = res[0], x = res[1], y = res[2];
        if(d != 0 || b != 0) {
            long[] answers = new long[(int)d];
            long x0 = Math.floorMod(x * Math.floorDiv(b, d),  n);
            for(int i = 0; i < d; i++) {
                answers[i] = Math.floorMod(x0 + i * Math.floorDiv(n, d), n);
            }
            return answers;
        }
        return null;
    }

    private boolean isPrime(long l) {
        for(int i = 2; i < Math.sqrt(l); i++) {
            if(l % i == 0) return false;
        }
        return true;
    }

}
