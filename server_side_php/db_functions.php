<?php

class DB_Functions {

    private $db;

    function __construct() {
        include_once './db_connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
		echo "Connection setup". $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    public function storeUser($name, $email, $gcm_regid) {
        // insert user into database
        $result = mysql_query("INSERT INTO humsafar_users(name, email, gcm_regid, created_at) VALUES('$name', '$email', '$gcm_regid', NOW())");
        // check for successful store
        if ($result) {
            // get user details
            $id = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM humsafar_users WHERE id = $id") or die(mysql_error());
            // return user details
            if (mysql_num_rows($result) > 0) {
                return mysql_fetch_array($result);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public function getUserByEmail($email) {
        $result = mysql_query("SELECT * FROM humsafar_users WHERE email = '$email' LIMIT 1");
        return $result;
    }

    public function getAllUsers() {
        $result = mysql_query("select * FROM humsafar_users");
        return $result;
    }

    public function isUserExisted($email, $password) {
        $result = mysql_query("SELECT email from humsafar_users WHERE email = '$email' & $password = '$password'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed
            return true;
        } else {
            // user not existed
            return false;
        }
    }
	
	public function getUserInfo($email, $password) {
		$result = mysql_query("SELECT `id`, `gcm_regid`, `email`, `name` from humsafar_users WHERE email = '$email' & $password = '$password'");
		return $result;
    }
	
	public function storeTripInfo($name, $source, $destination, $departure_time, $departure_day, $relaxation_time) {
	    $result = mysql_query("INSERT INTO `humsafar_trips` (`name`, `source`, `destination`, `departure_time`, `departure_day`, `relaxation_time`)
			VALUES('$name', '$source', '$destination', $departure_time, $departure_day, $relaxation_time)");
        // check for successful store
        if ($result) {
            // get user details
            $id = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM humsafar_users WHERE id = $id") or die(mysql_error());
            // return user details
            if (mysql_num_rows($result) > 0) {
                return mysql_fetch_array($result);
            } else {
                return false;
            }
        } else {
            return false;
        }
	}
	
	public function findPeopleNearby($name, $source, $destination, $departure_time, $departure_day, $relaxation_time) {
	    $result = mysql_query("SELECT `name`, `departure_time`, `departure_day`, `relaxation_time` FROM `humsafar_trips`
				WHERE `name` != '$name' AND `departure_time` BETWEEN ".( $departure_time - $relaxation_time)." AND ".( $departure_time + $relaxation_time).")");
        // check for successful store
        return $result;
	}
}

?>