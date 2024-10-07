package com.johnnyconsole.cryptographysuite.web.servlet.encoding;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.encoding.MorseStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.MorseString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MorseServlet")
public class MorseServlet extends HttpServlet {

    @EJB
    private MorseStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("morse-submit") != null) {
            String message = request.getParameter("message"),
                    encodeDecode = request.getParameter("encode-decode");

            if(encodeDecode.equals("encode")) {
                message = stateless.encode(message);
            }
            else {
                message = stateless.decode(message);
            }

            HttpSession session = request.getSession();
            session.setAttribute("morse", new MorseString(encodeDecode, message));
            response.sendRedirect("morse.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
