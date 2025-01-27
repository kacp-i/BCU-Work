<?php
// KACPER POPIS

    // grabs the connection file
    include "dbConnection.php";

    // checks if the inputs arent NULL
    if (isset($_POST['fName']) && isset($_POST['lName']) && isset($_POST['email']) && isset($_POST['email']) && isset($_POST['tel']) && isset($_POST['description'])) {

        // function to remove "noise"
        function validate($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        // grabbing all the values and removing any "noise"
        $fName = validate($_POST['fName']);
        $lName = validate($_POST['lName']);
        $email = validate($_POST['email']);
        $tel = validate($_POST['tel']);
        $desc = validate($_POST['description']);

        // creating the sql statement to insert the user data into the supporttickets table 
        $sql = "INSERT INTO `supporttickets` (`ticketID`, `firstName`, `lastName`, `email`, `phoneNum`, `reason`) VALUES (NULL, '$fName', '$lName', '$email', '$tel', '$desc')";

        // performs the sql query
        mysqli_query($conn, $sql);

        // redirects the user to a page confirming the ticket has been made
        header("Location: ../smallRedirects/ticketConfirm.php");

        // terminates the script
        exit();
    }
    // if the inputs are NULL, redirects the user to the support page with an error
    else {
        header("Location: ../support.html?error=Please fill in all the fields");
        // terminates the script
        exit();
    }