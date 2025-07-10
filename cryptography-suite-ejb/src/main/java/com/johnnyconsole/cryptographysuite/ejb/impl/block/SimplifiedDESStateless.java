package com.johnnyconsole.cryptographysuite.ejb.impl.block;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.block.SimplifiedDESStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class SimplifiedDESStateless implements SimplifiedDESStatelessLocal {

    private final String[][] S0 = {
            {"01", "00", "11", "10"},
            {"11", "10", "01", "00"},
            {"00", "10", "01", "11"},
            {"11", "01", "11", "10"}
    };

    private final String[][] S1 = {
            {"00", "01", "10", "11"},
            {"10", "00", "01", "11"},
            {"11", "00", "01", "00"},
            {"10", "01", "00", "11"}
    };

    private final int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6},
            P8 = {6, 3, 7, 4, 8, 5, 10, 9},
            P4 = {2, 4, 3, 1},
            IP = {2, 6, 3, 1, 4, 8, 5, 7},
            IPInv = {4, 1, 3, 5, 7, 2, 8, 6},
            T = {4, 1, 2, 3, 2, 3, 4, 1};

    private String K1, K2;


    @Override
    public String ecb_encrypt(String plaintext, String key) {
        generateKeys(key);
        StringBuilder ciphertext = new StringBuilder();
        for (char plain : plaintext.toCharArray()) {
            ciphertext.append(ecb_encryptChar(plain)).append(' ');
        }

        return ciphertext.deleteCharAt(ciphertext.length() - 1).toString();
    }

    @Override
    public String ecb_decrypt(String ciphertext, String key) {
        generateKeys(key);
        String[] chars = ciphertext.split(" ");
        StringBuilder plaintext = new StringBuilder();
        for (String binary : chars) {
            plaintext.append(ecb_decryptChar(binary));
        }

        return plaintext.toString();
    }

    @Override
    public String cbc_encrypt(String plaintext, String iv, String key) {
        return null;
    }

    @Override
    public String cbc_decrypt(String ciphertext, String iv, String key) {
        return null;
    }

    private void generateKeys(String key) {
        String p10 = permute(key, P10),
                a = shift(p10.substring(0, 5), 1),
                b = shift(p10.substring(5), 1);

        K1 = permute(a + b, P8);
        a = shift(a, 2);
        b = shift(b, 2);
        K2 = permute(a + b, P8);
    }

    private String ecb_encryptChar(char c) {
        String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'),
                ip = permute(binary, IP),
                pi1 = Pi(ip, K1),
                pi2 = Pi(nibbleSwap(pi1), K2);
        return permute(pi2, IPInv);
    }

    private char ecb_decryptChar(String s) {
        String ip = permute(s, IP),
                pi2 = Pi(ip, K2),
                pi1 = Pi(nibbleSwap(pi2), K1);
        return (char) (Integer.parseInt(permute(pi1, IPInv), 2));
    }

    private String permute(String bits, int[] ordering) {
        StringBuilder result = new StringBuilder();
        for (int bit : ordering) {
            result.append(bits.charAt(bit - 1));
        }
        return result.toString();
    }

    private String shift(String bits, int offset) {
        return bits.substring(offset) + bits.substring(0, offset);
    }

    private String nibbleSwap(String bits) {
        return bits.substring(4) + bits.substring(0, 4);
    }

    private String Pi(String bits, String key) {
        String left = bits.substring(0, 4),
                right = bits.substring(4),
                t = T(right, key);

        return XOR(left, t) + right;
    }

    private String T(String bits, String key) {
        String s = XOR(permute(bits, T), key);
        char[] chars = s.toCharArray();
        int s0x = Integer.parseInt((chars[0] - '0') + "" + (chars[3] - '0'), 2),
                s0y = Integer.parseInt((chars[1] - '0') + "" + (chars[2] - '0'), 2),
                s1x = Integer.parseInt((chars[4] - '0') + "" + (chars[7] - '0'), 2),
                s1y = Integer.parseInt((chars[5] - '0') + "" + (chars[6] - '0'), 2);
        String s0 = S0[s0x][s0y],
                s1 = S1[s1x][s1y];

        return permute(s0 + s1, P4);
    }

    private String XOR(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            switch (String.valueOf(a.charAt(i)) + b.charAt(i)) {
                case "00":
                case "11":
                    result.append("0");
                    break;
                default:
                    result.append("1");
            }
        }
        return result.toString();
    }
}
