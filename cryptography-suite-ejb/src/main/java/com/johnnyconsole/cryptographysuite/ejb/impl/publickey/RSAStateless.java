package com.johnnyconsole.cryptographysuite.ejb.impl.publickey;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey.RSAStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class RSAStateless implements RSAStatelessLocal {
    private String error;

    @Override
    public long[] generateKeypair(long p, long q, long e) {
        //TODO: Implement RSA Keypair Generation Function
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
}
