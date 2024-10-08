package com.johnnyconsole.cryptographysuite.ejb.objects;

import java.io.Serializable;

public class VigenereString implements Serializable {

    public String method, string;

    public VigenereString(String method, String string) {
        this.method = method;
        this.string = string;
    }

}
