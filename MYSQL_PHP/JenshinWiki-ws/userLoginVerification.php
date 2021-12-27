<?php
    require __DIR__. '/connect.php';
    $db = new DB_CONNECT;

    header("Content-Type:application/json");
    $username = $_POST['username'];
    $password = $_POST['password'];

    $response = array(
        'user' => null,
        'pesan' => null
    );
    
    $query = mysqli_query($db->connect_DB(), "SELECT * FROM users WHERE username='$username' AND password='$password'");
    if(mysqli_num_rows($query) > 0){
        $col = mysqli_fetch_assoc($query);
        
        $user = array(
            'name' => $col['name'],
            'username' => $col['username'],
            'status' => $col['status']
        );

        $response['user'] = $user;
        $response['pesan'] = "Login Berhasil!";
    } else {
        $response['pesan'] = "Login Gagal!";
    }

    echo json_encode($response);
?>