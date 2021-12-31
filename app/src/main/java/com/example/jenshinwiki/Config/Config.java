package com.example.jenshinwiki.Config;

public interface Config {

    //Variabel serverIp digunakan untuk menentukan alamat IP untuk mengakses webservices.
    String serverIp = "192.168.159.202";

    //Baris - baris kode berikut digunakan untuk menghubungkan aplikasi dengan webservices
    // sehingga aplikasi dapat mengirim maupun menerima respon dari server yang dapat mengakses database MySQL.
    String userLoginVerification = "http://" + serverIp + "/JenshinWiki-ws/userLoginVerification.php/";
    String userRegister = "http://" + serverIp + "/JenshinWiki-ws/userRegister.php/";
    String requestUserList = "http://" + serverIp + "/JenshinWiki-ws/requestUserList.php/";
    String requestUserUpdate = "http://" + serverIp + "/JenshinWiki-ws/requestUserUpdate.php/";
    String requestMonsterList = "http://" + serverIp + "/JenshinWiki-ws/requestMonsterList.php/";
    String requestMonsterUpdate = "http://" + serverIp + "/JenshinWiki-ws/requestMonsterUpdate.php/";
    String requestItemList = "http://" + serverIp + "/JenshinWiki-ws/requestItemList.php/";
    String requestItemUpdate = "http://" + serverIp + "/JenshinWiki-ws/requestItemUpdate.php/";
}