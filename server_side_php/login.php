<?php

// response json
$json = array();

if (isset($_POST["email"]) && isset($_POST["password"])) {
    $email = $_POST["email"];
	$password = $_POST["password"];
	
    // Store user details in db
    include_once './db_functions.php';
    include_once './GCM.php';

    $db = new DB_Functions();
    $gcm = new GCM();

    $res = $db->isUserExisted($email, $password);
	
	if($res == true){
		$user_info = $db->getUserInfo($email, $password);
		$response["success"] = 1;
		$response["message"] = json_encode($user_info);
		echo json_encode($response);
	}
	else{
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "This email/password pair doesn't exist.";

		// echoing JSON response
		echo json_encode($response);
	}
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is(are) missing";

    // echoing JSON response
    echo json_encode($response);
}
?>