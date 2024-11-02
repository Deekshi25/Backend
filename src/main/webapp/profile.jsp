<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Student Profile</title>
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

        .profile-card {
            background-color: #004080;
            border-radius: 10px;
            padding: 2rem;
            margin: 1rem;
            width: 300px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            transition: transform 0.3s;
        }

        .profile-card:hover {
            transform: scale(1.05);
        }

        .profile-card h2 {
            font-size: 1.8rem;
            margin-bottom: 1rem;
            color: #f0a500; /* Highlight color */
        }

        .profile-card p {
            font-size: 1rem;
            line-height: 1.4;
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
            margin-top: 1rem;
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
    </style>
</head>
<body>

    <!-- Header Section -->
    <header>
        <h1>EduSupport - Student Profile</h1>
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
        <div class="profile-card">
            <h2>Profile Information</h2>
            <p><strong>Name:</strong> KORA ABHIJITH</p>
            <p><strong>Email:</strong> 22000*****@kluniversity.in</p>
            <p><strong>University:</strong> K L University</p>
            <p><strong>Course:</strong>Java Full Stack Development</p>
            <p><strong>Year:</strong>3-1</p>
            
        

        
            <h2>Highlights</h2>
            <p><strong>Achievements:</strong>01</p>
            <p><strong>Skills:</strong>C, Java, Python</p>
            <p><strong>Interests:</strong>Problem Solving</p>
        

       
            <h2>Contact Information</h2>
            <p><strong>Phone:</strong> 98745*****</p>
            <p><strong>LinkedIn:</strong> https://www.linkedin.com/in/abhijith-kora-a2191025a/</p>
            <p><strong>GitHub:</strong>github.com</p>

            <button class="btn">Edit Profile</button>
        </div>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
