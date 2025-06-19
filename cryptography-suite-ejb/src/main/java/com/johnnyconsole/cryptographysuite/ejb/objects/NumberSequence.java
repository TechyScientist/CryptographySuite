package com.johnnyconsole.cryptographysuite.ejb.objects;

public class NumberSequence {

    public String base;
    public int bitLength;
    public int[] sequence;

    public NumberSequence(String base, int bitLength, int[] sequence) {
        this.base = base;
        this.bitLength = bitLength;
        this.sequence = sequence;
    }

}
