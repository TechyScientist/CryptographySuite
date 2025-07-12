<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cryptography Suite Web App</title>
    <link rel="stylesheet" href="assets/style/main.css" />
    <style>
        ul {
            margin-left: 60px;
        }

        ul li {
            margin-bottom: 10px;
        }

        div#algorithms {
            margin-top: 10px;
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
            <li><a href="blumblumshub.jsp">Blum-Blum-Shub PRSG</a></li>
            <li><a href="morse.jsp">Morse Encoding</a></li>
            <li><a href="simplesub.jsp">Simple Substitution Cipher</a></li>
            <li><a href="atbash.jsp">Atbash Cipher</a></li>
            <li><a href="vigenere.jsp">Vigen&egrave;re Cipher</a></li>
            <li><a href="keyed-vigenere.jsp">Keyed Vigen&egrave;re Cipher</a></li>
        </ul>
        <ul>
            <li><a href="binary-sdes.jsp">Binary String Simplified DES</a></li>
            <li><a href="binary-saes.jsp">Binary String Simplified AES</a></li>
            <li><a href="knapsack.jsp">Knapsack Cryptosystem</a></li>
        </ul>
    </div>
</div>

<hr/>

</body>
</html>