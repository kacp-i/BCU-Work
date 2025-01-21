<?php
    // starts a session
    session_start();

    // grabs the connection file
    include "dbConnection.php";

    // checks if the inputs are NULL
    if (isset($_POST['username']) && isset($_POST['password'])) {

        // function to validate data / remove "noise"
        function validate($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        // validating the username and password values
        $username = validate($_POST['username']);
        $password = validate($_POST['password']);

        // if the username is empty, give an error
        if (empty($username)) {
            header("Location: ../index.php?error=User Name is required");
            exit();
        }
        // if the password is empty, give an error
        else if(empty($password)) {
            header("Location: ../index.php?error=Password is required");
            exit();
        }
        else {
            // creating the sql query
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
                    $_SESSION['id'] = $row['id'];
                    $_SESSION['type'] = 0;
                    
                    header("Location: ../index.php");
                    exit();
                }
                else {
                    header("Location: ../index.php?error=Incorect User name or password");
                    exit();
                }
            }
            else {
                header("Location: ../index.php?error=Incorect User name or password");
                exit();
            }
        }
    }
    else {
        header("Location: ../index.php");
        exit();
    }
