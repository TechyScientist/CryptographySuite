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
            grid-template-columns: repeat(3, auto);
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
    <h2>About the Knapsack Cryptosystem</h2>

    <div id="wrapper">
         <div>
            <h2>Generate Knapsack Keys</h2>
            <form action="KnapsackKeygenServlet" method="post">
                <label for="superincreasing-knapsack"style="vertical-align: top;">Superincreasing Weight Set (one per line):</label><br/>
                <textarea name="superincreasing-knapsack" id="superincreasing-knapsack" placeholder="Superincreasing Weights" required style="width: 250px; height: 125px; resize: none; vertical-align: top;"></textarea><br/><br/>
                <label for="multiplier">Multiplier:</label>
                <input type="text" id="multiplier" name="multiplier" placeholder="Multiplier" required/><br/><br/>
                <label for="modulus">Modulus:</label>
                <input type="text" id="modulus" name="modulus" placeholder="Modulus" required/><br/><br/>
                <input type="submit" name="knapsack-keygen-submit" id="knapsack-keygen-submit" value="Generate Keys"/>
            </form>
         </div>

        <div>
            <h2>Encode a Message</h2>
            <form action="KnapsackServlet" method="post">
                <label for="key" style="vertical-align: top;">Recipient's Public Key:</label>
                <textarea id="key" name="key" placeholder="Recipient's Public Key" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="encipher"/>
                <input type="submit" name="knasack-submit" id="knapsack-encode-submit" value="Encrypt Message"/>
            </form>
        </div>
        <div>
            <h2>Decode a Message</h2>
            <form action="RSAServlet" method="post">
                <label for="key" style="vertical-align: top;">Private Key:</label>
                <textarea id="key" name="key" placeholder="Private Key" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="decipher"/>
                <input type="submit" name="knapsack-submit" id="knapsack-encode-submit" value="Decrypt Message"/>
            </form>
        </div>
    </div><br/><br/>
</div>

<hr/>

</body>
</html>