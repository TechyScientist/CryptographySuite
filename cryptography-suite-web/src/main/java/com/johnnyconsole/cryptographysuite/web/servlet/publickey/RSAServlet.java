package com.johnnyconsole.cryptographysuite.web.servlet.publickey;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey.RSAStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RSAServlet")
public class RSAServlet extends HttpServlet {

    @EJB
    private RSAStatelessLocal stateless;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("rsa-encode-decode-submit") != null) {
            String message = request.getParameter("message"),
                    encodeDecode = request.getParameter("encode-decode");
            long key = Long.parseLong(request.getParameter("key")),
                    modulus = Long.parseLong(request.getParameter("n"));

                request.getSession().setAttribute("rsa", new EncodedString(encodeDecode, stateless.encrypt(message, key, modulus)));
                response.sendRedirect("rsa.jsp?encrypt=true");

        } else {
                response.sendRedirect("rsa.jsp");
        }
    }

}
