<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");


    //Mendeklarasikan array response untuk mengirim data data user yang akan ditampilkan di user list.
    $response = array(
        'error' => FALSE,
        'error_text' => "",
        'data' => array(),
    );

    //Mengambil seluruh data user yang ada dalam database
    $result = mysqli_query($db->connect_DB(), "SELECT * FROM users");
    //Apabila data user lebih dari 0, mengirimkan semua data tersebut ke aplikasi.
    if(mysqli_num_rows($result) > 0){
        $hasil = array();
        //Mengambil data user yang diminta dari database dan memasukkannya kedalam array product
        while($col = mysqli_fetch_assoc($result)){
            $product = array(
                'id' => $col['id'],
                'name' => $col['name'],
                'email' => $col['email'],
                'username' => $col['username'],
                'password' => $col['password'],
                'status' => $col['status']
            );
            //Memasukkan array product ke dalam array hasil.
            $hasil[$col['id']] = $product;

            //Menyiapkan pesan Berhasil dan data data user
            $response['error'] = FALSE;
            $response['error_text'] = "Berhasil";
            $response['data'] = $hasil;
        } 
    } else {
        //Apabila tidak terdapat data user sama sekali, mempersiapkan pesan "Tidak ada user yang ditemukan!"
        $response['error'] = TRUE;
        $response['error_text'] = "Tidak ada user yang ditemukan!";
    }
    
    //Menutup koneksi dengan database
    $db->close_DB();
    
    //Mengirim respon ke aplikasi.
    echo json_encode($response, JSON_PRETTY_PRINT);
?>