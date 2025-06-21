<%@ page import="com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cryptography Suite Web App</title>
    <link rel="stylesheet" href="assets/style/main.css"/>
</head>
<body>
<div id="header">
    <h1>Cryptography Suite Web App</h1>
</div>
<div id="body">
    <a href="index.jsp">Return to Home</a>
    <h2>About the Atbash Encoding</h2>

    <h2>Encode or Decode a Message</h2>
    <form action="AtbashServlet" method="post">
        <label for="message" style="vertical-align: top;">Message:</label>
        <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
        <label for="encode-decode">Encipher or Decipher?</label>
        <select name="encode-decode" id="encode-decode" required>
            <option value="encode">Encipher</option>
            <option value="decode">Decipher</option>
        </select><br/><br/>
        <input type="submit" name="atbash-submit" id="atbash-submit" value="Encipher/Decipher"/>
    </form>

    <% if(request.getSession() != null && request.getSession().getAttribute("atbash") != null) {
            EncodedString encodedString = (EncodedString) request.getSession().getAttribute("atbash"); %>
            <p id="success">The <strong><%= encodedString.method %>ed</strong> message is:<br/><strong><%= encodedString.string %></strong></p>
    <%      request.getSession().invalidate();
        } %>
</div>

<hr/>

</body>
</html>