<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Student Home</title>
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

        /* Main Section */
        .main {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 2rem;
        }

        .main h2 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }

        .project-card {
            background-color: #004080;
            border-radius: 10px;
            padding: 2rem;
            margin: 1rem;
            width: 300px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s;
        }

        .project-card:hover {
            transform: scale(1.05);
        }

        .project-card h3 {
            font-size: 1.5rem;
            margin: 0 0 1rem 0;
        }

        .project-card p {
            font-size: 1rem;
            line-height: 1.4;
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
        <h1>EduSupport - Student Home</h1>
    </header>

    <!-- Navigation Section -->
    <nav>
        <ul>
            <li><a href="studenthome">Home</a></li>
            <li><a href="profile">Profile</a></li>
            <li><a href="projects">Projects</a></li>
            <li><a href="portfolio">Portfolio</a></li>
            <li><a href="stulogout">Logout</a></li>
        </ul>
    </nav>

    <!-- Main Content Section -->
    <div class="main">
        <h2>Welcome, ABHIJITH!</h2>
        <p>Explore your projects and showcase your portfolio!</p>

        <div class="project-card">
            <h3>Project Title 1</h3>
            <p>This project focuses on [brief description].</p>
            <button class="btn">View Project</button>
        </div>

        <div class="project-card">
            <h3>Project Title 2</h3>
            <p>This project involves [brief description].</p>
            <button class="btn">View Project</button>
        </div>

        <div class="project-card">
            <h3>Project Title 3</h3>
            <p>This project covers [brief description].</p>
            <button class="btn">View Project</button>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
