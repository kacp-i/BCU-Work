<!-- KACPER POPIS -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"> <!-- Setting the character encoding -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Homepage</title> <!-- Setting the title of the page --> 
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
                <a href="index.php" id="curPage"/>Eventura</a>
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

        <!-- div creating a banner containing text and images -->
        <div class="banner">
            <div class="textColumn">
                <h1>WEDDING EVENTS THAT MATCHES YOUR STYLE</h1>
                <p>Explore our meticulously created wedding services tailored to bring your dream event to life </p>
                <!-- user can be redirected to the services page -->
                <a href="services.php" class="button">Services</a>
            </div>
            <div class="imgColumn">
                <img src="images/image1.png" alt="Man">
            </div>
        </div>

        <!-- div to display a couple sponsors that we "work" with -->
        <div class="sponsors">
            <h3>Some of our sponsors:</h3>
            
            <!-- using a list to display these images (with redirects to their websites) -->
            <ul>
                <li><a href="https://www.whirpool.co.uk"><img src="images/sponsors/whirpool.svg" alt="Whirpool"></a></li>
                <li><a href="https://www.foodstorm.com"><img src="images/sponsors/foodstorm.png" alt="FoodStorm"></a></li>
                <li><a href="https://www.mcdonalds.com"><img src="images/sponsors/mcdonald.png" alt="McDonalds"></a></li>
                <li><a href="https://www.compass-group.com"><img src="images/sponsors/compass.png" alt="Compass Group"></a></li>
            </ul>
        </div>
        
        <!-- div containing images and text-->
        <div class="content1">
            <div class="column">
                <h1>LOCATIONS</h1>
            </div>
            <div class="column">
                <h1>OUR SERVICES</h1>
            </div>
            <div class="separator">
                <p>Explore our ranged loacted all around the UK & Europe</p>
            </div>
            <div class="separator">
                <p>View our variety of packages to make your wedding day a special one</p>
            </div>
            <div class="column">
                <img src="images/image2.png" alt="">
            </div>
            <div class="column">
                <img src="images/image4.png" alt="">
            </div>
        </div>

        <!-- div simulating a similar view as to what we have on the reviews page -->
        <div class="content2">
            <h1>OUR TOP REVIEWS</h1>
            <div class="grid3">
                <div class="column">
                    <span>★★★★★</span>
                    <h3>Sarah M.</h3>
                    <p>From start to finish, working with Eventura was an absolute dream! They took the time to understand our vision and made our wedding day truly wonderful. The attention to detail was impeccable, and every little thing was perfectly coordinated. We didn’t have to worry about a thing, as they handled all the logistics with ease. If you're looking for a flawless wedding, Eventura is the one to trust! Thank you for making our day unforgettable! 💖</p>
                </div>
                <div class="column">
                    <span>★★★★★</span>
                    <h3>Alex K.</h3>
                    <p>I can't thank Eventura enough for making my wedding day incredible! From the very first consultation, they knew exactly what we wanted and executed it perfectly. The team at Eventura took care of everything, from venue selection to the smallest details. My wife and I were able to relax and enjoy our special day without a worry in the world. Our guests were blown away by how smoothly everything went. If you want your wedding to be flawless, Eventura is the way to go. Highly recommend!</p>
                </div>
                <div class="column">
                    <span>★★★★★</span>
                    <h3>James L.</h3>
                    <p>Eventura completely exceeded our expectations! As someone who isn't big on planning, I was worried about how everything would come together. But the team at Eventura made everything so easy. They were professional, efficient, and really listened to what we wanted. The day went off without a hitch, and all the little touches they added made it feel so personal. I’m grateful for their hard work in making our wedding an event to remember. If you’re looking for top-notch wedding planning, look no further than Eventura!</p>
                </div>
            </div>
            <!-- button to redirect the user to the reviews page -->
            <div class="centeredBtn">
                <a href="reviews.php" class="button">See Reviews</a>
            </div>
        </div>

        <!-- div containing an image and a redirect to the teams page-->
        <div class="content3">
            <h1>MEET THE TEAM</h1>
            <div class="imageBand">
                <div class="band"></div>
                <img src="images/image3.png" alt="">
            </div>
            <div class="centeredBtn">
                <a href="team.php" class="button">Meet The Team</a>
            </div>
        </div>

        <!-- creating a footer for the page holding redirects to all main pages on the website and some payment icons and some brief text-->
        <footer>
            <div class="footerContent">
                <!-- splitting the content into a 30 / 70 split -->
                <div class="split30">
                    <h1>EVENTURA</h1>
                    <p>We have planning options that suit your style from the layout to the location to the menu!</p>
                </div>
                <div class="split70">
                    <ul>
                        <li><a href="reviews.php">Reviews</a></li>
                        <li><a href="support.php">Customer Support</a></li>
                        <li><a href="team.php">The Team</a></li>
                        <li><a href="locations.php">Locations</a></li>
                        <li><a href="services.php">Services</a></li>
                    </ul>
                </div>
            </div>
            <div class="splitter">
                <div class="copyright">
                    <p>Eventura Copyright © All rights reserved</p>
                </div>
                <div class="payments">
                    <ul>
                        <li><img src="images/paymentIcons/payment1.svg" alt="Visa"></li>
                        <li><img src="images/paymentIcons/payment2.svg" alt="Mastercard"></li>
                        <li><img src="images/paymentIcons/payment3.svg" alt="Maestro"></li>
                        <li><img src="images/paymentIcons/payment4.svg" alt="PayPal"></li>
                        <li><img src="images/paymentIcons/payment5.svg" alt="Google Wallet"></li>
                        <li><img src="images/paymentIcons/payment6.svg" alt="Solo"></li>
                        <li><img src="images/paymentIcons/payment7.svg" alt="WorldPay"></li>
                    </ul>
                </div>
            </div>
        </footer>
    </body>
</html>