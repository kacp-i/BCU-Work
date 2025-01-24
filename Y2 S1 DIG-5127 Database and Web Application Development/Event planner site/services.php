<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Services</title>
    <link rel="stylesheet" href="css/services.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <?php session_start ?>
</head>
<body>
    <div class="wrapper">
        <!-- Navigation -->
        <nav>
            <div class="logo">
                <a href="index.html">Eventura</a> <!-- Use a real path like index.html or your homepage -->
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

        <!-- Content Section -->
        <main>
            <div class="content3">
                <div class="whiteBox">
                    <!-- Add your image here -->
                    <img src="images/services-page-example.webp" alt="Event image description">
                </div>
            </div>
        </main>

        <!-- Footer -->
        <footer>
            <div class="footerContent">
                <div class="split30">
                    <h1>EVENTURA</h1>
                    <p>We have planning options that suit your style from the layout to the location to the menu!</p>
                </div>
                <div class="split70">
                    <ul>
                        <li><a href="reviews.html">Reviews</a></li>
                        <li><a href="support.html">Customer Support</a></li>
                        <li><a href="team.html">The Team</a></li>
                        <li><a href="locations.html">Locations</a></li>
                        <li><a href="services.html">Services</a></li>
                    </ul>
                </div>
            </div>
            <div class="splitter">
                <div class="copyright">
                    <p>Eventura Copyright © All rights reserved</p>
                </div>
                <div class="payments">
                    <ul>
                        <li>
                            <a href="https://www.google.com" target="_blank">
                                <img src="images/payment-methods.png" alt="Payments">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>


