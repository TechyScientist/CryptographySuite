package com.johnnyconsole.cryptographysuite.web.servlet.block;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.block.SimplifiedAESStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BinarySAESServlet")
public class BinarySimplifiedAESServlet extends HttpServlet {

    @EJB
    private SimplifiedAESStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("binary-saes-submit") != null) {
            String message = request.getParameter("message"),
                    key = request.getParameter("key"),
                    encodeDecode = request.getParameter("encode-decode"),
                    result = "";

            if (key.length() != 16) {
                response.sendRedirect("binary-saes.jsp?error=key&key=" + key);
            } else {

                while (message.length() % 16 != 0) {
                    message += '\0';
                }

                if (encodeDecode.equals("encrypt")) {
                    for(int i = 0; i < message.length(); i += 168) {
                        result += stateless.encrypt(message.substring(i, i + 16), key);
                    }
                }
                else {
                    for (int i = 0; i < message.length(); i += 16) {
                        result += stateless.decrypt(message.substring(i, i + 16), key);
                    }
                }
                HttpSession session = request.getSession();
                session.setAttribute("saes", new EncodedString(encodeDecode, result, key));
                response.sendRedirect("binary-saes.jsp");
            }
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
