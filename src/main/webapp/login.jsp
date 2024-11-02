<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Student Login</title>
    <link rel="stylesheet" href="style.css">
    <style>
        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #003366;
            color: #fff;
        }

        header {
            background-color: #003366;
            color: #fff;
            padding: 2rem;
            text-align: center;
        }

        header h1 {
            font-size: 3rem;
            margin: 0;
        }

        /* Navigation Bar Styles */
        
        nav ul {
            list-style: none;
            padding: 0;
            display: flex;
            justify-content: center;
            margin: 1rem 0;
        }

        nav ul li {
            margin: 0 1.5rem;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 1.2rem;
            transition: color 0.3s;
            padding: 0.5rem 1rem;
            border-radius: 5px;
        }

        nav ul li a:hover,
        nav ul li a.active {
            background-color: #fff;
            color: #003366;
        }

            <style>
    .form-container {

   	 max-width: 440px; /* Max width of the form */
   	 margin: auto;
   	 padding: 20px;
     background-color: white;
     border-radius: 8px;
     box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
	input[type=text], input[type=password], input[type=number], input[type=email], select, input[type=date], textarea {
    width: calc(100% - 10px); /* Adjust width */
    max-width: 440px; /* Limiting the maximum width */
    padding: 10px;
    margin: 8px 0;
    display: block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
  }
  .button {
    background-color: black;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 10px 40px;
    cursor: pointer;
  }
  label {
    padding: 12px 0;
    display: block;
    font-weight: bold;
  }
  .radio-label {
    padding: 0;
    display: inline;
    font-weight: normal;
}

  h3 {
          text-align: center;
          text-decoration: underline;
          font-size: 24px;
          color: #333;
          margin-top: 30px;
          margin-bottom: 20px;
  }

footer{
            background-color: #00284d;
            padding: 2rem;
            color: white;
            text-align: center;
            margin-top: 2rem;
        }

    </style>
</head>
<body>
  <header>
        <h1>EduSupport</h1>
    </header>

    <!-- Navigation Section -->
    <nav>
        <ul>
            <li><a href="/">Home</a></li>    
            <li><a href="login">Login</a></li>
            <li><a href="about">About Us</a></li>
            <li><a href="contact">Contact Us</a></li>
        </ul>
    </nav>
<br/>
<br/>
<center>
<div class="form-container">
<form method="post" action="checkstudentlogin">
<table>
<tr>
<td> <label for="email">Enter Email :</label> </td>
<td> <input type="email" name="email" id="email" required/> </td>
</tr>
<tr>
<td> <label for="password">Enter Password</label> </td>
<td> <input type="password" name="password" id="password" required/> </td>
</tr>
<tr>
<td colspan="2">
<input class="button" type="submit" value="Login"/>
<input class="button" type="reset" value="Clear"/></td>
</tr>
</table>
</form>
</div>
</center>
</body>
    </html>
