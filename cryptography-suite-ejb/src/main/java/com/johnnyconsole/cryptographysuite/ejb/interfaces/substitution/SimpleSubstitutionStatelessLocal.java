package com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution;

import javax.ejb.Local;

@Local
public interface SimpleSubstitutionStatelessLocal {

    String encipherDecipher(String alphabet, String message);

}
