package com.johnnyconsole.cryptographysuite.ejb.objects;

public class NumberSequence {

    public String base;
    public int[] sequence;

    public NumberSequence(String base, int[] sequence) {
        this.base = base;
        this.sequence = sequence;
    }

}
