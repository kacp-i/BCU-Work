<?php
// Panu

    // start a session

    //session_start();
    include "dbConnection.php";
    include "dbreviews.php";

    
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        if(array_key_exists('washelpful', $_POST)) {
            echo '<div>'."this is an echo3".'</div>';
            echo "$reviewId";
            $sqlTemp = "UPDATE reviewdetails SET reviewHelpfulCount = reviewHelpfulCount + 1 WHERE reviewID = $reviewId";
            $resultTemp = mysqli_query($conn, $sqlTemp);
            echo "Helpful Count Updated For Review ID - ". $reviewId;
        }

        else if(array_key_exists('submitreview', $_POST)) {
            echo '<div>'."this is an echo2".'</div>';
            if (isset($_SESSION['id']) && $_SESSION['accType'] === "customer") { // must be a customer to review
                // Triggered when a review is submitted in reviews.php
                if (isset($_POST['serv_name']) && isset($_POST['rating']) && isset($_POST['descript'])) { // rating/radio
                        function validate($data) {
                            $data = trim($data);
                            $data = stripslashes($data);
                            $data = htmlspecialchars($data);
                            return $data;
                        }
                        
                        // variables needed for sql table insertion
                        $customerName = $username;
                        $customerId = "SELECT customerID FROM customerdetails WHERE username = '$customerName';";
                        $serviceName = validate($_POST['serv_name']);
                        $serviceId = "SELECT serviceID FROM services WHERE username = '$serviceName';";
                        $reviewScore = validate($_POST['rating']); //should be value 0-5
                        $reviewDescription = validate($_POST['descript']);
                        $reviewHelpfulCount = 0;

                        // sql query
                        $sql = "INSERT INTO 'reviewdeatails' ('reviewID', 'customerID', 'service_id', 'reviewScore', 'reviewDescription', 'reviewHelpfulCount') VALUES (NULL, '$$customerId','$serviceId', '$reviewScore', '$reviewDescription', '$reviewHelpfulCount')";
                        
                        mysqli_query($conn, $sql);
                        echo "Review Posted";
                        header("Location: ../reviews.php");
                    }
                    else{
                        // else statement when a review is submitted with NULL inputs
                        header("Location: ../reviews.php?error=Review not posted insufficient data");
                        exit();
                    }
            }
        }
        else{
            header("Location: ../reviews.php?error=You must be logged in to review a service");
            //exit();
        }
        header('Location: ../reviews.php');
    }