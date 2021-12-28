<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;
    header("Content-Type:application/json");

    $parameter = array(
        'id' => $_POST['id'],
        'name' => $_POST['name'],
        'email' => $_POST['email'],
        'username' => $_POST['username'],
        'password' => $_POST['password'],
        'status' => $_POST['status'],
        'action' => $_POST['action']
    );

    if($parameter['action']=="edit"){
        $query = "UPDATE `users` SET 
            `name`='".$parameter['name']."',
            `status`='".$parameter['status']."',
            `password`='".$parameter['password']."' 
            WHERE `id`='".$parameter['id']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil mengubah ".$parameter['name'];
        } else {
            echo "Gagal mengubah ".$parameter['name'];
        }

    } else if ($parameter['action']=="delete"){
        $query = "DELETE FROM `users` WHERE `id`=".$parameter['id'];
        if(mysqli_query($db->connect_DB(),$query)){
            echo "Berhasil menghapus ".$parameter['name'];
        } else {
            echo "Gagal menghapus ".$parameter['name'];
        }
    }
    $db->close_DB();
?>