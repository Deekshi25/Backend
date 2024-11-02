<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Projects & Portfolio</title>
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
        
        .main {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .main .text-content {
            width: 50%;
            color: white;
        }

        .main h2 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }

        .main p {
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 1.5rem;
        }

        .main .btn {
            background-color: #f0a500;
            color: #003366;
            padding: 1rem 2rem;
            border: none;
            border-radius: 5px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .main .btn:hover {
            background-color: #ffc107;
        }

        .main .illustration {
            width: 40%;
        }

        .main .illustration img {
            width: 100%;
            border-radius: 10px;
        }

        /* Footer Section */
        footer {
            background-color: #00284d;
            padding: 2rem;
            color: white;
            text-align: center;
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

    <!-- Main Content Section -->
    <div class="main">
        <!-- Text Section -->
        <div class="text-content">
            <h2>A Goal Without a Plan is Just a Wish</h2>
            <p>EduSupport is a Portfolio and Project Management System that enhances the academic experience of students by enabling them to showcase their projects and portfolios.</p>
            <button class="btn">Get Started</button>
        </div>

        <!-- Illustration Section -->
        <div class="illustration">
            <img src="illustration.jpg" alt="Portfolio Illustration">
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
