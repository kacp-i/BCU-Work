<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create your account</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
        <?php session_start ?>
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
        
        <div class="signup">
            <div class="signupBG">
                <img src="images/loginBG.jpg" alt="">
            </div>
            
            <div class="signupFormContainer">
                <div class="formHeader">
                    <h1>Create an account</h1>
                </div>

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