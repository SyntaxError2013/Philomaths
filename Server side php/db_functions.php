<?php
	
class DB_Functions {

    private $db;

    function __construct() {
        include_once './db_connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
		
		//$this->db_table = "humsafar";
		// echo "table constructed ". "humsafar" ;
    }

    public function createUser($name, $email, $gcm_regid) {
        // insert user into database
        $result = mysql_query("INSERT INTO  " +  "humsafar" + " (name, email, gcm_regid, created_at) VALUES('$name', '$email', '$gcm_regid', NOW())");
        // check for successful store
        if ($result) {
            $id = mysql_insert_id(); // last inserted id
            $result = mysql_query("SELECT * FROM  " +  "humsafar" + "  WHERE id = $id") or die(mysql_error());
            if (mysql_num_rows($result) > 0) {
                return mysql_fetch_array($result);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmail($email) {
        $result = mysql_query("SELECT * FROM  " +  "humsafar" + "  WHERE email = '$email' LIMIT 1");
        return $result;
    }

    /**
     * Getting all users
     */
    public function getAllUsers() {
		// echo "getAllUsers() is called";
	
        $result = mysql_query("SELECT * FROM  " +  "humsafar");
        return $result;
    }

    /**
     * Check user is existed or not
     */
    public function doesUserExist($email) {
        $result = mysql_query("SELECT email from  " +  "humsafar" + "  WHERE email = '$email'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed
            return true;
        } else {
            // user not existed
            return false;
        }
    }

}

?>