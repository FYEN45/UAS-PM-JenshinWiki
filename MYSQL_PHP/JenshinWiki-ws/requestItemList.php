<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;
    header("Content-Type:application/json");

    $response = array(
        'error' => FALSE,
        'error_text' => "",
        'data' => array(),
    );

    $result = mysqli_query($db->connect_DB(), "SELECT * FROM items");
    if(mysqli_num_rows($result) > 0){
        $hasil = array();
        while($col = mysqli_fetch_assoc($result)){
            $product = array(
                'id' => $col['id'],
                'item_name' => $col['item_name'],
                'item_description' => $col['item_description'],
                'item_image' => $col['item_image']
            );
            $hasil[$col['id']] = $product;

            $response['error'] = FALSE;
            $response['error_text'] = "Berhasil";
            $response['data'] = $hasil;
        } 
    } else {
        $response['error'] = TRUE;
        $response['error_text'] = "No Item is found";
    }

    echo json_encode($response, JSON_PRETTY_PRINT);
?>