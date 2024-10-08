package com.johnnyconsole.cryptographysuite.ejb.objects;

import java.io.Serializable;

public class VigenereString implements Serializable {

    public String method, keyword, string, alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public VigenereString(String method, String keyword, String string) {
        this.method = method;
        this.string = string;
    }

    public VigenereString(String alphabet, String keyword, String method, String string) {
        this(method, keyword, string);
        this.alphabet = alphabet;
    }

}
