package com.johnnyconsole.cryptographysuite.ejb.interfaces.block;

import javax.ejb.Local;

@Local
public interface SimplifiedDESStatelessLocal {

    String ecb_encrypt(String msg, String key);
    String ecb_decrypt(String msg, String key);

}
