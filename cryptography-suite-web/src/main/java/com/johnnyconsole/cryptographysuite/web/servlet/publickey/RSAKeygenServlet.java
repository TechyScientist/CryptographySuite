package com.johnnyconsole.cryptographysuite.web.servlet.publickey;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.publickey.RSAStatelessLocal;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RSAKeygenServlet")
public class RSAKeygenServlet extends HttpServlet {

    @EJB
    RSAStatelessLocal stateless;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("rsa-keygen-submit") != null) {
            long p = Long.parseLong(request.getParameter("prime-p")),
                    q = Long.parseLong(request.getParameter("prime-q")),
                    e = Long.parseLong(request.getParameter("desired-e"));

            long[] keypair = stateless.generateKeypair(p, q, e);
            if(keypair != null) {
                HttpSession session = request.getSession();
                session.setAttribute("rsa-keypair", keypair);
                response.sendRedirect("rsa.jsp?keygen=success");
            }
            else {
                response.sendRedirect("rsa.jsp?keygen-error=" + stateless.error());
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
