<?php
// Kacper Popis
    session_start();
    print($_SESSION['id']);
    session_destroy();
    $_SESSION = array();
    print($_SESSION['id']);

    header("Location: ../index.php");
    exit();