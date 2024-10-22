package com.johnnyconsole.cryptographysuite.web.servlet.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.AtbashStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AtbashServlet")
public class AtbashServlet extends HttpServlet {

    @EJB
    private AtbashStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("atbash-submit") != null) {
            String message = request.getParameter("message").toUpperCase(),
                    encodeDecode = request.getParameter("encode-decode");

            message = stateless.encipherDecipher(message);

            HttpSession session = request.getSession();
            session.setAttribute("atbash", new EncodedString(encodeDecode, message));
            response.sendRedirect("atbash.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
