<%@ page import="com.johnnyconsole.cryptographysuite.ejb.objects.EncodedString" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cryptography Suite Web App</title>

    <style>
        :root {
            --color-primary: #B3282D;
            --color-background: #F0D4D5;
        }

        * {
            margin: 0;
            padding: 0;
            font-family: "Calibri Light", sans-serif;
        }

        body {
            margin: 20px 200px;
            background: var(--color-background)
        }

        div#body {
            padding: 30px;
        }

        h2 {
            margin-bottom: 10px;
        }

        h3 {
            font-size: 20px;
            margin-bottom: 10px;
        }

        a {
            display: block;
            width: fit-content;
            color: black;
            margin-bottom: 10px;
        }

        form {
            margin-left: 30px;
            margin-bottom: 25px;
        }

        label {
            margin-right: 10px;
        }

        input, select, textarea {
            padding: 5px
        }

        input[type="text"], select {
            margin-right: 10px;
        }

        div#header {
            background: var(--color-primary);
            padding: 20px;
            color: white;
            border-radius: 16px;
        }

        hr {
            height: 20px;
            background-color: var(--color-primary);
            border-radius: 16px;
        }

        p#success {
            background-color: darkgreen;
            color: white;
            border-radius: 16px;
            text-align: center;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
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