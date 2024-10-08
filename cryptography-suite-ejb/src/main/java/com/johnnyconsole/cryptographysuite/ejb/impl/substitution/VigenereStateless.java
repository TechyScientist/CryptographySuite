package com.johnnyconsole.cryptographysuite.ejb.impl.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.VigenereStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class VigenereStateless implements VigenereStatelessLocal {

    public char[][] table = new char[26][26];

    private void generateTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (char)('A' + (i + j) % 26);
            }
        }
    }

    @Override
    public String encipher(String msg, String key) {
        generateTable();
        key = blowUpKey(msg, key.toUpperCase());
        StringBuilder ciphertext = new StringBuilder();
        for(int i = 0; i < msg.length(); i++) {
            ciphertext.append(encipher(msg.toUpperCase().charAt(i), key.charAt(i)));
        }
        return ciphertext.toString();
    }

    @Override
    public String decipher(String msg, String key) {
        generateTable();
        key = blowUpKey(msg, key.toUpperCase());
        StringBuilder plaintext = new StringBuilder();
        for(int i = 0; i < msg.length(); i++) {
            plaintext.append(decipher(msg.toUpperCase().charAt(i), key.charAt(i)));
        }
        return plaintext.toString();
    }

    private char encipher(char c, char key) {
        if(!Character.isLetter(c)) return c;
        return table[c - 'A'][key - 'A'];
    }

    private char decipher(char c, char key) {
        if(!Character.isLetter(c)) return c;
        for(int i = 0; i < table[key - 'A'].length; i++) {
            if(table[key - 'A'][i] == c) return table[0][i];
        }
        return '\0';
    }

    private String blowUpKey(String msg, String key) {
        int l = key.length();
        for (int i = key.length(); i < msg.length() ; i++) {
            key += key.charAt(i % l);
        }
        return key;
    }
}
