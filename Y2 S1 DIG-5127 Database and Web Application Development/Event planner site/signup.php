<!-- KACPER POPIS -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"> <!-- Setting the character encoding -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create your account</title> <!-- Setting the title of the page --> 
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
        
        <!-- div to hold the signup form and give the page a background -->
        <div class="signup">
            <!-- page background -->
            <div class="signupBG">
                <img src="images/loginBG.jpg" alt="">
            </div>
            
            <!-- creating a container / wrapper to hold the form -->
            <div class="signupFormContainer">
                <div class="formHeader">
                    <h1>Create an account</h1>
                </div>
                
                <!-- creaeting the form to allow a user to create an account -->
                <!-- the form uses a php script to check against the database to see if the user already exists if this user exists an error will be given to the user -->
                <!-- all the fields in the form are required before the user submits -->
                <!-- the form asks for a username, password, email, telephone number, first name, last name, address and a postcode -->
                <!-- user can select if the account being made is a business account or a customer account -->
                <form action="phpScripts/dbSignup.php" method="post">

                    <div class="accType">
                        <div id="optionOne">
                            <p>Customer</p>
                        </div>
                        <label class="switch">
                            <input type="checkbox" name="accountType" id="accType">
                            <span class="slider round"></span>
                        </label>
                        <div id="optionTwo">
                            <p>Business</p>
                        </div>
                    </div>

                    <input type="text" name="username" id="uName" placeholder="Username" required>

                    <div class="sameLine">
                        <input type="password" name="password" id="pWord" placeholder="Password" required>
                        <input type="password" name="passwordConfirm" id="pWordConfirm" placeholder="Re-type your Password" required>
                    </div>
                    
                    <input type="email" name="email" id="email" placeholder="example_123@gmail.com" required>
                    <input type="tel" name="tel" id="tel" placeholder="+447123123123" pattern="+[0-9]{12}" required>
                    <input type="text" name="fName" id="fName" placeholder="First Name" required>
                    <input type="text" name="lName" id="lName" placeholder="Last Name" required>
                    <input type="text" name="addLineOne" id="addLineOne" placeholder="Address Line 1" required>
                    <input type="text" name="postcode" id="pCode" placeholder="Post Code" required>
                    

                    <input type="submit" value="Signup">
                </form>
            </div>
        </div>
    </body>
</html>