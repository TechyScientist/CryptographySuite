package com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution;

import javax.ejb.Local;

@Local
public interface AtbashStatelessLocal {

    String encipherDecipher(String message);

}
