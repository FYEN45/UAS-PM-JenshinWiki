<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");

    //Mendeklarasikan array response untuk mengirim data-data Monster yang akan ditampilkan di MonsterList.
    $response = array(
        'error' => FALSE,
        'error_text' => "",
        'data' => array(),
    );

    //Mengambil seluruh data monster yang ada dalam database
    $result = mysqli_query($db->connect_DB(), "SELECT * FROM monsters");
    //Apabila data monster lebih dari 0, mengirimkan semua data tersebut ke aplikasi.
    if(mysqli_num_rows($result) > 0){
        $hasil = array();
        //Mengambil data monster yang diminta dari database dan memasukkannya kedalam array product
        while($col = mysqli_fetch_assoc($result)){
            $product = array(
                'id' => $col['id'],
                'monster_name' => $col['monster_name'],
                'monster_description' => $col['monster_description'],
                'monster_image' => $col['monster_image']
            );
            //Memasukkan array product ke dalam array hasil.
            $hasil[$col['id']] = $product;
            
            //Menyiapkan pesan Berhasil dan data-data monster
            $response['error'] = FALSE;
            $response['error_text'] = "Berhasil";
            $response['data'] = $hasil;
        } 
    } else {
        //Apabila tidak terdapat data monster sama sekali, mempersiapkan pesan "Tidak ada monster yang ditemukan!"
        $response['error'] = TRUE;
        $response['error_text'] = "No Monster is found";
    }

    //Menutup koneksi dengan database
    $db->close_DB();

    //Mengirim respon ke aplikasi.
    echo json_encode($response, JSON_PRETTY_PRINT);
?>