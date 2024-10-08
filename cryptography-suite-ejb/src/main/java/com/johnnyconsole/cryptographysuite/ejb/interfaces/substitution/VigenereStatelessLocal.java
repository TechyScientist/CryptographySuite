package com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution;

import javax.ejb.Local;

@Local
public interface VigenereStatelessLocal {

    String encipher(String msg, String key);
    String decipher(String msg, String key);
}
