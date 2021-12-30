package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ItemDescriptionActivity extends AppCompatActivity {
    ImageView imageViewItem;
    TextView textViewIDItem;
    TextView textViewNameItem;
    TextView textViewDescriptionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        initViews(); //Memanggil function initViews
        receiveData(); //Memanggil function receiveData
    }

    private void initViews() {
        /*Mendeklarasikan variable yang berisi komponen yang ada
        pada layout activity_item_description*/
        imageViewItem = findViewById(R.id.description_itemImage);
        textViewIDItem = findViewById(R.id.description_itemId);
        textViewNameItem = findViewById(R.id.description_itemName);
        textViewDescriptionItem = findViewById(R.id.description_itemDestription);
    }

    private void receiveData() {
        /*Function untuk mengambil data dari intent sebelumnya, kemudian ditampilkan
        pada komponennya masing-masing*/
        Intent intent = getIntent();
        /*key id, name, description, image harus sama seperti saat mengirimkan (intent.putExtra)
        pada halaman intent sebelumnya*/
        textViewIDItem.setText(intent.getStringExtra("id"));
        textViewNameItem.setText(intent.getStringExtra("name"));
        textViewDescriptionItem.setText(intent.getStringExtra("description"));
        /*Mengkonversikan image yang tadinya berupa link url,
        sehingga dapat ditampilkan pada image view, memakai library Picasso*/
        String url = intent.getStringExtra("image");
        Picasso.get().load(url).into(imageViewItem);
    }
}