package com.johnnyconsole.cryptographysuite.ejb.objects;

public class NumberSequence {

    public String base;
    public int p, q, seed, bitLength;
    public int[] intSequence;
    public String[] strSequence;

    public NumberSequence(int p, int q, int seed, int bitLength, int[] sequence) {
        this.base = "Decimal";
        this.p = p;
        this.q = q;
        this.seed = seed;
        this.bitLength = bitLength;
        this.intSequence = sequence;
    }

    public NumberSequence(int p, int q, int seed, int bitLength, String[] sequence) {
        this.base = "Binary";
        this.p = p;
        this.q = q;
        this.seed = seed;
        this.bitLength = bitLength;
        this.strSequence = sequence;
    }

}
