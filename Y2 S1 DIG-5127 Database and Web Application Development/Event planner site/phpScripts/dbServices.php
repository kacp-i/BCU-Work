<?php
// Kacper Popis
    // starts a session
    session_start();

    // grabs the connection file
    include "dbConnection.php";

    // function to remove "noise"
    function validate($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }

    // checking if the user has an id and is a customer  
    if (isset($_SESSION['id']) && $_SESSION['accType'] === "customer") {
        $locID = validate($_POST['location']);
        $serID = validate($_POST['service']);
        $userID = validate($_SESSION['id']);

        $sql = "INSERT INTO `eventdetails` (`eventID`, `locationID`, `serviceID`, `customerID`) VALUES (NULL, '$locID', '$serID', '$userID')";

        mysqli_query($conn, $sql);

        echo "Booking Created";

        header("Location: ../index.php");
    }
    else {
        header("Location: ../login.php?error=You must be logged in to book an event");
        exit();
    }