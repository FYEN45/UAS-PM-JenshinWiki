package com.example.jenshinwiki.Config;

public interface Config {

    //Penjelasan : serverIp digunakan untuk menentukan alamat IP untuk mengakses webservices.
    String serverIp = "192.168.1.11";

    //Penjelasan : Baris - baris kode berikut digunakan untuk menghubungkan aplikasi dengan webservices
    // sehingga aplikasi dapat mengirim maupun menerima respon dari server yang dapat mengakses database MySQL.
    String userLoginVerification = "http://" + serverIp + "/JenshinWiki-ws/userLoginVerification.php/";
    String userRegister = "http://" + serverIp + "/JenshinWiki-ws/userRegister.php/";
    String requestUserList = "http://" + serverIp + "/JenshinWiki-ws/requestUserList.php/";
    String requestUserUpdate = "http://" + serverIp + "/JenshinWiki-ws/requestUserUpdate.php/\";";
}
