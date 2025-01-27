<?php
// KACPER POPIS

    // resumes a session
    session_start();

    // grabs the php script to connect to the database
    include "dbConnection.php";

    // function to remove "noise"
    function validate($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

    // checking if the session variable has an ID assigned and if the account type is a customer
    if (isset($_SESSION['id']) && $_SESSION['accType'] === "customer") {
        // grabbing all the values and clearing any "noise"
        $locID = validate($_POST['location']);
        $serID = validate($_POST['service']);
        $userID = validate($_SESSION['id']);

        // creating the sql query to insert data into the eventdetails table with the data provided
        $sql = "INSERT INTO `eventdetails` (`eventID`, `locationID`, `serviceID`, `customerID`) VALUES (NULL, '$locID', '$serID', '$userID')";

        // performing the query
        mysqli_query($conn, $sql);

        // sends a message to confirm a booking has been made
        echo "Booking Created";

        // redirects the user to a confirmation page
        header("Location: ../smallRedirects/eventConfirm.php");

        // terminates the script
        exit();
    }
    // if the session variable is not assigned it redirects the user to the login page to login
    else {
        header("Location: ../login.php?error=You must be logged in as a customer to book an event");
        // terminates the script
        exit();
    }