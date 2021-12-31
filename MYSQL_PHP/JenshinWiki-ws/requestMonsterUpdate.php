<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");

    //Deklarasi array parameter yang memiliki input id, monster_name, monster_description, monster_image, dan action dari aplikasi.
    $parameter = array(
        'id' => $_POST['id'],
        'monster_name' => $_POST['monster_name'],
        'monster_description' => $_POST['monster_description'],
        'monster_image' => $_POST['monster_image'],
        'action' => $_POST['action']
    );

    //apabila action berupa add
    if($parameter['action']=="add"){
        //Mencoba melakukan insert data monster
        $query = "INSERT INTO `monsters` SET 
            `id`='".$parameter['id']."',
            `monster_name`='".$parameter['monster_name']."',
            `monster_description`='".$parameter['monster_description']."',
            `monster_image`='".$parameter['monster_image']."'";

        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil menambahkan, mengirimkan pesan Berhasil menambahkan data.
            echo "Berhasil menambahkan ".$parameter['monster_name'];
        } else {
            //Apabila gagal menambahkan, mengirimkan pesan gagal menambahkan data.
            echo "Gagal menambahkan ".$parameter['monster_name'];
        }

    //apabila action berupa edit
    }else if($parameter['action']=="edit"){
        //Mencoba melakukan update data monster berdasarkan id yang sama dengan yang diterima php.
        $query = "UPDATE `monsters` SET 
            `monster_name`='".$parameter['monster_name']."',
            `monster_description`='".$parameter['monster_description']."',
            `monster_image`='".$parameter['monster_image']."' 
            WHERE `id`='".$parameter['id']."'";

        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil mengubah, mengirimkan pesan Berhasil mengubah data.
            echo "Berhasil mengubah ".$parameter['monster_name'];
        } else {
            //Apabila gagal mengubah, mengirimkan pesan gagal mengubah data.
            echo "Gagal mengubah ".$parameter['monster_name'];
        }

    //Apabila action berupa delete
    } else if ($parameter['action']=="delete"){
        //Mencoba menghapus monster
        $query = "DELETE FROM `monsters` WHERE `id`='".$parameter['id']."'";

        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil menghapus monster, mengirimkan pesan berhasil menghapus.
            echo "Berhasil menghapus ".$parameter['monster_name'];
        } else {
            //apabila gagal menghapus monster, mengirimkan pesan gagal menghapus.
            echo "Gagal menghapus ".$parameter['monster_name'];
        }
    }

    //Menutup koneksi dengan database
    $db->close_DB();
?>