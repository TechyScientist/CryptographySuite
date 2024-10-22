package com.johnnyconsole.cryptographysuite.web.servlet.encoding;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding.OhaverStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OhaverServlet")
public class OhaverServlet extends HttpServlet {

    @EJB
    private OhaverStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("ohaver-submit") != null) {
            String message = request.getParameter("message").toUpperCase().replace(" ", "_"),
                    encodeDecode = request.getParameter("encode-decode");

            message = stateless.encodeDecode(message);

            HttpSession session = request.getSession();
            session.setAttribute("ohaver", new EncodedString(encodeDecode, message));
            response.sendRedirect("ohaver.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
