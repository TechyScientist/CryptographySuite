package com.johnnyconsole.cryptographysuite.ejb.impl.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.AtbashStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class AtbashStateless implements AtbashStatelessLocal {

    @Override
    public String encipherDecipher(String message) {
        StringBuilder result = new StringBuilder();
        for(char c: message.toCharArray()) {
            if(!Character.isLetter(c)) result.append(c);
            else result.append((char)(('Z' - c) + 'A'));
        }
        return result.toString();
    }
}
