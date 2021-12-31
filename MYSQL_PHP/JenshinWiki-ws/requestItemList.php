<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");

    //Mendeklarasikan array response untuk mengirim data-data Item yang akan ditampilkan di ItemList.
    $response = array(
        'error' => FALSE,
        'error_text' => "",
        'data' => array(),
    );

    //Mengambil seluruh data item yang ada dalam database
    $result = mysqli_query($db->connect_DB(), "SELECT * FROM items");
    //Apabila data item lebih dari 0, mengirimkan semua data tersebut ke aplikasi.
    if(mysqli_num_rows($result) > 0){
        $hasil = array();
        //Mengambil data item yang diminta dari database dan memasukkannya kedalam array product
        while($col = mysqli_fetch_assoc($result)){
            $product = array(
                'id' => $col['id'],
                'item_name' => $col['item_name'],
                'item_description' => $col['item_description'],
                'item_image' => $col['item_image']
            );
            //Memasukkan array product ke dalam array hasil.
            $hasil[$col['id']] = $product;

            //Menyiapkan pesan Berhasil dan data-data item
            $response['error'] = FALSE;
            $response['error_text'] = "Berhasil";
            $response['data'] = $hasil;
        } 
    } else {
        //Apabila tidak terdapat data item sama sekali, mempersiapkan pesan "Tidak ada item yang ditemukan!"
        $response['error'] = TRUE;
        $response['error_text'] = "No Item is found";
    }

    //Menutup koneksi dengan database
    $db->close_DB();

    //Mengirim respon ke aplikasi.
    echo json_encode($response, JSON_PRETTY_PRINT);
?>