package com.example.jenshinwiki.Config;

public interface Config {

    public String serverIp = "192.168.1.11";    //IP dari Server untuk mengakses server local

    public String userLoginVerification = "http://"+ serverIp +"/JenshinWiki-ws/userLoginVerification.php/";
    public String userRegister          = "http://"+ serverIp +"/JenshinWiki-ws/userRegister.php/";
    public String userListRequest       = "";
    public String userUpdateRequest     = "";
}
