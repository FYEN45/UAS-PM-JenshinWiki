package com.example.jenshinwiki;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    MaterialCardView materialCardViewBoss;
    MaterialCardView materialCardViewItem;
    MaterialCardView materialCardViewUserlist;
    FloatingActionButton FloatingActionButtonWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        enableCardviewForAdmin();

        //intent materialcard di activity home ke halaman list boss
        materialCardViewBoss.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MonsterListActivity.class);
            startActivity(intent);
        });

        //intent materialcard item dari activity home ke halaman list item
        materialCardViewItem.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, ItemListActivity.class);
            startActivity(intent);
        });

        materialCardViewUserlist.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
            startActivity(intent);
        });

        FloatingActionButtonWebsite.setOnClickListener(view -> {
            String url = "https://genshin.honeyhunterworld.com/?lang=EN";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        materialToolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.menu_logout) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
    }

    private void initViews() {
        materialToolbar = findViewById(R.id.topAppBar);
        materialCardViewBoss = findViewById(R.id.materialCardViewBoss);
        materialCardViewItem = findViewById(R.id.materialCardViewItem);
        materialCardViewUserlist = findViewById(R.id.materialCardViewUserlist);
        FloatingActionButtonWebsite = findViewById(R.id.fab_addItem);
    }

    private void enableCardviewForAdmin() {
        if (TempLoginData.Temp_Status.equals("admin")) {
            materialCardViewUserlist.setVisibility(View.VISIBLE);
            materialCardViewUserlist.setEnabled(true);
        } else {
            materialCardViewUserlist.setVisibility(View.GONE);
            materialCardViewUserlist.setEnabled(false);
        }
    }
}