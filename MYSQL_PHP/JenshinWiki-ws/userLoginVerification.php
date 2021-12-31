<?php
    //import connect.php
    require __DIR__. '/connect.php';
    
    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");

    //Mendeklarasikan variabel dan mengambil data - data yang dikirim dari aplikasi
    $username = $_POST['username'];
    $password = $_POST['password'];

    //Deklarasi array response.
    $response = array(
        'user' => null,
        'pesan' => null
    );

    //Memeriksa apakah username dan password yang diinput user sesuai dengan data yang ada dalam database
    $query = mysqli_query($db->connect_DB(), "SELECT * FROM users WHERE username='$username' AND password='$password'");
    
    //Apabila sesuai, diberikan akses untuk login.
    if(mysqli_num_rows($query) > 0){
        //Mengambil data yang ditemukan
        $col = mysqli_fetch_assoc($query);
        
        //Memasukkan data yang ditemukan kedalam array user untuk dikirim kembali ke aplikasi.
        $user = array(
            'name' => $col['name'],
            'username' => $col['username'],
            'status' => $col['status']
        );

        //Menyiapkan response data user
        $response['user'] = $user;
        //Memberikan pesan Login berhasil untuk ditampilkan.
        $response['pesan'] = "Login Berhasil!";
    } else {
        //Apabila username dan password yang diinput tidak sesuai dengan data dalam database, mengirim pesan "Login Gagal!"
        $response['pesan'] = "Login Gagal!";
    }

    //Menutup koneksi database
    $db->close_DB();

    //Mengirim response ke aplikasi
    echo json_encode($response);
?>