<!-- KACPER POPIS -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"> <!-- Setting the character encoding -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login to your account</title> <!-- Setting the title of the page --> 
        <link rel="stylesheet" href="css/style.css"> <!-- Linking the css file to the page -->
        <!-- Loading in a google font called "Inter" -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
        <?php session_start() ?> <!-- Resumes an existing session (if there is one) if not, generates a new session -->
    </head>

    <body>
        <!-- Navigation that is on the top of the page -->
        <nav>
            <!-- div which holds the homepage button -->
            <div class="logo">
                <a href="index.php">Eventura</a>
            </div>

            <!-- div holding the navigation to the remaining pages-->
            <div class="pageNav">
            <a href="services.php">Services</a>
                <a href="locations.php">Locations</a>
                <a href="team.php">The Team</a>
                <a href="support.php">Customer Support</a>
                <a href="reviews.php">Reviews</a>
            </div>

            <!-- div in charge of showing / hiding the login button when necessary -->
            <div class="profile">
                <!-- if there is no session, show the login button -->
                <?php if (empty($_SESSION['accType'])): ?>
                    <a href="login.php" class="login">Login</a>
                <?php else: ?>
                    <!-- if the account is of a customer type then the login button changes to a profile icon -->
                    <!-- the user will be redirected to the customer dashboard once pressing on the profile icon -->
                    <?php if ($_SESSION['accType'] === "customer"): ?>
                        <!-- creating a logout button -->
                        <form action="phpScripts/dbEndSession.php" class="logout">
                            <input type="submit" value="Logout">
                        </form>

                        <a href="customer.html">
                            <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                        </a>
                    <!-- if the account is of a business type then the login button changes to a profile icon -->
                    <!-- the user will be redirected to the business dashboard once pressing on the profile icon-->
                    <?php elseif ($_SESSION['accType'] === "business"): ?>
                        <!-- creating a logout button -->
                        <form action="phpScripts/dbEndSession.php" class="logout">
                            <input type="submit" value="Logout">
                        </form>

                        <a href="business.html">
                            <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                        </a>
                    <?php endif ?>
                <?php endif ?>
            </div>
        </nav>
        
        <!-- div to hold the login form and give the page a background -->
        <div class="login">
            <!-- page background -->
            <div class="loginBG">
                <img src="images/loginBG.jpg" alt="">
            </div>
            
            <!-- creating a container / wrapper to hold the form -->
            <div class="loginFormContainer">
                <div class="formHeader">
                    <h1>Welcome</h1>
                </div>
                
                <!-- creaeting the form to allow a user to login -->
                <!-- the form uses a php script to check against the database to see if the user exists and then logs in the user if they exist, if not it gives an error -->
                <!-- the form asks for a username and password -->
                <!-- user can be redirected to a signup page from here -->
                <form action="phpScripts/dbLogin.php" method="post">
                    <input type="text" name="username" id="uName" placeholder="Username" required>

                    <input type="password" name="password" id="pWord" placeholder="Password" required><br>
                    <div class="forgotPass">
                        <a href="forgotPass.html" id="forgotPass">Forgot Password</a>
                    </div>
                    
                    <input type="submit" value="Login">
                    
                    <p>No account? <a href="signup.php">Sign Up</a></p>
                </form>
            </div>
        </div>
    </body>
</html>