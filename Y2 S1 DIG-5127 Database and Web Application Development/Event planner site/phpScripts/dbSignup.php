<?php
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

        $sql = "SELECT * FROM customerdetails, businesses WHERE username = '$username'";

        $result = mysqli_query($conn, $sql);

        // checking if the username doesnt exists
        if (mysqli_num_rows($result) === 0) {
            // grabbing all the values and making them usable
            $username = validate($_POST['username']);
            $password = validate($_POST['password']);
            $passwordCon = validate($_POST['passwordConfirm']);
            $email = validate($_POST['email']);
            $tel = validate($_POST['tel']);
            $fName = validate($_POST['fName']);
            $lName = validate($_POST['lName']);
            $addLineOne = validate($_POST['addLineOne']);
            $postcode = validate($_POST['postcode']);

            if ($password === $passwordCon) {
                // creating the sql query
                if (count($_POST) === 9) {
                    $sql = "INSERT INTO `customerdetails` (`customerID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES (NULL, '$username', '$password', '$email', '$tel', '$addLineOne', '$postcode', '$fName', '$lName', current_timestamp())";
                }
                elseif (count($_POST) === 10) {
                    $sql = "INSERT INTO `businesses` (`businessID`, `username`, `password`, `email`, `phoneNum`, `addressLineOne`, `postCode`, `firstName`, `lastName`, `regDate`) VALUES (NULL, '$username', '$password', '$email', '$tel', '$addLineOne', '$postcode', '$fName', '$lName', current_timestamp())";
                }

                $result = mysqli_query($conn, $sql);

                echo "Account created";

                header("Location: ../login.php");
            }
            else {
                header("Location: ../signup.php?error=Passwords do not match");
                exit();
            }
        }
        else {
            header("Location: ../signup.php?error=Username is already taken");
        }
    }
    else {
        header("Location: ../signup.php");
        exit();
    }
