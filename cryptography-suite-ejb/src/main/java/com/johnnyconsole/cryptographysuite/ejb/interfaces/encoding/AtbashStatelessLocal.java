package com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding;

import javax.ejb.Local;

@Local
public interface AtbashStatelessLocal {

    String encodeDecode(String message);

}
