package com.example.jenshinwiki;

public class TempLoginData {
    //Menyimpan Data user yang melakukan login
    public static String Temp_Username = "";
    public static String Temp_Name = "";
    public static String Temp_Status = ""; //admin & user

    public static void removeTempData() {
        Temp_Username = null;
        Temp_Name = null;
        Temp_Status = null;
    }
}
