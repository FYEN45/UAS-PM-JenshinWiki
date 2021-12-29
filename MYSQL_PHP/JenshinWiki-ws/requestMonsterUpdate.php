<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;
    header("Content-Type:application/json");

    $parameter = array(
        'id' => $_POST['id'],
        'monster_name' => $_POST['monster_name'],
        'monster_description' => $_POST['monster_description'],
        'monster_image' => $_POST['monster_image'],
        'action' => $_POST['action']
    );

    if($parameter['action']=="add"){
        $query = "INSERT INTO `monsters` SET 
            `id`='".$parameter['id']."',
            `monster_name`='".$parameter['monster_name']."',
            `monster_description`='".$parameter['monster_description']."',
            `monster_image`='".$parameter['monster_image']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil menambahkan ".$parameter['monster_name'];
        } else {
            echo "Gagal menambahkan ".$parameter['monster_name'];
        }

    }else if($parameter['action']=="edit"){
        $query = "UPDATE `monsters` SET 
            `monster_name`='".$parameter['monster_name']."',
            `monster_description`='".$parameter['monster_description']."',
            `monster_image`='".$parameter['monster_image']."' 
            WHERE `id`='".$parameter['id']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil mengubah ".$parameter['monster_name'];
        } else {
            echo "Gagal mengubah ".$parameter['monster_name'];
        }

    } else if ($parameter['action']=="delete"){
        $query = "DELETE FROM `monsters` WHERE `id`='".$parameter['id']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil menghapus ".$parameter['monster_name'];
        } else {
            echo "Gagal menghapus ".$parameter['monster_name'];
        }
    }
    $db->close_DB();
?>