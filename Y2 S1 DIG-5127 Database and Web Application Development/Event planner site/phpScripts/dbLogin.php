<?php
// KACPER POPIS

    // starts a session
    session_start();

    // grabs the php script to connect to the database
    include "dbConnection.php";

    // checks if the username and password inputs arent NULL
    if (isset($_POST['username']) && isset($_POST['password'])) {

        // function to remove "noise"
        function validate($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        // grabbing all the values and clearing any "noise"
        $username = validate($_POST['username']);
        $password = validate($_POST['password']);

        // if the username is empty, give an error
        if (empty($username)) {
            // redirects the user to the login page with an error showing a username is required
            header("Location: ../login.php?error=Username is required");
            // terminates the script
            exit();
        }
        // if the password is empty, give an error
        else if(empty($password)) {
            // redirects the user to the login page with an error showing a password is required
            header("Location: ../login.php?error=Password is required");
            // terminates the script
            exit();
        }
        // if the username and password variable arent empty the script will search the database for the user
        else {
            // checking the customer table first
            // creating the sql query to grab data from the customerdetails table with matching data
            $sql = "SELECT * FROM customerdetails WHERE username='$username' AND password='$password'";

            // performing the query and storing the results as a variable
            $result = mysqli_query($conn, $sql);

            // checking if the amount of rows results to 1
            if (mysqli_num_rows($result) === 1) {

                // stores the row as an associative array (key, value) as a variable
                $row = mysqli_fetch_assoc($result);

                // checking if the username and password values in the row match the user input
                if ($row['username'] === $username && $row['password'] === $password) {
                    // sends a message to confirm a successful login
                    echo "Logged in!";

                    // create a session variable
                    $_SESSION['username'] = $row['username'];
                    $_SESSION['id'] = $row['customerID'];
                    $_SESSION['accType'] = "customer";

                    // redirects the user to the homepage after logging in
                    header("Location: ../index.php");
                    // terminates the script
                    exit();
                }
                // if the data entered by the user doesnt match with the data retrieved the user isnt logged in and an error is sent
                else {
                    header("Location: ../login.php?error=Incorect User name or password");
                    // terminates the script
                    exit();
                }
            }
            // if the user isnt found in the customer table, the business table is attempted
            else {
                // checking the business table
                // creating the sql query to grab data from the businesses table with matching data
                $sql = "SELECT * FROM businesses WHERE username='$username' AND password='$password'";

                // performing the query and storing the results as a variable
                $result = mysqli_query($conn, $sql);

                // checking if the amount of rows results to 1
                if (mysqli_num_rows($result) === 1) {

                    // stores the row as an associative array (key, value) as a variable
                    $row = mysqli_fetch_assoc($result);

                    // checking if the username and password values in the row match the user input
                    if ($row['username'] === $username && $row['password'] === $password) {
                        // sends a message to confirm a successful login
                        echo "Logged in!";

                        // create a session variable
                        $_SESSION['username'] = $row['username'];
                        $_SESSION['id'] = $row['businessID'];
                        $_SESSION['accType'] = "business";
                        
                        // redirects the user to the homepage after logging in
                        header("Location: ../index.php");
                        // terminates the script
                        exit();
                    }
                    // if the data entered by the user doesnt match with the data retrieved the user isnt logged in and an error is sent
                    else {
                        header("Location: ../login.php?error=Incorect User name or password");
                        // terminates the script
                        exit();
                    }
                }
                // if the user isnt found in the business table the user is redirected to the login page and an error is sent
                else {
                    header("Location: ../login.php?error=Incorect User name or password");
                    // terminates the script
                    exit();
                }
            }
        }
    }
    // if the post variable is not set redirect to the login page with an error
    else {
        header("Location: ../index.php?error=Fill in the fields");
        // terminates the script
        exit();
    }