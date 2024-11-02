
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>About Us - EduSupport</title>

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
            padding: 1.5rem;
            text-align: center;
            border-bottom: 2px solid #f0a500;
        }

        header h1 {
            font-size: 2.5rem;
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

        /* About Us Section */
        .about-section {
            text-align: center;
            padding: 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .about-section h2 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
            font-weight: 600;
            color: #f0a500;
        }

        .about-section p.subtitle {
            font-size: 1.2rem;
            margin-bottom: 2rem;
            color: #ccc;
            font-style: italic;
        }

        .about-description {
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 2rem;
            max-width: 800px;
            margin: 0 auto;
        }

        /* Image Section */
        .image-container {
            display: flex;
            justify-content: center;
            gap: 1.5rem;
            margin-bottom: 2rem;
        }

        .image-container img {
            width: 250px;
            height: 150px;
            object-fit: cover;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        }

        /* Buttons Section */
        .button-container {
            display: flex;
            justify-content: center;
            gap: 2rem;
        }

        .button-container .btn {
            background-color: #333;
            color: #fff;
            padding: 1rem 2rem;
            border: none;
            border-radius: 5px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .button-container .btn:hover {
            background-color: #f0a500;
        }

        .footer-description {
            text-align: center;
            font-size: 1rem;
            margin-top: 2rem;
            color: #ccc;
        }

        footer {
            background-color: #00284d;
            padding: 1.5rem;
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

    <!-- About Section -->
    <section class="about-section">
        <h2>ABOUT US</h2>
        <p class="subtitle">Plans are only good intentions unless they immediately degenerate into hard work</p>

        <p class="about-description">
            EduSupport is a Portfolio and Project Management System which will enhance the academic experience of students by enabling them to showcase their projects and portfolios. 
            The platform allows students to upload projects, including detailed descriptions and media. This makes it easier for students to present their work and achievements in an organized manner.
        </p>

        <!-- Image Section -->
        <div class="image-container">
            <img src="office1.jpg" alt="Office Image 1">
            <img src="office2.jpg" alt="Office Image 2">
        </div>

        <!-- Button Section -->
        <div class="button-container">
            <button class="btn">PRESENTATION</button>
            <button class="btn">FEEDBACK</button>
            <button class="btn">MILESTONES</button>
        </div>

        <p class="footer-description">
            The above features make our website an ideal choice for Students and Teachers in need of a Project and Portfolio Management System.
        </p>
    </section>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
