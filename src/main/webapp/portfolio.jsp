<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Portfolio</title>
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

        .portfolio-card {
            background-color: #004080;
            border-radius: 10px;
            padding: 2rem;
            margin: 1rem;
            width: 350px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s;
        }

        .portfolio-card:hover {
            transform: scale(1.05);
        }

        .portfolio-card h2 {
            font-size: 1.8rem;
            margin-bottom: 1rem;
            color: #f0a500;
        }

        .portfolio-card p {
            font-size: 1rem;
            line-height: 1.4;
        }

        .portfolio-card .tags {
            margin-top: 1rem;
        }

        .portfolio-card .tags span {
            background-color: #f0a500;
            color: #003366;
            padding: 0.3rem 0.8rem;
            border-radius: 3px;
            margin-right: 0.5rem;
            font-size: 0.9rem;
        }

        .btn {
            background-color: #f0a500;
            color: #003366;
            padding: 1rem 2rem;
            border: none;
            border-radius: 5px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #ffc107;
        }

        /* Footer Section */
        footer {
            background-color: #00284d;
            padding: 2rem;
            color: white;
            text-align: center;
        }

        .upload-section {
            margin-top: 2rem;
        }

        .upload-section input[type="file"] {
            padding: 0.5rem;
            background-color: #f0a500;
            color: #003366;
            border: none;
            border-radius: 5px;
        }

        .upload-section input[type="submit"] {
            padding: 0.7rem 1.5rem;
            margin-top: 1rem;
            background-color: #f0a500;
            color: #003366;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .upload-section input[type="submit"]:hover {
            background-color: #ffc107;
        }
    </style>
</head>
<body>

    <!-- Header Section -->
    <header>
        <h1>EduSupport - Portfolio</h1>
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

        <!-- Portfolio Cards -->
        <div class="portfolio-card">
            <h2>Web Development Portfolio</h2>
            <p>A collection of web development projects showcasing skills in HTML, CSS, and JavaScript.</p>
            <div class="tags">
                <span>HTML</span><span>CSS</span><span>JavaScript</span>
            </div>
        </div>

        <div class="portfolio-card">
            <h2>Data Science Projects</h2>
            <p>Analysis and visualization projects using Python and various libraries like Pandas and Matplotlib.</p>
            <div class="tags">
                <span>Python</span><span>Data Analysis</span><span>Visualization</span>
            </div>
        </div>

        <div class="portfolio-card">
            <h2>Mobile App Development</h2>
            <p>Apps created using Flutter showcasing UI/UX design and functionality.</p>
            <div class="tags">
                <span>Flutter</span><span>Mobile Development</span><span>UI/UX</span>
            </div>
        </div>

        <!-- Upload Portfolio Section -->
        <div class="upload-section">
            <h2>Upload Your Portfolio</h2>
            <form action="upload_portfolio.php" method="post" enctype="multipart/form-data">
                <input type="file" name="portfolioFile" id="portfolioFile">
                <input type="submit" value="Upload Portfolio">
            </form>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
