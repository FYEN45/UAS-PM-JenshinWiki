package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//MainActivity.java digunakan untuk menampilkan gambar dan tulisan dalam waktu 3 detik.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Membuat object runnable untuk menjalankan method intentLogin.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                intentLogin();
            }
        };

        //Membuat object handler dan meminta handler untuk melakukan delay selama 3 detik.
        // Setelah itu menjalankan perintah yang telah ditentukan runnable.
        Handler handler = new Handler();
        handler.postDelayed(r, 3000);

        //Mendeklarasikan, Menginisialisasikan, dan Memberikan fungsi kepada splash Text,
        // dimana saat splashText ditekan, menghentikan perintah yang dijalankan handler dan melakukan
        // intent ke halaman Login tanpa harus menunggu 3 detik.
        TextView splashText = findViewById(R.id.textViewSplash);
        splashText.setOnClickListener(v -> {
            handler.removeCallbacks(r);
            intentLogin();
        });
    }

    //Method intentLogin digunakan untuk melakukan perpindahan halaman dari halaman Splash
    // ke halaman Login.
    public void intentLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}