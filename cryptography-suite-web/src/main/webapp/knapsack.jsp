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

        input[type="number"], select {
            margin-right: 10px;
        }

        input.weight {
            width: 5em;
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
         <div>
            <h2>Generate Knapsack Keys</h2>
            <form action="KnapsackKeygenServlet" method="post">
                <label for="weight1">Superincreasing Weight Set:</label>
                <input type="number" name="weight1" id="weight1" class="weight" placeholder="Weight 1" required/>
                <input type="number" name="weight2" id="weight2" class="weight" placeholder="Weight 2" required/>
                <input type="number" name="weight3" id="weight3" class="weight" placeholder="Weight 3" required/>
                <input type="number" name="weight4" id="weight4" class="weight" placeholder="Weight 4" required/>
                <input type="number" name="weight5" id="weight5" class="weight" placeholder="Weight 5" required/>
                <input type="number" name="weight6" id="weight6" class="weight" placeholder="Weight 6" required/>
                <input type="number" name="weight7" id="weight7" class="weight" placeholder="Weight 7" required/>
                <input type="number" name="weight8" id="weight8" class="weight" placeholder="Weight 8" required/><br/><br/>
                <label for="multiplier">Multiplier:</label>
                <input type="number" id="multiplier" name="multiplier" placeholder="Multiplier" required/><br/><br/>
                <label for="modulus">Modulus:</label>
                <input type="number" id="modulus" name="modulus" placeholder="Modulus" required/><br/><br/>
                <input type="submit" name="knapsack-keygen-submit" id="knapsack-keygen-submit" value="Generate Keys"/>
            </form>
         </div>

        <div>
            <h2>Encode a Message</h2>
            <form action="KnapsackServlet" method="post">
                <label for="weight1">Receiver's Public Key Set:</label>
                <input type="number" name="weight1" id="weight1" class="weight" placeholder="Weight 1" required/>
                <input type="number" name="weight2" id="weight2" class="weight" placeholder="Weight 2" required/>
                <input type="number" name="weight3" id="weight3" class="weight" placeholder="Weight 3" required/>
                <input type="number" name="weight4" id="weight4" class="weight" placeholder="Weight 4" required/>
                <input type="number" name="weight5" id="weight5" class="weight" placeholder="Weight 5" required/>
                <input type="number" name="weight6" id="weight6" class="weight" placeholder="Weight 6" required/>
                <input type="number" name="weight7" id="weight7" class="weight" placeholder="Weight 7" required/>
                <input type="number" name="weight8" id="weight8" class="weight" placeholder="Weight 8" required/><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="encipher"/>
                <input type="submit" name="knasack-submit" id="knapsack-submit" value="Encrypt Message"/>
            </form>
        </div>
        <div>
            <h2>Decode a Message</h2>
            <form action="RSAServlet" method="post">
                <label for="weight1">Private Key Set:</label>
                <input type="number" name="weight1" id="weight1" class="weight" required/>
                <input type="number" name="weight2" id="weight2" class="weight" required/>
                <input type="number" name="weight3" id="weight3" class="weight" required/>
                <input type="number" name="weight4" id="weight4" class="weight" required/>
                <input type="number" name="weight5" id="weight5" class="weight" required/>
                <input type="number" name="weight6" id="weight6" class="weight" required/>
                <input type="number" name="weight7" id="weight7" class="weight" required/>
                <input type="number" name="weight8" id="weight8" class="weight" required/><br/><br/>
                <label for="message" style="vertical-align: top;">Message:</label>
                <textarea name="message" id="message" placeholder="Message" required style="width: 250px; height: 125px; resize: none;"></textarea><br/><br/>
                <input type="hidden" name="encode-decode" id="encode-decode" value="decipher"/>
                <input type="submit" name="knapsack-submit" id="knapsack-submit" value="Decrypt Message"/>
            </form>
        </div>
    </div><br/><br/>

<hr/>

</body>
</html>