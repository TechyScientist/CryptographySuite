package com.johnnyconsole.cryptographysuite.ejb.impl.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.SimpleSubstitutionStatelessLocal;

import javax.ejb.Stateless;

@Stateless
public class SimpleSubstitutionStateless implements SimpleSubstitutionStatelessLocal {

    @Override
    public String encipherDecipher(String alphabet, String message) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < message.length(); i++) {
            if(!Character.isLetter(message.charAt(i))) result.append(message.charAt(i));
            else result.append(alphabet.charAt(message.charAt(i) - 'A'));
        }
        return result.toString();
    }
}
