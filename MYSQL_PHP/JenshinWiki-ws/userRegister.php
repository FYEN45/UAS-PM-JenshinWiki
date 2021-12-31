<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");


    //Mendeklarasikan variabel dan mengambil data - data yang dikirim dari aplikasi.
    $name = $_POST['name'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $status = "user"; //Status berisi "user", untuk menentukan status user saat melakukan registrasi.

    //Memeriksa apakah username sudah terdaftar didalam database.
    $query = mysqli_query($db->connect_DB(), "SELECT * FROM users WHERE username='$username'");
    if(mysqli_num_rows($query) > 0) {
        //Apabila sudah terdaftar, mengirimkan pesan Registrasi Gagal!.
        echo "Registrasi Gagal! Username sudah terdaftar.";
    } else {
        //Memeriksa apakah email sudah terdaftar didalam database.
        $query = mysqli_query($db->connect_DB(), "SELECT * FROM users WHERE email='$email'");
        if(mysqli_num_rows($query) > 0) {
            //Apabila sudah terdaftar, mengirimkan pesan Registrasi Gagal!.
            echo "Registrasi Gagal! Email sudah terdaftar.";
        } else {        
            //Apabila belum terdaftar, mendaftarkan nama, email, username, password, dan status untuk user baru.
            $insert = "INSERT INTO users (`name`, `email`, `username`, `password`, `status`) 
                    VALUES ('$name', '$email', '$username', '$password', '$status')";
            mysqli_query($db->connect_DB(), $insert);
            //Mengirimkan pesan "Registrasi Berhasil!"
            echo "Registrasi Berhasil!";
        }
    }
    
    //Menutup koneksi dengan database.
    $db->close_DB();
?>