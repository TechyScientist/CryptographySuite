package com.johnnyconsole.cryptographysuite.ejb.impl.block;


import com.johnnyconsole.cryptographysuite.ejb.interfaces.block.SimplifiedAESStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class SimplifiedAESStateless implements SimplifiedAESStatelessLocal {

    //RCON Values to use in generating the SAES Key Schedule
    private static final String RCON1 = "10000000";
    private static final String RCON2 = "00110000";

    //The SAES Key Schedule
    private static String Key0, Key1, Key2;

    //The S-Box for SAES Encryption
    private static final String[][] S = {
            {"1001", "0100", "1010", "1011"},
            {"1101", "0001", "1000", "0101"},
            {"0110", "0010", "0000", "0011"},
            {"1100", "1110", "1111", "0111"}
    };

    //The Inverse S-Box for SAES Decryption
    private static final String[][] SInv = {
            {"1010", "0101", "1001", "1011"},
            {"0001", "0111", "1000", "1111"},
            {"0110", "0000", "0010", "0011"},
            {"1100", "0100", "1101", "1110"}
    };

    //The left matrix for SAES Encryption Mix Column Step
    private static final String[][] ENC_MATRIX = {
            {"0001", "0100"},
            {"0100", "0001"}
    };

    //The left matrix for SAES Decryption Mix Column Step
    private static final String[][] DEC_MATRIX = {
            {"1001", "0010"},
            {"0010", "1001"}
    };

    //Multiplication Table for GF(2^4) mod x^4 + x + 1
    private static final String[][] MUL = {
            {"0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000"},
            {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"},
            {"0000", "0010", "0100", "0110", "1000", "1010", "1100", "1110", "0011", "0001", "0111", "0101", "1011", "1001", "1111", "1101"},
            {"0000", "0011", "0110", "0101", "1100", "1111", "1010", "1001", "1011", "1000", "1101", "1110", "0111", "0100", "0001", "0010"},
            {"0000", "0100", "1000", "1100", "0011", "0111", "1011", "1111", "0110", "0010", "1110", "1010", "0101", "0001", "1101", "1001"},
            {"0000", "0101", "1010", "1111", "0111", "0010", "1101", "1000", "1110", "1011", "0100", "0001", "1001", "1100", "0011", "0110"},
            {"0000", "0110", "1100", "1010", "1011", "1101", "0111", "0001", "0101", "0011", "1001", "1111", "1110", "1000", "0010", "0100"},
            {"0000", "0111", "1110", "1001", "1111", "1000", "0001", "0110", "1101", "1010", "0011", "0100", "0010", "0101", "1100", "1011"},
            {"0000", "1000", "0011", "1011", "0110", "1110", "0101", "1101", "1100", "0100", "1111", "0111", "1010", "0010", "1001", "0001"},
            {"0000", "1001", "0001", "1000", "0010", "1011", "0011", "1010", "0100", "1101", "0101", "1100", "0110", "1111", "0111", "1110"},
            {"0000", "1010", "0111", "1101", "1110", "0100", "1001", "0011", "1111", "0101", "1000", "0010", "0001", "1011", "0110", "1100"},
            {"0000", "1011", "0101", "1110", "1010", "0001", "1111", "0100", "0111", "1100", "0010", "1001", "1101", "0110", "1000", "0011"},
            {"0000", "1100", "1011", "0111", "0101", "1001", "1110", "0010", "1010", "0110", "0001", "1101", "1111", "0011", "0100", "1000"},
            {"0000", "1101", "1001", "0100", "0001", "1100", "1000", "0101", "0010", "1111", "1011", "0110", "0011", "1110", "1010", "0111"},
            {"0000", "1110", "1111", "0001", "1101", "0011", "0010", "1100", "1001", "0111", "0110", "1000", "0100", "1010", "1011", "1010"},
            {"0000", "1111", "1101", "0010", "1001", "0110", "0100", "1011", "0001", "1110", "1100", "0011", "1000", "0111", "1010", "1010"}
    };

    @Override
    public String encrypt(String msg, String key) {
        GenerateKeySchedule(key);
        String round0 = addKey(msg, Key0),
                round1 = encryptionRound1(round0);
        return encryptionRound2(round1);
    }

    @Override
    public String decrypt(String msg, String key) {
        GenerateKeySchedule(key);
        String round2 = addKey(msg, Key2),
                round1 = decryptionRound1(round2);
        return decryptionRound0(round1);
    }

    private static String XOR(String a, String b) {
        StringBuilder r = new StringBuilder();
        for(int i = 0; i < a.length(); i++) {
            switch(a.charAt(i) + "" + b.charAt(i)) {
                case "00":
                case "11":
                    r.append("0");
                    break;
                default:
                    r.append("1");
            }
        }
        return r.toString();
    }

    private static String rotateNibble(String s) {
        return s.substring(4) + s.substring(0, 4);
    }

    private static String NibSub(String s, String[][] box) {
        String s1 = box[Integer.parseInt(s.substring(0, 2), 2)][Integer.parseInt(s.substring(2, 4), 2)],
                s2 = box[Integer.parseInt(s.substring(4, 6), 2)][Integer.parseInt(s.substring(6), 2)];
        return s1 + s2;
    }

    private static String[][] NibbleMatrix(String s) {
        return new String[][]{
                new String[] {s.substring(0, 4), s.substring(8, 12)},
                new String[] {s.substring(4, 8), s.substring(12)},
        };
    }

    private static String BinaryString(String[][] nibbleMatrix) {
        return nibbleMatrix[0][0] + nibbleMatrix[1][0] + nibbleMatrix[0][1] + nibbleMatrix[1][1];
    }

    private static void GenerateKeySchedule(String key) {
        String[] W = new String[6];
        W[0] = key.substring(0, 8);
        W[1] = key.substring(8);
        Key0 = W[0] + W[1];
        W[2] = XOR(XOR(W[0], RCON1), NibSub(rotateNibble(W[1]), S));
        W[3] = XOR(W[2], W[1]);
        Key1 = W[2] + W[3];
        W[4] = XOR(XOR(W[2], RCON2), NibSub(rotateNibble(W[3]), S));
        W[5] = XOR(W[4], W[3]);
        Key2 = W[4] + W[5];
    }

    private static String addKey(String text, String key) {
        return XOR(text, key);
    }

    private static String mul(String a, String b) {
        return MUL[Integer.parseInt(a, 2)][Integer.parseInt(b, 2)];
    }

    private static String[][] mixColumn(String[][] matrix1, String[][] matrix2) {
        String[][] result = new String[matrix1.length][matrix1[0].length];
        result[0][0] = XOR(mul(matrix1[0][0], matrix2[0][0]), mul(matrix1[0][1], matrix2[1][0]));
        result[0][1] = XOR(mul(matrix1[0][0], matrix2[0][1]), mul(matrix1[0][1], matrix2[1][1]));
        result[1][0] = XOR(mul(matrix1[1][0], matrix2[0][0]), mul(matrix1[1][1], matrix2[1][0]));
        result[1][1] = XOR(mul(matrix1[1][0], matrix2[0][1]), mul(matrix1[1][1], matrix2[1][1]));
        return result;
    }

    private static String decryptionRound0(String text) {
        StringBuilder result = new StringBuilder();
        result.append(BinaryString(mixColumn(DEC_MATRIX, NibbleMatrix(text))));
        String nib2 = result.substring(4, 8),
                nib4 =result.substring(12);
        result.replace(4, 8, nib4);
        result.replace(12, 16, nib2);
        text = result.toString();
        result = new StringBuilder();
        result.append(NibSub(text.substring(0, 8), SInv));
        result.append(NibSub(text.substring(8), SInv));
        return addKey(result.toString(), Key0);

    }

    private static String encryptionRound1(String text) {
        StringBuilder result = new StringBuilder();
        result.append(NibSub(text.substring(0, 8), S));
        result.append(NibSub(text.substring(8), S));
        String nib2 = result.substring(4, 8),
                nib4 =result.substring(12);
        result.replace(4, 8, nib4);
        result.replace(12, 16, nib2);
        String[][] matrix = NibbleMatrix(result.toString());
        matrix = mixColumn(ENC_MATRIX, matrix);
        result = new StringBuilder(BinaryString(matrix));
        return addKey(result.toString(), Key1);
    }

    private static String decryptionRound1(String text) {
        StringBuilder result = new StringBuilder(text);
        String nib2 = result.substring(4, 8),
                nib4 =result.substring(12);
        result.replace(4, 8, nib4);
        result.replace(12, 16, nib2);
        text = result.toString();
        result = new StringBuilder();
        result.append(NibSub(text.substring(0, 8), SInv));
        result.append(NibSub(text.substring(8), SInv));
        return addKey(result.toString(), Key1);

    }

    private static String encryptionRound2(String text) {
        StringBuilder result = new StringBuilder();
        result.append(NibSub(text.substring(0, 8), S));
        result.append(NibSub(text.substring(8), S));
        String nib2 = result.substring(4, 8),
                nib4 =result.substring(12);
        result.replace(4, 8, nib4);
        result.replace(12, 16, nib2);
        return addKey(result.toString(), Key2);
    }
}
