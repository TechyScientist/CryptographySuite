package com.johnnyconsole.cryptographysuite.ejb.impl.block;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.block.SimplifiedDESStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class SimplifiedDESStateless implements SimplifiedDESStatelessLocal {

    private static final String[][] S0 = {
            {"01", "00", "11", "10"},
            {"11", "10", "01", "00"},
            {"00", "10", "01", "11"},
            {"11", "01", "11", "10"}
    };

    private static final String[][] S1 = {
            {"00", "01", "10", "11"},
            {"10", "00", "01", "11"},
            {"11", "00", "01", "00"},
            {"10", "01", "00", "11"}
    };

    @Override
    public String ecb_encrypt(String msg, String key) {
        String p10 = P10(key),
            firstShiftL = shift(p10.substring(0, 5), 1),
            firstShiftR = shift(p10.substring(5), 1),
            K1 = P8(firstShiftL + firstShiftR),
            K2 = P8(shift(firstShiftL, 2) + shift(firstShiftR, 2)),
            ip = IP(msg),
            pi1 = PIt(ip, K1),
            sw = SW(pi1),
            pi2 = PIt(sw, K2);
        return IPInv(pi2);
    }

    @Override
    public String ecb_decrypt(String msg, String key) {
        String p10 = P10(key),
                firstShiftL = shift(p10.substring(0, 5), 1),
                firstShiftR = shift(p10.substring(5), 1),
                K1 = P8(firstShiftL + firstShiftR),
                K2 = P8(shift(firstShiftL, 2) + shift(firstShiftR, 2)),
                ip = IP(msg),
                pi2 = PIt(ip, K2),
                sw = SW(pi2),
                pi1 = PIt(sw, K1);
        return IPInv(pi1);
    }

    @Override
    public String cbc_encrypt(String msg, String iv, String key) {
        return ecb_encrypt(XOR(msg, iv), key);
    }

    @Override
    public String cbc_decrypt(String msg, String iv, String key) {
        return XOR(ecb_decrypt(msg, key), iv);
    }

    private static String IP(String text) {
        return String.valueOf(text.charAt(1)) +
                text.charAt(5) +
                text.charAt(2) +
                text.charAt(0) +
                text.charAt(3) +
                text.charAt(7) +
                text.charAt(4) +
                text.charAt(6);
    }

    private static String PIt(String s, String key) {
        return XOR(s.substring(0, 4), T(s.substring(4), key)) + s.substring(4);
    }

    private static String T(String A, String key) {
        A = String.valueOf(A.charAt(3)) +
                A.charAt(0) +
                A.charAt(1) +
                A.charAt(2) +
                A.charAt(1) +
                A.charAt(2) +
                A.charAt(3) +
                A.charAt(0);

        String B = XOR(A, key);
        String s0x = B.charAt(0) + "" + B.charAt(3), s0y = B.charAt(1) + "" + B.charAt(2),
                s1x = B.charAt(4) + "" + B.charAt(7), s1y = B.charAt(5) + "" + B.charAt(6),
                s0 = S0[Integer.parseInt(s0x, 2)][Integer.parseInt(s0y, 2)],
                s1 = S1[Integer.parseInt(s1x, 2)][Integer.parseInt(s1y, 2)];
        return P4(s0 + s1);
    }

    private static String SW(String text) {
        return text.substring(4) + text.substring(0, 4);
    }

    private static String IPInv(String text) {
        return String.valueOf(text.charAt(3)) +
                text.charAt(0) +
                text.charAt(2) +
                text.charAt(4) +
                text.charAt(6) +
                text.charAt(1) +
                text.charAt(7) +
                text.charAt(5);
    }

    private static String shift(String value, int n) {
        while(n-- != 0) {
            value = value.substring(1) + value.charAt(0);
        }
        return value;
    }

    private static String XOR(String a, String b) {
        String r = "";
        for(int i = 0; i < a.length(); i++) {
            switch(a.charAt(i) + "" + b.charAt(i)) {
                case "00":
                case "11":
                    r += "0";
                    break;
                default:
                    r += "1";
            }
        }
        return r;
    }

    private static String P4(String key) {
        return String.valueOf(key.charAt(1)) +
                key.charAt(3) +
                key.charAt(2) +
                key.charAt(0);
    }

    private static String P8(String key) {
        while(key.length() > 8) {key = key.substring(1);}
        return String.valueOf(key.charAt(3)) +
                key.charAt(0) +
                key.charAt(4) +
                key.charAt(1) +
                key.charAt(5) +
                key.charAt(2) +
                key.charAt(7) +
                key.charAt(6);

    }

    private static String P10(String key) {
        return String.valueOf(key.charAt(2)) +
                key.charAt(4) +
                key.charAt(1) +
                key.charAt(6) +
                key.charAt(3) +
                key.charAt(9) +
                key.charAt(0) +
                key.charAt(8) +
                key.charAt(7) +
                key.charAt(5);
    }
}
