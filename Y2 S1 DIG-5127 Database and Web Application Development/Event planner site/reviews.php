<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reviews</title>
    <link rel="stylesheet" href="css/reviews.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <?php session_start() ?>
    
    <div class="hideMe">
        <?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviews.php");?>
        <?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviewsubmit.php");?>
    </div>
    
</head>
<body>
    <div class="wrapper">
        <nav>
            <div class="logo">
                <a href="index.php">Eventura</a>
            </div>
            <div class="pageNav">
                <a href="services.php">Services</a>
                <a href="locations.php">Locations</a>
                <a href="team.php">The Team</a>
                <a href="support.php">Customer Support</a>
                <a href="reviews.php" id="curPage">Reviews</a>
            </div>
            <div class="profile">
                <?php if (empty($_SESSION['accType'])): ?>
                    <a href="login.php">Login</a>
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
        <?php //$reviewsubmit = new Dbreviewsubmit(); 
        ?>
            <div class="left-section">
                <h2>OUR TOP REVIEWS</h2>
                <div class="top-reviews">
                    <div class="review-card">
                        <div class="review-header">
                            <span class="review-stars"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php"; //could remove
                            echo "$reviewScores[0]"; ?></span>
                            <span class="review-author"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$customerNames[0]"; ?></span>
                            <span class="review-service"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$serviceNames[0]"; ?></span>
                        </div>
                        <p>
                            <?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewDescriptions[0]"; ?>
                        </p>
                        <span class="review-helpful"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewHelpfulCounts[0]"; ?></span>
                            <form action="phpScripts/dbreviewsubmit.php" method="post">
                            <div class="buttons">
                            <?php //$reviewsubmit->setReviewId(1) 
                            ?>
                                <?php $reviewId = 1?>
                                <input type = "submit" name = "washelpful" onclick = "<?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviewsubmit.php");?>" class = "submit-btn" value = "Was Helpful"/>                            </div>
                        </form>
                    </div>
                    <div class="review-card">
                        <div class="review-header">
                            <span class="review-stars"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewScores[1]"; ?></span>
                            <span class="review-author"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$customerNames[1]"; ?></span>
                            <span class="review-service"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$serviceNames[1]"; ?></span>
                        </div>
                        <p>
                            <?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewDescriptions[1]"; ?>
                        </p>
                        <span class="review-helpful"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewHelpfulCounts[1]"; ?></span>
                            <form action="phpScripts/dbreviewsubmit.php" method="post">
                            <div class="buttons">
                                <?php $reviewId = 2?>
                                <input type = "submit" name = "washelpful" onclick = "<?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviewsubmit.php");?>" class = "submit-btn" value = "Was Helpful"/>                            </div>
                        </form>
                    </div>
                    <div class="review-card">
                        <div class="review-header">
                            <span class="review-stars"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewScores[2]"; ?></span>
                            <span class="review-author"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$customerNames[2]"; ?></span>
                            <span class="review-service"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$serviceNames[2]"; ?></span>
                        </div>
                        <p>
                            <?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewDescriptions[2]"; ?>
                        </p>
                        <span class="review-helpful"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewHelpfulCounts[2]"; ?></span>
                        <form action="phpScripts/dbreviewsubmit.php" method="post">
                            <div class="buttons">
                                <?php $reviewId = 3?>
                                <input type = "submit" name = "washelpful" onclick = "<?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviewsubmit.php");?>" class = "submit-btn" value = "Was Helpful"/>                            </div>
                        </form>
                    </div>
                </div>

                <h2>ALL REVIEWS</h2>
                <div class="all-reviews"> 
                    <?php 
                    // just testing if iteration of all can work
                    //include __DIR__."/../phpScripts/dbreviews.php";
                    echo "Total Reviews: " , $totalReviewCount;
                    for($x = 0; $x < $totalReviewCount; $x++){
                        ?>
                        <div class="review-card">
                        <div class="review-header">
                            <span class="review-stars"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewScores[$x]"; ?></span>
                            <span class="review-author"><?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$customerNames[$x]"; ?></span>
                            <span class="review-service"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$serviceNames[$x]"; ?></span>
                        </div>
                        <p>
                            <?php
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewDescriptions[$x]"; ?>
                        </p>
                        <span class="review-helpful"><?php 
                            //include __DIR__."/../phpScripts/dbreviews.php";
                            echo "$reviewHelpfulCounts[$x]"; ?></span>
                        <form action="phpScripts/dbreviewsubmit.php" method="post">
                            <div class="buttons">
                                <?php $reviewId = $x?>
                                <input type = "submit" name = "washelpful" onclick = "<?php include($_SERVER['DOCUMENT_ROOT']."/eventsite/phpScripts/dbreviewsubmit.php");?>" class = "submit-btn" value = "Was Helpful"/>
                            </div>
                        </form>
                    </div>
                    <?php 
                    }
                    ?>

                <form action="phpScripts/dbreviewsubmit.php" method="post">
                    <div class="rating-container">
                        <h2>Rate your experience</h2>
                        <div class="stars">
                            <input type="radio" id="star5" name="rating" value="5">
                            <label for="star5" class="star">&#9733;</label>
                            <input type="radio" id="star4" name="rating" value="4">
                            <label for="star4" class="star">&#9733;</label>
                            <input type="radio" id="star3" name="rating" value="3">
                            <label for="star3" class="star">&#9733;</label>
                            <input type="radio" id="star2" name="rating" value="2">
                            <label for="star2" class="star">&#9733;</label>
                            <input type="radio" id="star1" name="rating" value="1">
                            <label for="star1" class="star">&#9733;</label>
                        </div>
                        <textarea type="serv_name" name="serv_name" placeholder="Write your service name here..."></textarea required>
                        <textarea type="descript" name="descript" placeholder="Write your review here..."></textarea required>
                        <div class="buttons">
                            <input type = "submit" name = "submitreview" class = "submit-btn" value = "Submit Review" />
                            <input type = "submit" name = "submitreview" class = "submit-btn" value = "Cancel Writing Review" />
                        </div>
                    </div>
                </form>
            </div>
        </main>

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





