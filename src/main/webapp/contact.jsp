
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Contact Us</title>
    
    <style>
        /* Apply same CSS as other pages */

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

        /* Main Section for Contact Form */
        .contact-container {
            max-width: 900px;
            margin: 3rem auto;
            background-color: #00284d;
            padding: 2rem;
            border-radius: 10px;
        }

        .contact-container h2 {
            text-align: center;
            font-size: 2.5rem;
            margin-bottom: 2rem;
        }

        .contact-container form {
            display: flex;
            flex-direction: column;
            gap: 1.5rem;
        }

        .contact-container label {
            font-size: 1.2rem;
        }

        .contact-container input, .contact-container textarea {
            padding: 1rem;
            font-size: 1.1rem;
            border-radius: 5px;
            border: none;
            width: 100%;
        }

        .contact-container textarea {
            resize: none;
            height: 150px;
        }

        .contact-container .btn-submit {
            background-color: #f0a500;
            color: #003366;
            padding: 1rem 2rem;
            border: none;
            border-radius: 5px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .contact-container .btn-submit:hover {
            background-color: #ffc107;
        }

        /* Footer Section */
        footer {
            background-color: #00284d;
            padding: 2rem;
            color: white;
            text-align: center;
            margin-top: 2rem;
        }

    </style>
</head>
<body>

    <!-- Header Section -->
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
    <!-- Main Content Section (Contact Form) -->
    <div class="contact-container">
        <h2>Contact Us</h2>
        <form action="#" method="post">
            <label for="name">Your Name</label>
            <input type="text" id="name" name="name" placeholder="Enter your name" required>

            <label for="email">Your Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>

            <label for="message">Your Message</label>
            <textarea id="message" name="message" placeholder="Enter your message" required></textarea>

            <button type="submit" class="btn-submit">Send Message</button>
        </form>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
