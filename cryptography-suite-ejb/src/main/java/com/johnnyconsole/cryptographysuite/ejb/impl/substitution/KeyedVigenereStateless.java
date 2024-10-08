package com.johnnyconsole.cryptographysuite.ejb.impl.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.KeyedVigenereStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class KeyedVigenereStateless implements KeyedVigenereStatelessLocal {

    public char[][] table = new char[26][26];

    private void generateTable(String alphabet) {
        char[] letters = alphabet.toUpperCase().toCharArray();
        System.arraycopy(letters, 0, table[0], 0, table[0].length);
        for(int i = 1; i < table.length; i++) {
            System.arraycopy(table[i - 1], 1, table[i], 0, table[i].length - 1);
            table[i][table[i].length - 1] = table[i - 1][0];
        }
    }

    public String encipher(String alphabet, String msg, String key) {
        generateTable(alphabet);
        key = blowUpKey(msg, key.toUpperCase());
        StringBuilder ciphertext = new StringBuilder();
        for(int i = 0; i < msg.length(); i++) {
            ciphertext.append(encipher(msg.toUpperCase().charAt(i), key.charAt(i)));
        }
        return ciphertext.toString();
    }

    public String decipher(String alphabet, String msg, String key) {
        generateTable(alphabet);
        key = blowUpKey(msg, key.toUpperCase());
        StringBuilder plaintext = new StringBuilder();
        for(int i = 0; i < msg.length(); i++) {
            plaintext.append(decipher(msg.toUpperCase().charAt(i), key.charAt(i)));
        }
        return plaintext.toString();
    }

    private int findTableRow(char c) {
        for (int i = 0; i < table.length; i++) {
            if(table[i][0] == c) return i;
        }
        return -1;
    }

    private int findKeyColumn(char c) {
        for (int i = 0; i < table[0].length; i++) {
            if(table[0][i] == c) return i;
        }
        return -1;
    }

    private char encipher(char c, char key) {
        if(!Character.isLetter(c)) return c;
        return table[findTableRow(c)][findKeyColumn(key)];
    }

    private char decipher(char c, char key) {
        if(!Character.isLetter(c)) return c;
        int row = findTableRow(key);
        for (int i = 0; i < table[row].length; i++) {
            if(table[row][i] == c) return table[0][i];
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