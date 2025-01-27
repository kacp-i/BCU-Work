<?php
// Panu

    // start a session

    //session_start();

    include "dbConnection.php";

    //echo '<div>'."this is an echo1".'</div>';
    $sql = "SELECT * FROM reviewdetails";
        $result = mysqli_query($conn, $sql);

        // Return the number of rows in result set
        $totalReviewCount=mysqli_num_rows($result);
        //printf("There are %d number of reviews.\n",$totalReviewCount);

        // array for each item of data in table to be referenced in reviews.php to be displayed correctly
        // no need for review id
        $customerNames = array();
        $serviceNames = array();
        $reviewScores = array();
        $reviewDescriptions = array();
        $reviewHelpfulCounts = array();
        $reviewId = 0;



        if (mysqli_num_rows($result) > 0) { // === 0 ??
            while ($row = mysqli_fetch_assoc($result)){
                $customerId = $row["customerID"];
               // $customerNames [] = "SELECT username FROM customerdetails WHERE customerID = '$customerId';"; //cant do sqlquery?!
                $sqlTemp = "SELECT username FROM customerdetails WHERE customerID = '$customerId'";
                $resultTemp = mysqli_query($conn, $sqlTemp);
                $rowTemp = mysqli_fetch_assoc($resultTemp);
                $rowTemp = $rowTemp["username"];
                $customerNames [] = $rowTemp;

                $serviceId= $row["service_id"];
                //$serviceNames [] = "SELECT serviceName FROM services WHERE serviceID = '$serviceId';";
                $sqlTemp = "SELECT serviceName FROM services WHERE serviceID = '$serviceId'";
                $resultTemp = mysqli_query($conn, $sqlTemp);
                $rowTemp = mysqli_fetch_assoc($resultTemp);
                $rowTemp = $rowTemp["serviceName"];
                $serviceNames [] = $rowTemp;
            
                $reviewScores [] = $row["reviewScore"];
                $reviewDescriptions [] = $row["reviewDescription"];
                $reviewHelpfulCounts [] = $row["reviewHelpfulCount"];
            }
        }

