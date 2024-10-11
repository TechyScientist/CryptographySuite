package com.johnnyconsole.cryptographysuite.ejb.objects;

import java.io.Serializable;

public class VigenereString implements Serializable {

    public String method, keyword, string, alphabetkeyword, alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public VigenereString(String method, String keyword, String string) {
        this.method = method;
        this.keyword = keyword;
        this.string = string;
    }

    public VigenereString(String alphabet, String alphabetkeyword, String keyword, String method, String string) {
        this(method, keyword, string);
        this.alphabetkeyword = alphabetkeyword;
        this.alphabet = alphabet;
    }

}
