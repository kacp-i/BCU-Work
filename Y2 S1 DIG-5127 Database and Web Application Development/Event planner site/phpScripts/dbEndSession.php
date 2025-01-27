<?php
// KACPER POPIS

    // resumes an ongoing session
    session_start();

    // clears the session global variable
    session_destroy();

    // sets the session global variable to an empty array
    $_SESSION = array();

    // after logging out, it redirects the user to the homepage
    header("Location: ../index.php");

    // terminates the current script
    exit();