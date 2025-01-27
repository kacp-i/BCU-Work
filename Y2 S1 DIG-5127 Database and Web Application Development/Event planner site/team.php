<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meet The Team</title>
    <link rel="stylesheet" href="css/team.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <?php session_start() ?>
</head>
<body>
    <div class="wrapper">
        <!-- Navigation -->
        <nav>
            <div class="logo">
                <a href="index.php">Eventura</a>
            </div>
            <div class="pageNav">
                <a href="services.php">Services</a>
                <a href="locations.php">Locations</a>
                <a href="team.php" id="curPage">The Team</a>
                <a href="support.php">Customer Support</a>
                <a href="reviews.php">Reviews</a>
            </div>
            <div class="profile">
                <?php if (empty($_SESSION['accType'])): ?>
                    <a href="login.php" class="login">Login</a>
                <?php else: ?>
                    <?php if ($_SESSION['accType'] === "customer"): ?>
                        <form action="phpScripts/dbEndSession.php" class="logout">
                            <input type="submit" value="Logout">
                        </form>

                        <a href="customer.html">
                            <img src="images/profile_image.png" alt="Profile Image" class="profile-img">
                        </a>
                    <?php elseif ($_SESSION['accType'] === "business"): ?>
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

        <!-- Main Content -->
        <main class="container">
            <div class="team-section">
                <h2>MEET THE TEAM</h2>
                <div class="location-dropdown">
                    <label for="locations">SELECT:</label>
                    <select id="locations" name="locations">
                        <option value="merrydale">Merrydale Manor</option>
                        <option value="farnham">Farnham Castle</option>
                        <option value="brendebury">Brendebury Court Barns</option>
                    </select>
                </div>
                <div class="team-row">
                    <!-- Row 1 -->
                    <div class="team-member">
                        <img src="images/teamImages/img1.png" alt="Team Member 1" class="team-photo">
                        <p class="team-role">Event planner</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img2.png" alt="Team Member 2" class="team-photo">
                        <p class="team-role">Catering Director</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img3.png" alt="Team Member 3" class="team-photo">
                        <p class="team-role">Venue Coordinator</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img4.png" alt="Team Member 4" class="team-photo">
                        <p class="team-role">Photographer</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img5.png" alt="Team Member 5" class="team-photo">
                        <p class="team-role">Videographer</p>
                    </div>
                    <!-- Row 2 -->
                    <div class="team-member">
                        <img src="images/teamImages/img7.png" alt="Team Member 6" class="team-photo">
                        <p class="team-role">Florist</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img6.png" alt="Team Member 7" class="team-photo">
                        <p class="team-role">Lighting Technician</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img9.png" alt="Team Member 8" class="team-photo">
                        <p class="team-role">Security Coordinator</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img8.png" alt="Team Member 9" class="team-photo">
                        <p class="team-role">Sound Technician</p>
                    </div>
                    <div class="team-member">
                        <img src="images/teamImages/img10.png" alt="Team Member 10" class="team-photo">
                        <p class="team-role">Transportation Coordinator</p>
                    </div>
                </div>
            </div>
        </main>

        <section class="spacer"></section>
        <!-- Footer -->
        <footer>
            <div class="footerContent">
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
    </div>
</body>
</html>
