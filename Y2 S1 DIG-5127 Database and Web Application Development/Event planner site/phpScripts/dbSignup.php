<?php
// KACPER POPIS

    // grabs the connection file
    include "dbConnection.php";

    // checks if the inputs arent NULL
    if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['passwordConfirm']) && isset($_POST['email']) && isset($_POST['tel']) && isset($_POST['fName']) && isset($_POST['lName']) && isset($_POST['addLineOne']) && isset($_POST['postcode'])) {

        // function to remove "noise"
        function validate($data) {
            $data = trim($data);
            $data = stripslashes($data);
            $data = htmlspecialchars($data);
            return $data;
        }

        // grabbing the username and clearing any "noise"
        $username = validate($_POST['username']);

        // sql statement to grab all the rows from the customerdetails table with matching data
        $sql = "SELECT * FROM customerdetails WHERE username = '$username'";

        // performing the query and storing results
        $result = mysqli_query($conn, $sql);

        // if the variable has 0 rows, it checks the business table to see if the username is taken
        if (mysqli_num_rows($result) === 0) {
            // sql statement to grab all the rows from the businesses table with matching data
            $sql = "SELECT * FROM businesses WHERE username = '$username'";

            // performs the query and storing results
            $result = mysqli_query($conn, $sql);
            
            // if the variable has 0 rows, the remainder of the data input by the user is validated
            if (mysqli_num_rows($result) === 0) {
                // grabbing all the values and removing "noise" from them
                $password = validate($_POST['password']);
                $passwordCon = validate($_POST['passwordConfirm']);
                $email = validate($_POST['email']);
                $tel = validate($_POST['tel']);
                $fName = validate($_POST['fName']);
                $lName = validate($_POST['lName']);
                $addLineOne = validate($_POST['addLineOne']);
                $postcode = validate($_POST['postcode']);

                // checking if the password is the same as the password confirmation (the password retyped by the user)
                if ($password === $passwordCon) {

                    // the post variable will contain 9 elements if the user selects a customer account
                    if (count($_POST) === 9) {
                        // creating the sql statement to insert the user data into the customerdetails table
                        $sql = "INSERT INTO `customerdetails` (`customerID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES (NULL, '$username', '$password', '$email', '$tel', '$addLineOne', '$postcode', '$fName', '$lName', current_timestamp())";
                    }
                    // the post variable holds 10 elements if the user selects a business account
                    // this is due to the slider - as when its checked it is also submitted in the form
                    elseif (count($_POST) === 10) {
                        // creating the sql statement to insert the user data into the businesses table
                        $sql = "INSERT INTO `businesses` (`businessID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES (NULL, '$username', '$password', '$email', '$tel', '$addLineOne', '$postcode', '$fName', '$lName', current_timestamp())";
                    }

                    // performs the sql query
                    mysqli_query($conn, $sql);

                    // message to show an account has been made
                    echo "Account created";

                    // redirects the user to a page to confirm account creation
                    header("Location: ../smallRedirects/accountConfirm.html");

                    // terminates the script
                    exit();
                }
                // if the passwords dont match, redirect the user to the signup page with an error
                else {
                    header("Location: ../signup.php?error=Passwords do not match");
                    // terminates the script
                    exit();
                }
            }
            // if the number of rows isnt 0, redirects the user to the signup page with an error
            else {
                header("Location: ../signup.php?error=Username is already taken");
                // terminates the script
                exit();
            }
        }
        // if the number of rows isnt 0, redirects the user to the signup page with an error
        else {
            header("Location: ../signup.php?error=Username is already taken");
            // terminates the script
            exit();
        }        
    }
    // if the inputs are NULL, redirects the user to the signup page with an error
    else {
        header("Location: ../signup.php?error=Fill in the fields of the form");
        // terminates the script
        exit();
    }