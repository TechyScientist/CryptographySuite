package com.johnnyconsole.cryptographysuite.ejb.impl.encoding;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding.MorseStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class MorseStateless implements MorseStatelessLocal {

    private final char[] plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private final String[] morse = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-",
            "...-", ".--", "-..-", "-.--", "--..",
            "-----", ".----", "..---", "...--", "....-",
            ".....", "-....", "--...", "---..", "----."
    };

    @Override
    public String encode(String msg) {
        if(msg == null || msg.isEmpty()) return "";
        StringBuilder encoded = new StringBuilder();

        for(char c : msg.toUpperCase().toCharArray()) {
            encoded.append(encodeChar(c)).append("/");
        }

        if(encoded.charAt(encoded.length() - 1) == '/') {
            encoded.deleteCharAt(encoded.length() - 1);
        }

        return encoded.toString();
    }

    @Override
    public String decode(String msg) {
        if(msg == null || msg.isEmpty()) return "";
        StringBuilder decoded = new StringBuilder();

        for(String c : msg.split("/")) {
            decoded.append(decodeChar(c));
        }

        return decoded.toString();
    }

    private String encodeChar(char c) {
        return plaintextIndex(c) == -1 ? c + "" : morse[plaintextIndex(c)];
    }

    private char decodeChar(String c) {
       return morseIndex(c) == -1 ? c.charAt(0) : plaintext[morseIndex(c)];
    }

    private int morseIndex(String c) {
        for(int i = 0; i < morse.length; i++) {
            if(morse[i].equals(c)) return i;
        }
        return -1;
    }

    private int plaintextIndex(char c) {
        for(int i = 0; i < plaintext.length; i++) {
            if(plaintext[i] == c) return i;
        }
        return -1;
    }
}
