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

        p {
            margin-bottom: 10px;
            margin-left: 20px;
        }

        a {
            display: block;
            width: fit-content;
            color: black;
            font-size: 18px;
        }

        ul {
            margin-left: 60px;
        }

        ul li {
            margin-bottom: 10px;
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

        div#algorithms {
            display: grid;
            width: fit-content;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }

    </style>
</head>
<body>
<div id="header">
    <h1>Cryptography Suite Web App</h1>
</div>
<div id="body">
    <h2>Algorithm Selection</h2>
    <p>The suite currently has support for the following algorithms:</p>
    <div id="algorithms">
        <ul>
            <li><a href="morse.jsp">Morse Encoding</a></li>
            <li><a href="ohaver.jsp">Ohaver Encoding</a></li>
            <li><a href="simplesub.jsp">Simple Substitution Cipher</a></li>
            <li><a href="atbash.jsp">Atbash Cipher</a></li>
            <li><a href="vigenere.jsp">Vigen&egrave;re Cipher</a></li>
            <li><a href="keyed-vigenere.jsp">Keyed Vigem&egrave;re Cipher</a></li>
        </ul>
        <ul>
            <li><a href="binary-sdes.jsp">Binary String Simplified DES</a></li>
            <li><a href="binary-saes.jsp">Binary String Simplified AES</a></li>
            <li><a href="rsa.jsp">RSA</a></li>
        </ul>
    </div>
</div>

<hr/>

</body>
</html>