<!-- KACPER POPIS -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"><!-- Setting the character encoding -->
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
        <nav>
            <!-- Navigation that is on the top of the page -->
        <nav>
            <!-- div which holds the homepage button -->
            <div class="logo">
                <a href="index.php"/>Eventura</a>
            </div>

            <!-- div holding the navigation to the remaining pages-->
            <div class="pageNav">
                <a href="services.php">Services</a>
                <a href="locations.php">Locations</a>
                <a href="team.php">The Team</a>
                <a href="support.php" id="curPage">Customer Support</a>
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
        
        <!-- container to hold the customer support form -->
        <div class="content3">
            <div class="whiteBox">
                <h1>Get In Touch With Us</h1>
                
                <!-- creating a little info band at the top of the form -->
                <div class="ticketBand">
                    <p>Report any issues you have encountered here, or if you require assistane with an event booking</p>
                </div>
                
                <!-- creating a form to allow a user to send feedback / request help -->
                <!-- the form requires a first name, last name, email, telephone number and a description -->
                <!-- the description is a large textarea where the user can type within the constraints of a box rather than an text type input that srolls to the right hitting the border -->
                <!-- once the user submits a ticket, it is stored inside of the database -->
                <!-- the user must be logged in to submit a ticket -->
                <div class="ticketForm">
                    <form action="phpScripts/dbTicket.php" method="post">
                        <div class="grid">
                            <div class="sameLine">
                                <input type="text" name="fName" id="fName" placeholder="First Name" required>
                                <input type="text" name="lName" id="lName" placeholder="Last Name" required>
                            </div>
    
                            <input type="email" name="email" id="email" placeholder="john_smith@hotmail.com" required>
    
                            <input type="tel" name="tel" id="tel" placeholder="+447123123123" required>

                            <textarea name="description" id="desc" placeholder="Tell us the reason for contacting" required></textarea>
    
                            <div class="centeredBtn">
                                <input type="submit" value="Submit Ticket">
                            </div>
                        </div>
                    </form>
                </div>
                <br><br>
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