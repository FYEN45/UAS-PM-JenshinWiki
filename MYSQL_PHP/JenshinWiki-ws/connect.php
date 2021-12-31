<?php
    //Connect.php digunakan untuk menghubungkan php dengan database.

    error_reporting(0); //Tidak mempedulikan error yang terjadi dalam php.
    
    //Import config.php
    require __DIR__. '/config.php';
    
    class DB_CONNECT {
        //$con adalah variabel yang akan mendefinisikan koneksi dengan database
        public $con;

        //Function ketika php di jalankan.
        function __construct()
        {
            $this->connect_DB(); //Menjalankan function connect_DB
        }

        //Function ketika php di hentikan.
        function __destruct()
        {
            $this->close_DB(); //Menjalankan function close_DB
        }

        //Function yang digunakan untuk menghubungkan php dengan database
        function connect_DB(){
            $this->con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            if(mysqli_connect_errno()){
                //Apabila koneksi gagal dilakukan, akan melakukan print "Koneksi Gagal!"
                printf("Koneksi Gagal! \n", mysqli_connect_error());
                exit();
            }
            return $this->con;
        }

        //function yang digunakan untuk menutup koneksi dengan database
        function close_DB(){
            mysqli_close($this->con);
        }
    }
?>