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
    <h2>About Binary String Simplified DES</h2>

    <h2>Encode or Decode a Message</h2>
    <form action="BinarySDESServlet" method="post">
        <label for="key">10-bit Key:</label>
        <input type="text" id="key" name="key" placeholder="Key" required/><br/><br/>
        <label for="mode">Cipher Mode:</label>
        <select name="mode" id="mode" required>
            <option value="ECB">Electronic Codebook (ECB)</option>
            <option value="CBC">Cipher Block Chaining (CBC)</option>
        </select><br/><br/>
        <div id="cbc" style="display: none;">
            <label for="iv">8-bit Initialization Vector:</label>
            <input name="iv" id="iv" placeholder="Initialization Vector"/><br/><br/>
        </div>
        <label for="message" style="vertical-align: top;">Message:</label>
        <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
        <label for="encode-decode">Encipher or Decipher?</label>
        <select name="encode-decode" id="encode-decode" required>
            <option value="encipher">Encipher</option>
            <option value="decipher">Decipher</option>
        </select><br/><br/>
        <input type="submit" name="binary-sdes-submit" id="binary-sdes-submit" value="Encipher/Decipher"/>
    </form>

    <%
        if(request.getParameter("error") != null && request.getParameter("error").equals("key")) { %>
    <p id="error">Invalid key <strong><%= request.getParameter("key") %></strong>: Key length must be 10 (is <%= request.getParameter("key").length()%>).</p>
    <%  }
        if(request.getSession() != null && request.getSession().getAttribute("sdes") != null) {
        EncodedString encodedString = (EncodedString) request.getSession().getAttribute("sdes"); %>
    <p id="success">The <strong><%= encodedString.mode + " mode " + encodedString.method %>ed</strong> message is: <strong><%= encodedString.string %></strong><br/>Using key: <strong><%= encodedString.key %></strong><% if(encodedString.mode.equals("CBC")) {%><br/>and initialization vector: <strong><%= encodedString.iv %></strong><%}%></p>
    <%      request.getSession().invalidate();
    } %>
</div>

<hr/>

</body>

<script>
    document.getElementById("mode").onchange = (event) => {
        if(document.getElementById("cbc").style.display === "none") {
            document.getElementById("cbc").style.display = "block";
            document.getElementById("iv").required = true;
        }
        else {
            document.getElementById("cbc").style.display = "none";
            document.getElementById("iv").required = false;
        }
    }
</script>
</html>