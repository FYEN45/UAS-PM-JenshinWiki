package com.example.jenshinwiki;

public class TempLoginData {
    //Menyimpan Data user yang berhasil melakukan login.
    public static String Temp_Username = "";
    public static String Temp_Name = "";
    public static String Temp_Status = "";

    //Method removeTempData digunakan untuk menghapus data user yang berhasil melakukan login.
    // Digunakan pada saat user melakukan Logout.
    public static void removeTempData() {
        Temp_Username = null;
        Temp_Name = null;
        Temp_Status = null;
    }
}
