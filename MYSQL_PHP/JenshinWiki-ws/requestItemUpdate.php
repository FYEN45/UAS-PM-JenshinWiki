<?php
    //import connect.php
    require __DIR__. '/connect.php';
    
    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");

    //Deklarasi array parameter yang memiliki input id, item_name, item_description, item_image, dan action dari aplikasi.
    $parameter = array(
        'id' => $_POST['id'],
        'item_name' => $_POST['item_name'],
        'item_description' => $_POST['item_description'],
        'item_image' => $_POST['item_image'],
        'action' => $_POST['action']
    );

    //apabila action berupa add
    if($parameter['action']=="add"){
        //Mencoba melakukan insert data item
        $query = "INSERT INTO `items` SET 
            `id`='".$parameter['id']."',
            `item_name`='".$parameter['item_name']."',
            `item_description`='".$parameter['item_description']."',
            `item_image`='".$parameter['item_image']."'";
            
        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil menambahkan, mengirimkan pesan Berhasil menambahkan data.
            echo "Berhasil menambahkan ".$parameter['item_name'];
        } else {
            //Apabila gagal menambahkan, mengirimkan pesan gagal menambahkan data.
            echo "Gagal menambahkan ".$parameter['item_name'];
        }
    
    //apabila action berupa edit
    }else if($parameter['action']=="edit"){
        //Mencoba melakukan update data item berdasarkan id yang sama dengan yang diterima php.
        $query = "UPDATE `items` SET 
            `item_name`='".$parameter['item_name']."',
            `item_description`='".$parameter['item_description']."',
            `item_image`='".$parameter['item_image']."' 
            WHERE `id`='".$parameter['id']."'";

        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil mengubah, mengirimkan pesan Berhasil mengubah data.
            echo "Berhasil mengubah ".$parameter['item_name'];
        } else {
            //Apabila gagal mengubah, mengirimkan pesan gagal mengubah data.
            echo "Gagal mengubah ".$parameter['item_name'];
        }
    
    //Apabila action berupa delete
    } else if ($parameter['action']=="delete"){
        //Mencoba menghapus item
        $query = "DELETE FROM `items` WHERE `id`='".$parameter['id']."'";

        if(mysqli_query($db->connect_DB(),$query)){
            //Apabila berhasil menghapus item, mengirimkan pesan berhasil menghapus.
            echo "Berhasil menghapus ".$parameter['item_name'];
        } else {
            //apabila gagal menghapus item, mengirimkan pesan gagal menghapus.
            echo "Gagal menghapus ".$parameter['item_name'];
        }
    }

    //Menutup koneksi dengan database
    $db->close_DB();
?>s