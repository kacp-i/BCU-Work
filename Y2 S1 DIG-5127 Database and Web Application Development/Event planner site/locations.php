<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Locations</title>
    <link rel="stylesheet" href="css/locationscss.css">
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
                <a href="locations.php" id="curPage">Locations</a>
                <a href="team.php">The Team</a>
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

        <br>

        <main class="container">
            <div class="left-section">
                <h2>Locations & Hours</h2>
                <p>For over 30 years, we’ve been crafting unforgettable wedding experiences for couples across the region. Our expert team of planners, designers, and coordinators is dedicated to bringing your vision to life, ensuring every detail is perfect. From elegant venues to customized menus, we specialize in creating moments that are as unique as your love story. Whether you're dreaming of an intimate gathering or a grand celebration, our goal is to make your wedding day stress-free and truly unforgettable.</p>
                <div class="location-dropdown">
                    <label for="location-select">Select Location:</label>
                    <select id="location-select">
                        <option value="merrydale">Merrydale Manor</option>
                        <option value="farnham">Farnham Castle</option>
                        <option value="brendebury">Brendebury Court Barns</option>
                    </select>
                </div>
            </div>
    
            <div class="right-section">
                <iframe
                    id="location-map"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d241317.11609992904!2d72.74109906931187!3d19.082197839789267!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3be7b5c0c5f5b251%3A0xa74b62f9e756fd7c!2sBloomington%20Bone%20%26%20Joint%20Clinic!5e0!3m2!1sen!2sus!4v123456789"
                    width="100%"
                    height="400"
                    style="border:0;"
                    allowfullscreen=""
                    loading="lazy"
                ></iframe>
            </div>
        </main>

        <section class="spacer"></section>
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

    <script>
        const locationMap = document.getElementById("location-map");
        const locationSelect = document.getElementById("location-select");

        locationSelect.addEventListener("change", (event) => {
            const location = event.target.value;
            switch (location) {
                case "merrydale":
                    locationMap.src = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2386.266472513102!2d-2.319195423758506!3d53.266844980669724!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487a516e82b8a347%3A0x7ed8fef75fc32557!2sMerrydale%20Manor%20Wedding%20Venue!5e0!3m2!1sen!2suk!4v1737899932920!5m2!1sen!2suk"; 
                    break;
                case "farnham":
                    locationMap.src = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2498.998334929937!2d-0.8047145238908645!3d51.21910633172387!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48742c534d503593%3A0x2a30252afeb70fd8!2sFarnham%20Castle!5e0!3m2!1sen!2suk!4v1737900252278!5m2!1sen!2suk";
                    break;
                case "brendebury":
                    locationMap.src = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2445.3518139662847!2d-2.5732785238279443!3d52.20065385982456!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x48705b2cdfcff135%3A0x60283cd4c0b7af0f!2sBredenbury%20Court%20Barns%20Unique%20Barn%20Wedding%20Venue!5e0!3m2!1sen!2suk!4v1737900340651!5m2!1sen!2suk";
                    break;
            }
        })
    </script>
</body>
</html>
