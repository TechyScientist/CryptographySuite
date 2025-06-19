package com.johnnyconsole.cryptographysuite.web.servlet.prng;

import com.johnnyconsole.cryptographysuite.ejb.interfaces.prng.BlumBlumShubStatelessLocal;
import com.johnnyconsole.cryptographysuite.ejb.objects.NumberSequence;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/BlumBlumShubServlet")
public class BlumBlumShubServlet extends HttpServlet {

    @EJB
    private BlumBlumShubStatelessLocal stateless;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("blum-blum-shub-submit") != null) {
            int bitLength = Integer.parseInt(request.getParameter("bitlength")),
                    p = Integer.parseInt(request.getParameter("p")),
                    q = Integer.parseInt(request.getParameter("q")),
                    seed = Integer.parseInt(request.getParameter("seed")),
                    length = Integer.parseInt(request.getParameter("length"));

            if(request.getParameter("sequence").equals("binary")) {
                String[] sequence = stateless.generateBinarySequence(bitLength, p, q, seed, length);
                if(sequence == null) {
                    response.sendRedirect("blum-blum-shub.jsp?error=" + stateless.error());
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("sequence", new NumberSequence(bitLength, p, q, seed, sequence));
                    response.sendRedirect("blum-blum-shub.jsp");
                }
            }
            else {
                int[] sequence = stateless.generateDecimalSequence(bitLength, p, q, seed, length);
                if(sequence == null) {
                    response.sendRedirect("blum-blum-shub.jsp?error=" + stateless.error());
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("sequence", new NumberSequence(bitLength, p, q, seed, sequence));
                    response.sendRedirect("blum-blum-shub.jsp");
                }
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}
