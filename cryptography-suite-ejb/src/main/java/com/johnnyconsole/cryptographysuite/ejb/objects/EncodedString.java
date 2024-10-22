package com.johnnyconsole.cryptographysuite.ejb.objects;

import java.io.Serializable;

public class EncodedString implements Serializable {

    public String method, string;

    public EncodedString(String method, String string) {
        this.method = method;
        this.string = string;
    }

}
