package com.johnnyconsole.cryptographysuite.web.servlet.block;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.block.SimplifiedDESStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BinarySDESServlet")
public class BinarySimplifiedDESServlet extends HttpServlet {

    @EJB
    private SimplifiedDESStatelessLocal stateless;

    private ArrayList<String> plaintext = new ArrayList<>(),
                        ciphertext = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("binary-sdes-submit") != null) {
            String message = request.getParameter("message"),
                    key = request.getParameter("key"),
                    encodeDecode = request.getParameter("encode-decode"),
                    mode=request.getParameter("mode"),
                    iv = "",
                    result = "";

            if (key.length() != 10) {
                response.sendRedirect("binary-sdes.jsp?error=key&key=" + key);
            } else {

                while (message.length() % 8 != 0) {
                    message += '\0';
                }

                if(mode.equals("ECB")) {
                    if (encodeDecode.equals("encipher")) {
                        for (int i = 0; i < message.length(); i += 8) {
                            result += stateless.ecb_encrypt(message.substring(i, i + 8), key);
                        }
                    } else {
                        for (int i = 0; i < message.length(); i += 8) {
                            result += stateless.ecb_decrypt(message.substring(i, i + 8), key);
                        }
                    }
                } else {
                    iv = request.getParameter("iv");

                    if (encodeDecode.equals("encipher")) {
                        for (int i = 0, block = 0; i < message.length(); i += 8, block++) {
                            ciphertext.add(stateless.cbc_encrypt(message.substring(i, i + 8), i == 0 ? iv : ciphertext.get(block - 1), key));
                            result += ciphertext.get(block);
                        }
                    } else {
                        for (int i = 0, block = 0; i < message.length(); i += 8, block++) {
                            ciphertext.add(message.substring(i, i + 8));
                            result += stateless.cbc_decrypt(message.substring(i, i + 8), i == 0 ? iv : ciphertext.get(block - 1), key);
                        }
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("sdes", new EncodedString(encodeDecode, result, key, mode, iv));
                response.sendRedirect("binary-sdes.jsp");
            }
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
