<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduSupport - Logout</title>
    <link rel="stylesheet" href="style.css">
    <style>
        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #003366;
            color: #fff;
            text-align: center;
        }

        header {
            background-color: #003366;
            color: #fff;
            padding: 2rem;
        }

        header h1 {
            font-size: 3rem;
            margin: 0;
        }

        /* Main Section */
        .main {
            padding: 4rem 2rem;
        }

        .message {
            font-size: 1.5rem;
            margin-bottom: 2rem;
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
            margin-top: 2rem;
        }
    </style>
</head>
<body>

    <!-- Header Section -->
    <header>
        <h1>EduSupport</h1>
    </header>

 
    
    <!-- Main Content Section -->
    <div class="main">
        <div class="message">
            You have successfully logged out.
        </div>
        <a href="/"><button class="btn">Return to Home</button></a>
        <a href="login"><button class="btn">Login Again</button></a>
    </div>

    <!-- Footer Section -->
    <footer>
        <p>&copy; 2024 EduSupport. All rights reserved.</p>
    </footer>

</body>
</html>
