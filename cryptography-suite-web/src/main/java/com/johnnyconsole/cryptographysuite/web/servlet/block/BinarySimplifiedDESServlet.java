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

@WebServlet("/BinarySDESServlet")
public class BinarySimplifiedDESServlet extends HttpServlet {

    @EJB
    private SimplifiedDESStatelessLocal stateless;
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
                if(mode.equals("ECB")) {
                    if (encodeDecode.equals("encipher")) {
                        result = stateless.ecb_encrypt(message, key);
                    } else {
                        result = stateless.ecb_decrypt(message, key);
                    }
                } else {
                    iv = request.getParameter("iv");

                    if (encodeDecode.equals("encipher")) {
                        result = stateless.cbc_encrypt(message, iv, key);
                    } else {
                        result = stateless.cbc_decrypt(message, iv, key);
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
