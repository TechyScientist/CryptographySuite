<%@ page import="com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString" %>
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
    <h2>About Binary String Simplified AES</h2>

    <h2>Encode or Decode a Message</h2>
    <form action="BinarySAESServlet" method="post">
        <label for="key">16-bit Key:</label>
        <input type="text" id="key" name="key" placeholder="Key" required><br/><br/>
        <label for="message" style="vertical-align: top;">Message:</label>
        <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
        <label for="encode-decode">Encipher or Decipher?</label>
        <select name="encode-decode" id="encode-decode" required>
            <option value="encrypt">Encipher</option>
            <option value="decrypt">Decipher</option>
        </select><br/><br/>
        <input type="submit" name="binary-saes-submit" id="binary-saes-submit" value="Encipher/Decipher"/>
    </form>
    <%
        if(request.getParameter("error") != null && request.getParameter("error").equals("key")) { %>
    <p id="error">Invalid key <strong><%= request.getParameter("key") %></strong>: Key length must be 16 (is <%= request.getParameter("key").length()%>).</p>
    <%  }
        if(request.getSession() != null && request.getSession().getAttribute("saes") != null) {
            EncodedString encodedString = (EncodedString) request.getSession().getAttribute("saes"); %>
    <p id="success">The <strong><%= encodedString.method %>ed</strong> message is: <strong><%= encodedString.string %></strong><br/>Using key: <strong><%= encodedString.key %></strong></p>
    <%      request.getSession().invalidate();
    } %>
</div>

<hr/>

</body>
</html>