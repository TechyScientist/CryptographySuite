package com.johnnyconsole.cryptographysuite.web.servlet.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding.MorseStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.VigenereStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.MorseString;
import com.johnnyconsole.cryptographysuite.ejb.objects.VigenereString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/VigenereServlet")
public class VigenereServlet extends HttpServlet {

    @EJB
    private VigenereStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("vigenere-submit") != null) {
            String keyword = request.getParameter("keyword"),
                    message = request.getParameter("message"),
                    encodeDecode = request.getParameter("encode-decode");

            if(encodeDecode.equals("encipher")) {
                message = stateless.encipher(message, keyword);
            }
            else {
                message = stateless.decipher(message, keyword);
            }

            HttpSession session = request.getSession();
            session.setAttribute("vigenere", new VigenereString(encodeDecode, keyword, message));
            response.sendRedirect("vigenere.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
