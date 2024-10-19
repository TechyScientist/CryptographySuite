package com.johnnyconsole.cryptographysuite.ejb.impl.encoding;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding.OhaverStatelessLocal;

import javax.ejb.Stateless;
import java.util.HashMap;

@Stateless
public class OhaverStateless implements OhaverStatelessLocal {

      private final HashMap<Character, String> morse = new HashMap<Character, String>() {{
          put('A', ".-"); put('B', "-..."); put('C', "-.-."); put('D', "-.."); put('E', ".");
          put('F', "..-."); put('G', "--."); put('H', "...."); put('I', ".."); put('J', ".---");
          put('K', "-.-"); put('L', ".-.."); put('M', "--"); put('N', "-."); put('O', "---");
          put('P', ".--."); put('Q', "--.-"); put('R', ".-."); put('S', "..."); put('T', "-");
          put('U', "..-"); put('V', "...-"); put('W', ".--"); put('X', "-..-"); put('Y', "-.--");
          put('Z', "--.."); put('_', "..--"); put('.', "---."); put(',', ".-.-"); put('!', "----");
      }};

    private final HashMap<String, Character> plain = new HashMap<String, Character>() {{
        put(".-", 'A'); put("-...", 'B'); put("-.-.", 'C'); put("-..", 'D'); put(".", 'E');
        put("..-.", 'F'); put("--.", 'G'); put("....", 'H'); put("..", 'I'); put(".---", 'J');
        put("-.-", 'K'); put(".-..", 'L'); put("--", 'M'); put("-.", 'N'); put("---", 'O');
        put(".--.", 'P'); put("--.-", 'Q'); put(".-.", 'R'); put("...", 'S'); put("-", 'T');
        put("..-", 'U'); put("...-", 'V'); put(".--", 'W'); put("-..-", 'X'); put("-.--", 'Y');
        put("--..", 'Z'); put("..--", '_'); put("---.", '.'); put(".-.-", ','); put("----", '!');
    }};

    @Override
    public String encode(String message) {
        return null;
    }

    @Override
    public String decode(String message) {
        return null;
    }

}
