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

        div#wrapper {
            display: grid;
            width: fit-content;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }

        p#success {
            background-color: darkgreen;
            color: white;
            border-radius: 16px;
            text-align: center;
            padding: 10px;
            margin-bottom: 10px;
        }

        p#error {
            background-color: darkred;
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
    <h2>About RSA</h2>

    <div id="wrapper">
         <div>
            <h2>Generate RSA Keys</h2>
            <form action="RSAKeygenServlet" method="post">
                <label for="prime-p">Prime p:</label>
                <input type="text" id="prime-p" name="prime-p" placeholder="Prime p" required/><br/><br/>
                <label for="prime-q">Prime q:</label>
                <input type="text" id="prime-q" name="prime-q" placeholder="Prime q" required/><br/><br/>
                <label for="desired-e">Desired Public Key:</label>
                <input type="text" id="desired-e" name="desired-e" placeholder="Desired Public Key" required/><br/><br/>
                <input type="submit" name="rsa-keygen-submit" id="rsa-keygen-submit" value="Generate Keys"/>
            </form>
         </div>

        <div>
            <h2>Encode a Message</h2>
            <form action="RSAServlet" method="post">
                <label for="key">Recipient's Public Key:</label>
                <input type="text" id="key" name="key" placeholder="Recipient's Public Key" required/><br/><br/>
                <label for="n">Modulus:</label>
                <input type="text" id="n" name="n" placeholder="Modulus" required/><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="encipher"/>
                <input type="submit" name="rsa-submit" id="rsa-encode-submit" value="Encrypt Message"/>
            </form>
        </div>
        <div>
            <h2>Decode a Message</h2>
            <form action="RSAServlet" method="post">
                <label for="key">Private Key:</label>
                <input type="text" id="key" name="key" placeholder="Private Key" required/><br/><br/>
                <label for="n">Modulus:</label>
                <input type="text" id="n" name="n" placeholder="Modulus" required/><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="decipher"/>
                <input type="submit" name="rsa-submit" id="rsa-encode-submit" value="Decrypt Message"/>
            </form>
        </div>
    </div><br/><br/>

    <% if(request.getParameter("keygen-error") != null) {
        String error = request.getParameter("keygen-error"); %>
        <p id="error"><strong>Key Generation Error:</strong>&nbsp;
        <% switch(error) {
            case "p-not-prime": %>
                Entered value p is not prime
        <%      break;
            case "q-not-prime": %>
                Entered value q is not prime
        <%      break;
            case "inverse-error": %>
                Generated decryption key value is invalid
        <%      break;
            case "e-incorrect": %>
                Desired encryption key is not valid
        <%      break;
            } %>
        </p>
<%  } else if(request.getParameter("keygen") != null) {
        HttpSession s = request.getSession();
        long[] keypair = (long[]) s.getAttribute("rsa-keypair");
        s.invalidate();  %>
        <p id="success">Generated Public Keyset: <strong>(n = <%= keypair[0] %>, e = <%= keypair[1] %>)</strong><br/>Generated Private Key: <strong>d = <%= keypair[2] %></strong></p>
<% } %>
</div>

<hr/>

</body>
</html>