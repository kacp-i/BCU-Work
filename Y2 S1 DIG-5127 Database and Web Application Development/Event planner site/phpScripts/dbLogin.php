<?php
    // starts a session
    session_start();

    // grabs the connection file
    include "dbConnection.php";

    // checks if the inputs arent NULL
    if (isset($_POST['username']) && isset($_POST['password'])) {

        // function to remove "noise"
        function validate($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        // grabbing all the values and making them usable
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
            // trying the customer table first
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
                    $_SESSION['accType'] = "customer";
                    
                    // redirect
                    header("Location: ../index.php");
                    exit();
                }
                else {
                    // redirect to login page with an error
                    header("Location: ../login.php?error=Incorect User name or password");
                    exit();
                }
            }
            else {
                // trying the business table
                // creating the sql query
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
                        $_SESSION['id'] = $row['id'];
                        $_SESSION['accType'] = "business";
                        
                        // redirect
                        header("Location: ../index.php");
                        exit();
                    }
                    else {
                        // redirect to login page with an error
                        header("Location: ../login.php?error=Incorect User name or password");
                        exit();
                    }
                }
                else {
                    // redirect to login page with an error
                    header("Location: ../login.php?error=Incorect User name or password");
                    exit();
                }
            }
        }
    }
    else {
        header("Location: ../index.php");
        exit();
    }
