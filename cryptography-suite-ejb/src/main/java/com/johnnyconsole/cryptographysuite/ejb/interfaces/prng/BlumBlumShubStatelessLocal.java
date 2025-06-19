package com.johnnyconsole.cryptographysuite.ejb.interfaces.prng;

import javax.ejb.Local;

@Local
public interface BlumBlumShubStatelessLocal {

    String[] generateBinarySequence(int bitLength, int p, int q, long seed, int length);
    int[] generateDecimalSequence(int bitLength, int p, int q, long seed, int length);
    String error();

}
