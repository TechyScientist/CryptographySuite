package com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution;

import javax.ejb.Local;

@Local
public interface KeyedVigenereStatelessLocal {

    String encipher(String alphabet, String msg, String key);
    String decipher(String alphabet, String msg, String key);
}