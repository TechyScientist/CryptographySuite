package com.johnnyconsole.cryptographysuite.ejb.objects;

import java.io.Serializable;

public class MorseString implements Serializable {

    public String method, string;

    public MorseString(String method, String string) {
        this.method = method;
        this.string = string;
    }

}
