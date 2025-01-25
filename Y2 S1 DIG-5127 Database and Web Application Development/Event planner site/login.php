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
        <?php session_start() ?>
    </head>
    <body>
        <nav>
            <div class="logo">
                <a href="index.php">Eventura</a>
            </div>
            <div class="pageNav">
            <a href="services.php">Services</a>
                <a href="locations.php">Locations</a>
                <a href="team.php">The Team</a>
                <a href="support.php">Customer Support</a>
                <a href="reviews.php">Reviews</a>
            </div>
            <div class="profile">
                <?php if (empty($_SESSION['accType'])): ?>
                    <a href="login.php">Login</a>
                <?php else: ?>
                    <?php if ($_SESSION['accType'] === "customer"): ?>
                        <a href="customerDash.php">
                            <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                        </a>
                    <?php elseif ($_SESSION['accType'] === "business"): ?>
                        <a href="businessDash.php">
                            <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                        </a>
                    <?php endif ?>
                <?php endif ?>
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
                    
                    <p>No account? <a href="signup.php">Sign Up</a></p>
                </form>
            </div>
        </div>
    </body>
</html>