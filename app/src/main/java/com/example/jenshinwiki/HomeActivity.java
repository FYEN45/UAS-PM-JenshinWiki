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

        initViews(); //Memanggil method initViews.
        enableCardviewForAdmin(); //Memanggil method enableCardviewForAdmin

        //Memberikan fungsi untuk card view boss untuk berpindah ke halaman Monster List.
        materialCardViewBoss.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MonsterListActivity.class);
            startActivity(intent);
        });

        //Memberikan fungsi untuk card view item untuk berpindah ke halaman Item List.
        materialCardViewItem.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, ItemListActivity.class);
            startActivity(intent);
        });

        //Memberikan fungsi untuk card view user list untuk berpindah ke halaman User List.
        materialCardViewUserlist.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
            startActivity(intent);
        });

        //Memberikan fungsi untuk FloatingActionButtonWebsite untuk mengarahkan user ke website
        // Genshin Impact Wiki menggunakan browser yang ada dalam device.
        FloatingActionButtonWebsite.setOnClickListener(view -> {
            String url = "https://genshin.honeyhunterworld.com/?lang=EN";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        //Memberikan fungsi untuk menu pada toolbar
        materialToolbar.setOnMenuItemClickListener(menuItem -> {
            //Apabila user menekan tombol Logout, user akan di arahkan ke halaman Login.
            // Data login sebelumnya akan dihapus.
            if (menuItem.getItemId() == R.id.menu_logout) {
                TempLoginData.removeTempData(); //Memanggil method removeTempData dari kelas TempLoginData.
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                //Flag pada intent ini digunakan untuk menyatakan halaman berikutnya (LoginActivity) sebagai
                // halaman utama, dan menghapus semua activity yang berjalan sebelumnya.
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

    //Method initViews digunakan untuk meng-inisialisasikan komponen - komponen dalam
    // layout sehingga java dapat mengakses komponen tersebut.
    private void initViews() {
        materialToolbar = findViewById(R.id.topAppBar);
        materialCardViewBoss = findViewById(R.id.materialCardViewBoss);
        materialCardViewItem = findViewById(R.id.materialCardViewItem);
        materialCardViewUserlist = findViewById(R.id.materialCardViewUserlist);
        FloatingActionButtonWebsite = findViewById(R.id.fab_addItem);
    }

    //Method enableCardviewForAdmin digunakan untuk memeriksa apakah user yang login berupa user atau admin.
    //Apabila user memiliki status admin, user akan memiliki akses untuk menekan card view user list.
    //Apabila user tidak memiliki status admin, card view User List akan dihilangkan.
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