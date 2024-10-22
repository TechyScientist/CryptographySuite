package com.johnnyconsole.cryptographysuite.web.servlet.substitution;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.substitution.SimpleSubstitutionStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SimpleSubstitutionServlet")
public class SimpleSubstitutionServlet extends HttpServlet {

    @EJB
    private SimpleSubstitutionStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("simplesub-submit") != null) {
            String alphabet = "",
                    message = request.getParameter("message").toUpperCase(),
                    encodeDecode = request.getParameter("encode-decode");

            for (int i = 0; i < 26; i++) {
                alphabet += request.getParameter("alphabet" + (char)(i + 'A'));
            }

            message = stateless.encipherDecipher(alphabet, message);

            HttpSession session = request.getSession();
            session.setAttribute("simplesub", new EncodedString(encodeDecode, message));
            response.sendRedirect("simplesub.jsp?alphabet=" + alphabet);
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }
}
