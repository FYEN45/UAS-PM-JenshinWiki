package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MonsterDescriptionActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textViewID;
    TextView textViewName;
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_description);

        initViews(); //Memanggil function initViews
        receiveData(); //Memanggil function receiveData
    }

    private void initViews() {
        /*Mendeklarasikan variable yang berisi komponen yang ada
        pada layout activity_monster_description*/
        imageView = findViewById(R.id.description_monsterImage);
        textViewID = findViewById(R.id.description_monsterId);
        textViewName = findViewById(R.id.description_monsterName);
        textViewDescription = findViewById(R.id.description_monsterDestription);
    }

    private void receiveData() {
        /*Function untuk mengambil data dari intent sebelumnya, kemudian ditampilkan
        pada komponennya masing-masing*/
        Intent intent = getIntent();
        /*key id, name, description, image harus sama seperti saat mengirimkan (intent.putExtra)
        pada halaman intent sebelumnya*/
        textViewID.setText(intent.getStringExtra("id"));
        textViewName.setText(intent.getStringExtra("name"));
        textViewDescription.setText(intent.getStringExtra("description"));
        /*Mengkonversikan image yang tadinya berupa link url,
        sehingga dapat ditampilkan pada image view, memakai library Picasso*/
        String url = intent.getStringExtra("image");
        Picasso.get().load(url).into(imageView);
    }
}