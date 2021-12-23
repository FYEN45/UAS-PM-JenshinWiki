<?php
    error_reporting(0);
    require __DIR__. '/config.php';
    
    class DB_CONNECT {
        public $con;

        function __construct()
        {
            $this->connect_DB();
        }

        function __destruct()
        {
            $this->close_DB();
        }

        function connect_DB(){
            $this->con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            if(mysqli_connect_errno()){
                printf("Koneksi Gagal! \n", mysqli_connect_error());
                exit();
            }
            return $this->con;
        }

        function close_DB(){
            mysqli_close($this->con);
        }
    }
?>