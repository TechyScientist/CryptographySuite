package com.johnnyconsole.cryptographysuite.web.servlet.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.KeyedVigenereStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.VigenereString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/KeyedVigenereServlet")
public class KeyedVigenereServlet extends HttpServlet {

    @EJB
    private KeyedVigenereStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("vigenere-submit") != null) {
            String alphabet = request.getParameter("alphabet").toUpperCase(),
                    alphabetkey = request.getParameter("alphabetkeyword").toUpperCase(),
                    keyword = request.getParameter("cipherkeyword").toUpperCase(),
                    message = request.getParameter("message").toUpperCase(),
                    encodeDecode = request.getParameter("encode-decode");

            if(encodeDecode.equals("encipher")) {
                message = stateless.encipher(alphabet, alphabetkey, message, keyword);
            }
            else {
                message = stateless.decipher(alphabet, alphabetkey, message, keyword);
            }

            HttpSession session = request.getSession();
            session.setAttribute("vigenere", new VigenereString(alphabet, alphabetkey, keyword, encodeDecode, message));
            response.sendRedirect("keyed-vigenere.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
