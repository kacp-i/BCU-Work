<!-- KACPER POPIS -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"> <!-- Setting the character encoding -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Services</title> <!-- Setting the title of the page --> 
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
                <a href="index.php"/>Eventura</a>
            </div>

            <!-- div holding the navigation to the remaining pages-->
            <div class="pageNav">
                <a href="services.php" id="curPage"">Services</a>
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
        
        <!-- div to hold the form to create a booking for an event -->
        <div class="content3">
            <!-- creating a form to allow the user to select a service and a location for the event -->
            <form action="phpScripts/dbServices.php" method="post">
                <div class="whiteBox">
                    <h1>Select Your Service</h1>
                
                    <!-- the user selects 1 service out of the 6 possible ones -->
                    <!-- 1 out of the 6 must be selected before the user can press the submit button on the form -->
                    <!-- the choices are put into a 3 column grid, and each choice has a radio button connected to it -->
                    <!-- this content could potentially be loaded from the database in the future rather than hard code it -->
                    <div class="grid">
                        <label for="ser1">
                            <div class="gridChoice">
                                <h3>Photobomb</h4>
                                <h2>PPD: £1250.99</h2>
                                <h4>Service Type: Photography</h4>
                                <h4>Provider: Intel</h4>
                                <h5>Service Description:</h5>
                                <p>Photobomb is a professional photography company dedicated to preserving life’s most cherished memories through stunning imagery. Specializing in a wide range of photography services, we aim to provide clients with high-quality photos that tell their unique stories</p>
                            </div>
                        </label>
                        <label for="ser2">
                            <div class="gridChoice">
                                <h3>Child Entertainment</h4>
                                <h2>PPD: £299.99</h2>
                                <h4>Service Type: Entertainment</h4>
                                <h4>Provider: Kids R Us</h4>
                                <h5>Service Description:</h5>
                                <p>Kids R Us specializes in providing engaging and interactive experiences for children aged 2 to 12 years. Our mission is to create magical moments that inspire creativity, joy, and learning through various fun-filled activities</p>
                            </div>
                        </label>
                        <label for="ser3">
                            <div class="gridChoice">
                                <h3>Hamburger Cheeseburger BigMac Whopper</h4>
                                <h2>PPD: £799.99</h2>
                                <h4>Service Type: Catering</h4>
                                <h4>Provider: McDonalds</h4>
                                <h5>Service Description:</h5>
                                <p>McDonald's is a globally recognized fast-food chain that serves a diverse range of affordable and delicious meals, making it a popular choice for families, individuals, and children. Known for its iconic golden arches, McDonald's aims to provide an enjoyable dining experience with a focus on quality, convenience, and customer satisfaction</p>
                            </div>
                        </label>
                        <input type="radio" id="ser1" name="service" value="1" required>
                        <input type="radio" id="ser2" name="service" value="2">
                        <input type="radio" id="ser3" name="service" value="3">

                        <label for="ser4">
                            <div class="gridChoice">
                                <h3>Feastingables</h4>
                                <h2>PPD: £19.00</h2>
                                <h4>Service Type: Catering</h4>
                                <h4>Provider: Mr Burger</h4>
                                <h5>Service Description:</h5>
                                <p>This innovative fast-food concept leverages delivery-only services to reach fans and customers nationwide. The brand is known for its delicious burgers, fries, and unique menu items, all created with a focus on quality and customer satisfaction</p>
                            </div>
                        </label>
                        <label for="ser5">
                            <div class="gridChoice">
                                <h3>Elegant Events Catering</h4>
                                <h2>PPD: £2000.00</h2>
                                <h4>Service Type: Catering</h4>
                                <h4>Provider: EEC</h4>
                                <h5>Service Description:</h5>
                                <p>Elegant Events Catering is a premier wedding catering company dedicated to creating unforgettable culinary experiences for couples on their special day. With a focus on high-quality ingredients, exceptional service, and customized menus, we aim to elevate wedding celebrations with delicious food that reflects the couple’s unique tastes and style</p>
                            </div>
                        </label>
                        <label for="ser6">
                            <div class="gridChoice">
                                <h3>Freddy Fez</h4>
                                <h2>PPD: £840.75</h2>
                                <h4>Service Type: Entertainment</h4>
                                <h4>Provider: FF Inc</h4>
                                <h5>Service Description:</h5>
                                <p>Freddy Fez is an enchanting traveling circus that brings joy and wonder to audiences of all ages. With a rich tradition of performing arts, our circus combines breathtaking acrobatics, whimsical clowns, and mesmerizing animal acts to create a magical experience that captivates and entertains</p>
                            </div>
                        </label>
                            
                        <input type="radio" id="ser4" name="service" value="4">
                        <input type="radio" id="ser5" name="service" value="5">
                        <input type="radio" id="ser6" name="service" value="6">
                    </div>
                    <br>
                </div>

                <div class="whiteBox">
                    <h1>Select Your Location</h1>
                    
                    <!-- the user selects 1 location out of the 3 possible ones -->
                    <!-- 1 out of the 3 must be selected before the user can press the submit button on the form -->
                    <!-- the choices are put into a 3 column grid, and each choice has a radio button connected to it -->
                    <!-- this content could potentially be loaded from the database in the future rather than hard code it -->
                    <div class="grid">
                        <label for="loc1">
                            <div class="gridChoice">
                                <h3>Merrydale Manor</h3>
                                <h2>Size: 2000</h2>
                                <p>Address: Knutsford, Cheshire</p>
                                <img src="images/locationImages/merrydale.jpeg" alt="Merrydale Venue">
                            </div>
                        </label>
                        <label for="loc2">
                            <div class="gridChoice">
                                <h3>Farnham Castle</h3>
                                <h2>Size: 875</h2>
                                <p>Address: Farnham, Surrey</p>
                                <img src="images/locationImages/farnham.jpg" alt="Farnham Venue">
                            </div>
                        </label>
                        <label for="loc3">
                            <div class="gridChoice">
                                <h3>Brendenbury Court Barns</h3>
                                <h2>Size: 1200</h2>
                                <p>Address: Hereford, Herefordshire</p>
                                <img src="images/locationImages/brendenbury.jpeg" alt="Brendenbury Venue">
                            </div>
                        </label>

                        <input type="radio" id="loc1" name="location" value="1" required>
                        <input type="radio" id="loc2" name="location" value="2">
                        <input type="radio" id="loc3" name="location" value="3">
                        <br>
                    </div>
                </div>
                
                <!-- user presses the submit button to create the event booking -->
                <div class="whiteBox">
                    <div class="centeredBtn">
                        <input type="submit" value="Confirm">
                    </div>
                </div>
            </form>
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


