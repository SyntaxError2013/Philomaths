<?php

// response json
$json = array();

if (isset($_POST["user_id"]) && isset($_POST["source"]) && isset($_POST["destination"])
		&& isset($_POST["departure_time"]) && isset($_POST["departure_day"]) && isset($_POST["relaxation_time"])) {
    $name = $_POST["user_id"];
    $source = $_POST["source"];
    $destination = $_POST["destination"];
    $departure_time = $_POST["departure_time"];
    $departure_day = $_POST["departure_day"];
    $relaxation_time = $_POST["relaxation_time"];
   
	include_once './db_functions.php';
    include_once './GCM.php';

    $db = new DB_Functions();
    $gcm = new GCM();

	$res = $db->findPeopleNearby($name, $source, $destination, $departure_time, $departure_day, $relaxation_time);

	$response["success"] = 1;
	$response["message"] = json_encode($res);

	// echoing JSON response
	echo json_encode($response);
	
    //echo $result;
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is(are) missing";

    // echoing JSON response
    echo json_encode($response);
}
?>