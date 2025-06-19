package com.johnnyconsole.cryptographysuite.ejb.impl.prng;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.prng.BlumBlumShubStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class BlumBlumShubStateles implements BlumBlumShubStatelessLocal {
    private String error;

    @Override
    public String[] generateBinarySequence(int bitLength, int p, int q, long seed, int length) {
        if(!check(bitLength, p, q, seed, length)) return null;

        int n = p * q;
        String[] seq = new String[length];
        seed = Math.floorMod((seed * seed), n);
        for(int i = 0; i < length; i++) {
            StringBuilder bin = new StringBuilder();
            for(int bit = 0; bit < bitLength; bit++) {
                seed = Math.floorMod((seed * seed), n);
                bin.append(Math.floorMod((seed * seed), 2));
            }
            seq[i] = bin.toString();
        }
        return seq;
    }

    @Override
    public int[] generateDecimalSequence(int bitLength, int p, int q, long seed, int length) {
        if(!check(bitLength, p, q, seed, length)) return null;
        int n = p * q;
        int[] seq = new int[length];
        seed = Math.floorMod((seed * seed), n);
        for(int i = 0; i < length; i++) {
            StringBuilder bin = new StringBuilder();
            for(int bit = 0; bit < bitLength; bit++) {
                seed = Math.floorMod((seed * seed), n);
                bin.append(Math.floorMod((seed * seed), 2));
            }
            seq[i] = Integer.parseInt(bin.toString(), 2);
        }
        return seq;
    }

    @Override
    public String error() {
        return error;
    }

    private boolean check(int bitLength, int p, int q, long seed, int length) {
        if(bitLength < 1 || bitLength > 32) {
            error = "bit length = " + bitLength + " is out of range: must be within the range [1, 32]";
            return false;
        }
        if(!isPrime(p)) {
            error = "p = " + p + " is not prime";
            return false;
        }
        if(!isPrime(q)) {
            error = "q = " + q + " is not prime";
            return false;
        }
        if(Math.floorMod(p, 4) != 3) {
            error = "p = " + p + " is not 3 mod 4: is " + Math.floorMod(p, 4) + " mod 4";
            return false;
        }
        if(Math.floorMod(q, 4) != 3) {
            error = "q = " + q + " is not 3 mod 4:  is " + Math.floorMod(q, 4) + " mod 4";
            return false;
        }
        long n = (long) p * q;
        if(seed < 1 || seed >= n) {
            error = "seed = " + seed + " is out of range: must be within the range [1, " + n + ")";
            return false;
        }
        if(gcd(seed, n) != 1) {
            error = "seed = " + seed + " and n = " + n + " are not coprime";
            return false;
        }
        if(length < 1) {
            error = "length = "  + length + " must be positive and nonzero";
            return false;
        }
        return true;
    }

    private long gcd(long a, long b) {
        for(long n = Math.min(a, b); n > 1; n--) {
            if(Math.floorMod(a, n) == 0 && Math.floorMod(b, n) == 0) return n;
        }
        return 1;
    }

    private boolean isPrime(long n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if (Math.floorMod(n, i) == 0) return false;
        }
        return true;
    }
}
