<?php
// KACPER POPIS

    // setting out variables to connect to database
    $host= "localhost";
    $username= "root";
    $password = "";
    $dbName = "eventplanningsite";

    // variables used in connecting to database: databasehost, username, password, databasename, port, socket
    $conn = mysqli_connect($host, $username, $password, $dbName);
    
    // checking the connection
    if (!$conn) {
        echo "Connection failed!";
    }
    else {
        echo "Connection found!";
    }