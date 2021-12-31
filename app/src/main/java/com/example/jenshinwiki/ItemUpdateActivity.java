package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

//MonsterUpdateActivity dibuat untuk menambahkan, mengedit, maupun menghapus data Monster pada database
public class ItemUpdateActivity extends AppCompatActivity {
    //Penginisiasian variable - variable untuk pemanggilan komponen pada layout nantinya
    MaterialToolbar materialToolbar;
    ImageView imageViewUpdateItem;
    ImageView checkLinkImageItem;
    TextInputLayout textInputLayoutItemUpdateImage;
    TextInputLayout textInputLayoutItemUpdateId;
    TextInputLayout textInputLayoutItemUpdateName;
    TextInputLayout textInputLayoutItemUpdateDescription;
    EditText editTextItemUpdateImage;
    EditText editTextItemUpdateId;
    EditText editTextItemUpdateName;
    EditText editTextItemUpdateDescription;
    Button btnAddItem, btnSaveItem, btnDeleteItem;
    String action; //Untuk mengatur action add, edit, atau delete, untuk dikirim ke .php
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);

        initViews(); //Pemanggilan function initViews
        receiveData(); //Pemanggilan function receiveData

        //Fungsi button ini saat diklik adalah untuk mengecek, apakah link yang dimasukan
        //dapat tampil pada imageView
        checkLinkImageItem.setOnClickListener(v -> {
            String url = String.valueOf(editTextItemUpdateImage.getText());
            //Library Picasso untuk pengkonversian dari link url tersebut ke imageview
            Picasso.get().load(url).into(imageViewUpdateItem);
        });

        btnAddItem.setOnClickListener(v -> {
            action = "add"; //Memberikan nilai action = add, karena untuk button add data baru
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });

        btnSaveItem.setOnClickListener(v -> {
            action = "edit"; //Memberikan nilai action = edit, karena untuk button save/update data
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });

        btnDeleteItem.setOnClickListener(v -> {
            action = "delete"; //Memberikan nilai action = delete, karena untuk button delete data
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });
    }

    private void initViews() {
        /*Mendeklarasikan variable yang berisi komponen yang ada
        pada layout activity_monster_update*/
        imageViewUpdateItem = findViewById(R.id.update_itemImage);
        checkLinkImageItem = findViewById(R.id.checkLinkImageItem);
        textInputLayoutItemUpdateImage = findViewById(R.id.TextInputLayoutItemUpdateImage);
        textInputLayoutItemUpdateId = findViewById(R.id.TextInputLayoutItemUpdateId);
        textInputLayoutItemUpdateName = findViewById(R.id.TextInputLayoutItemUpdateName);
        textInputLayoutItemUpdateDescription = findViewById(R.id.TextInputLayoutItemUpdateDescription);
        editTextItemUpdateImage = findViewById(R.id.editTextItemUpdateImage);
        editTextItemUpdateId = findViewById(R.id.editTextItemUpdateId);
        editTextItemUpdateName = findViewById(R.id.editTextItemUpdateName);
        editTextItemUpdateDescription = findViewById(R.id.editTextItemUpdateDescription);
        btnAddItem = findViewById(R.id.btnAddItem);
        btnSaveItem = findViewById(R.id.btnSaveItem);
        btnDeleteItem = findViewById(R.id.btnDeleteItem);
        materialToolbar = findViewById(R.id.topAppBar);

        intent = getIntent();
        bundle = intent.getExtras();
    }

    private void receiveData() {
        //Jika data yang diterima tidak kosong, maka akan menampilkan data-data yang ada pada bundle
        if (bundle != null) {
            Intent intent = getIntent();
            //editText Id kita setEnabled'nya false agar tidak bisa diupdate pada kolom ID'nya
            editTextItemUpdateId.setEnabled(false);
            //Memberikan data yang ada pada intent ke komponennya masing-masing
            editTextItemUpdateId.setText(intent.getStringExtra("id"));
            editTextItemUpdateName.setText(intent.getStringExtra("name"));
            editTextItemUpdateDescription.setText(intent.getStringExtra("description"));
            editTextItemUpdateImage.setText(intent.getStringExtra("image"));
            //Pengkonversian dari link url ke imageView menggunakan library Picasso
            String url = intent.getStringExtra("image");
            Picasso.get().load(url).into(imageViewUpdateItem);
            //Button add dibuat GONE karena bundle ada datanya, sehingga berfungsi untuk update/delete data
            btnAddItem.setVisibility(View.GONE);
            materialToolbar.setTitle("Item Update");
        } else {
            //editText Id kita setEnabled menjadi true, karena else itu berarti data di bundle kosong
            //Artinya disini untuk Add Data, makanya btnSave dan btnDelete di GONE
            editTextItemUpdateId.setEnabled(true);
            btnSaveItem.setVisibility(View.GONE);
            btnDeleteItem.setVisibility(View.GONE);
            materialToolbar.setTitle("Item Add");
        }

    }

    //Function request untuk mengambil data dari database, menggunakan StringRequest
    //Data dari database akan diubah kedalam bentuk JSON
    private void requestUpdateData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.requestItemUpdate, responses -> {
            Toast.makeText(ItemUpdateActivity.this, responses, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ItemUpdateActivity.this, ItemListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }, error -> {
            Toast.makeText(ItemUpdateActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            //Function ini digunakan untuk mengirim data ke php dengan key sesiao dengan yang di file php
            //HashMap adalah sebuah class yang berisi sekumpulan pasangan nilai (value) dan kunci (key).
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //params.put memberikan data berpasangan ke file php
                //dengan key, dan value'nya
                params.put("id", String.valueOf(editTextItemUpdateId.getText()).trim());
                params.put("item_name", String.valueOf(editTextItemUpdateName.getText()).trim());
                params.put("item_description", String.valueOf(editTextItemUpdateDescription.getText()).trim());
                params.put("item_image", String.valueOf(editTextItemUpdateImage.getText()).trim());
                params.put("action", action);
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}