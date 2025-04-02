package com.johnnyconsole.cryptographysuite.ejb.interfaces.block;

import javax.ejb.Local;

@Local
public interface SimplifiedDESStatelessLocal {

    String encrypt(String msg, String key);
    String decrypt(String msg, String key);

}
