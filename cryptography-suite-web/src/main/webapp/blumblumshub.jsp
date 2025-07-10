<%@ page import="com.johnnyconsole.cryptographysuite.ejb.objects.NumberSequence" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cryptography Suite Web App</title>
    <link rel="stylesheet" href="assets/style/main.css" />
</head>
<body>
<div id="header">
    <h1>Cryptography Suite Web App</h1>
</div>
<div id="body">
    <a href="index.jsp">Return to Home</a>
    <h2>About the Blum-Blum-Shub Algorithm</h2>

    <h2>Encode or Decode a Message</h2>
    <form action="BlumBlumShubServlet" method="post">
       <label for="sequence">Binary or Decimal?</label>
        <select name="sequence" id="sequence">
            <option value="binary">Binary</option>
            <option value="decimal" selected>Decimal</option>
        </select><br/><br/>
        <label for="bitlength">Bit Length:</label>
        <select name="bitlength" id="bitlength">
            <option value="1">BIT (1 bit)</option>
            <option value="8" selected>BYTE (8 bits)</option>
            <option value="16">WORD (16 bits)</option>
            <option value="32">DWORD (32 bits)</option>
        </select><br/><br/>
        <label for="p">Prime value P:</label>
        <input type="number" name="p" id="p" placeholder="Prime P" required /><br/><br/>
        <label for="q">Prime value Q:</label>
        <input type="number" name="q" id="q" placeholder="Prime Q" required /><br/><br/>
        <label for="seed">Seed Value:</label>
        <input type="number" name="seed" id="seed" placeholder="Seed" required /><br/><br/>
        <label for="length">Sequence Length:</label>
        <input type="number" name="length" id="length" placeholder="Sequence Length" required /><br/><br/>
        <input type="submit" name="blum-blum-shub-submit" id="blum-blum-shub-submit" value="Generate Sequence" />
    </form>

        <% if(request.getParameter("error") != null) { %>
            <p id="error">Error: <%= request.getParameter("error") %></p>
       <% }
            else if(request.getSession() != null && request.getSession().getAttribute("sequence") != null) {
            NumberSequence sequence = (NumberSequence) request.getSession().getAttribute("sequence");
            if(sequence.base.equals("Binary")) {%>
                <p id="success">The <strong><%= sequence.bitLength %>-bit <%= sequence.base.toLowerCase() %> </strong> sequence with p = <strong><%= sequence.p %></strong>, q = <strong><%= sequence.q %></strong>, seed = <strong><%= sequence.seed %></strong> is: <br/> <strong><%= Arrays.toString(sequence.strSequence)%></strong></p>
    <%      } else { %>
                <p id="success">The <strong><%= sequence.bitLength %>-bit <%= sequence.base.toLowerCase() %> </strong> sequence with p = <strong><%= sequence.p %></strong>, q = <strong><%= sequence.q %></strong>, seed = <strong><%= sequence.seed %></strong> is: <br/> <strong><%= Arrays.toString(sequence.intSequence)%></strong></p>
    <%       }
             request.getSession().invalidate();
        } %>
</div>

<hr/>

</body>
</html>