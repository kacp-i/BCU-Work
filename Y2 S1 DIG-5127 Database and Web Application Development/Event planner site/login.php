<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login to your account</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    </head>
    <body>
        <nav>
            <div class="logo">
                <a href="index.php">Eventura</a>
            </div>
            <div class="pageNav">
                <a href="services.html">Services</a>
                <a href="locations.html">Locations</a>
                <a href="google.com">The Team</a>
                <a href="google.com">Customer Support</a>
                <a href="google.com">Reviews</a>
            </div>
            <div class="profile">
                <a href="login.html">
                    <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                </a>
            </div>
        </nav>
        
        <div class="login">
            <div class="loginBG">
                <img src="images/loginBG.jpg" alt="">
            </div>
            
            <div class="loginFormContainer">
                <div class="formHeader">
                    <h1>Welcome</h1>
                </div>

                <form action="phpScripts/dbLogin.php" method="post">
                    <input type="text" name="username" id="uName" placeholder="Username" required>

                    <input type="password" name="password" id="pWord" placeholder="Password" required><br>
                    <div class="forgotPass">
                        <a href="google.com" id="forgotPass">Forgot Password</a>
                    </div>
                    
                    <input type="submit" value="Login">
                    
                    <p>No account? <a href="google.com">Sign Up</a></p>
                </form>
            </div>
        </div>
    </body>
</html>