<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;
    header("Content-Type:application/json");

    $parameter = array(
        'id' => $_POST['id'],
        'item_name' => $_POST['item_name'],
        'item_description' => $_POST['item_description'],
        'item_image' => $_POST['item_image'],
        'action' => $_POST['action']
    );

    if($parameter['action']=="add"){
        $query = "INSERT INTO `items` SET 
            `id`='".$parameter['id']."',
            `item_name`='".$parameter['item_name']."',
            `item_description`='".$parameter['item_description']."',
            `item_image`='".$parameter['item_image']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil menambahkan ".$parameter['item_name'];
        } else {
            echo "Gagal menambahkan ".$parameter['item_name'];
        }
    }else if($parameter['action']=="edit"){
        $query = "UPDATE `items` SET 
            `item_name`='".$parameter['item_name']."',
            `item_description`='".$parameter['item_description']."',
            `item_image`='".$parameter['item_image']."' 
            WHERE `id`='".$parameter['id']."'";
        if(mysqli_query($db->connect_DB(), $query)){
            echo "Berhasil mengubah ".$parameter['item_name'];
        } else {
            echo "Gagal mengubah ".$parameter['item_name'];
        }
    } else if ($parameter['action']=="delete"){
        $query = "DELETE FROM `items` WHERE `id`='".$parameter['id']."'";
        if(mysqli_query($db->connect_DB(),$query)){
            echo "Berhasil menghapus ".$parameter['item_name'];
        } else {
            echo "Gagal menghapus ".$parameter['item_name'];s
        }
    }
    $db->close_DB();
?>