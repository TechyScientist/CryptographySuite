package com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding;

import javax.ejb.Local;
import java.util.HashMap;

@Local
public interface OhaverStatelessLocal {

    String encode(String message);
    String decode(String message);

}
