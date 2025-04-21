package com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey;

import javax.ejb.Local;

@Local
public interface RSAStatelessLocal {

    long[] generateKeypair(long p, long q, long e);
    String encrypt(String msg, long key);
    String decrypt(String msg, long key);

}
