package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //deklarasi dan inisialisasi komponen pada UI activity_home
        MaterialCardView materialCardViewBoss               = findViewById(R.id.materialCardViewBoss);
        MaterialCardView materialCardViewItem               = findViewById(R.id.materialCardViewItem);
        MaterialCardView materialCardViewUserlist           = findViewById(R.id.materialCardViewUserlist);
        FloatingActionButton FloatingActionButtonWebsite    = findViewById(R.id.floatingActionButtonWebsite);

        //intent materialcard di activity home ke halaman list boss
        materialCardViewBoss.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, .class);
            startActivity(intent);
            finish();
        });

        //intent materialcard item dari activity home ke halaman list item
        materialCardViewItem.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, .class);
            startActivity(intent);
            finish();
        });

        //intent materialcard userlist dari activity home ke halaman userlist
        materialCardViewUserlist.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
            startActivity(intent);
            finish();
        });

        //intent fab ke URL genshin impact
        FloatingActionButtonWebsite.setOnClickListener(view -> {
            String url = "https://genshin.honeyhunterworld.com/?lang=EN";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });


    }
}