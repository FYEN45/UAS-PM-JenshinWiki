<?php
    //import connect.php
    require __DIR__. '/connect.php';

    //membuat object $db
    $db = new DB_CONNECT;

    //Menentukan tipe konten, berupa JSON
    header("Content-Type:application/json");


    //Deklarasi array parameter yang memiliki input id, name, email, username, password, status, dan action dari aplikasi.
    $parameter = array(
        'id' => $_POST['id'],
        'name' => $_POST['name'],
        'email' => $_POST['email'],
        'username' => $_POST['username'],
        'password' => $_POST['password'],
        'status' => $_POST['status'],
        'action' => $_POST['action']
    );

    //apabila action berupa edit
    if($parameter['action']=="edit"){
        //Mencoba melakukan update data user berdasarkan id yang sama dengan yang diterima php.
        $query = "UPDATE `users` SET 
            `name`='".$parameter['name']."',
            `status`='".$parameter['status']."',
            `password`='".$parameter['password']."' 
            WHERE `id`='".$parameter['id']."'";
        
        if(mysqli_query($db->connect_DB(), $query)){
            //Apabila berhasil mengubah, mengirimkan pesan Berhasil mengubah data.
            echo "Berhasil mengubah ".$parameter['name'];
        } else {
            //Apabila gagal mengubah, mengirimkan pesan gagal mengubah data.
            echo "Gagal mengubah ".$parameter['name'];
        }

    //Apabila action berupa delete
    } else if ($parameter['action']=="delete"){
        //Mencoba menghapus user
        $query = "DELETE FROM `users` WHERE `id`=".$parameter['id'];

        
        if(mysqli_query($db->connect_DB(),$query)){
            //Apabila berhasil menghapus user, mengirimkan pesan berhasil menghapus.
            echo "Berhasil menghapus ".$parameter['name'];
        } else {
            //apabila gagal menghapus user, mengirimkan pesan gagal menghapus.
            echo "Gagal menghapus ".$parameter['name'];
        }
    }

    //Menutup koneksi dengan database
    $db->close_DB();
?>