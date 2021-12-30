<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;
    header("Content-Type:application/json");

    $response = array(
        'error' => FALSE,
        'error_text' => "",
        'data' => array(),
    );

    $result = mysqli_query($db->connect_DB(), "SELECT * FROM monsters");
    if(mysqli_num_rows($result) > 0){
        $hasil = array();
        while($col = mysqli_fetch_assoc($result)){
            $product = array(
                'id' => $col['id'],
                'monster_name' => $col['monster_name'],
                'monster_description' => $col['monster_description'],
                'monster_image' => $col['monster_image']
            );
            $hasil[$col['id']] = $product;
            
            $response['error'] = FALSE;
            $response['error_text'] = "Berhasil";
            $response['data'] = $hasil;
        } 
    } else {
        $response['error'] = TRUE;
        $response['error_text'] = "No Monster is found";
    }

    echo json_encode($response, JSON_PRETTY_PRINT);
?>