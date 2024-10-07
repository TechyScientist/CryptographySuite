package com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding;

import javax.ejb.Local;

@Local
public interface MorseStatelessLocal {

    String encode(String msg);
    String decode(String msg);

}
