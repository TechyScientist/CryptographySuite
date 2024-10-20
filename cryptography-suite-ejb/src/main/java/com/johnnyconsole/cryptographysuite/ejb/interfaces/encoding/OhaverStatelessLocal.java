package com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding;

import javax.ejb.Local;

@Local
public interface OhaverStatelessLocal {

    String encodeDecode(String message);

}
