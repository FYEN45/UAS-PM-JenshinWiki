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
public class MonsterUpdateActivity extends AppCompatActivity {
    //Penginisiasian variable - variable untuk pemanggilan komponen pada layout nantinya
    MaterialToolbar materialToolbar;
    ImageView imageViewUpdate;
    ImageView checkLinkImage;
    TextInputLayout textInputLayoutMonsterUpdateImage;
    TextInputLayout textInputLayoutMonsterUpdateId;
    TextInputLayout textInputLayoutMonsterUpdateName;
    TextInputLayout textInputLayoutMonsterUpdateDescription;
    EditText editTextMonsterUpdateImage;
    EditText editTextMonsterUpdateId;
    EditText editTextMonsterUpdateName;
    EditText editTextMonsterUpdateDescription;
    Button btnAdd, btnSave, btnDelete;
    String action; //Untuk mengatur action add, edit, atau delete, untuk dikirim ke .php
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_update);

        initViews(); //Pemanggilan function initViews
        receiveData(); //Pemanggilan function receiveData

        //Fungsi button ini saat diklik adalah untuk mengecek, apakah link yang dimasukan
        //dapat tampil pada imageView
        checkLinkImage.setOnClickListener(v -> {
            String url = String.valueOf(editTextMonsterUpdateImage.getText());
            //Library Picasso untuk pengkonversian dari link url tersebut ke imageview
            Picasso.get().load(url).into(imageViewUpdate);
        });

        btnAdd.setOnClickListener(v -> {
            action = "add"; //Memberikan nilai action = add, karena untuk button add data baru
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });

        btnSave.setOnClickListener(v -> {
            action = "edit"; //Memberikan nilai action = edit, karena untuk button save/update data
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });

        btnDelete.setOnClickListener(v -> {
            action = "delete"; //Memberikan nilai action = delete, karena untuk button delete data
            requestUpdateData(); //Pemanggilan function requestUpdateData
        });
    }

    private void initViews() {
        /*Mendeklarasikan variable yang berisi komponen yang ada
        pada layout activity_monster_update*/
        imageViewUpdate = findViewById(R.id.update_monsterImage);
        checkLinkImage = findViewById(R.id.checkLinkImage);
        textInputLayoutMonsterUpdateImage = findViewById(R.id.TextInputLayoutMonsterUpdateImage);
        textInputLayoutMonsterUpdateId = findViewById(R.id.TextInputLayoutMonsterUpdateId);
        textInputLayoutMonsterUpdateName = findViewById(R.id.TextInputLayoutMonsterUpdateName);
        textInputLayoutMonsterUpdateDescription = findViewById(R.id.TextInputLayoutMonsterUpdateDescription);
        editTextMonsterUpdateImage = findViewById(R.id.editTextMonsterUpdateImage);
        editTextMonsterUpdateId = findViewById(R.id.editTextMonsterUpdateId);
        editTextMonsterUpdateName = findViewById(R.id.editTextMonsterUpdateName);
        editTextMonsterUpdateDescription = findViewById(R.id.editTextMonsterUpdateDescription);
        btnAdd = findViewById(R.id.btnAddMonster);
        btnSave = findViewById(R.id.btnSaveMonster);
        btnDelete = findViewById(R.id.btnDeleteMonster);
        materialToolbar = findViewById(R.id.topAppBar);

        intent = getIntent();
        bundle = intent.getExtras();
    }

    private void receiveData() {
        //Jika data yang diterima tidak kosong, maka akan menampilkan data-data yang ada pada bundle
        if (bundle != null) {
            Intent intent = getIntent();
            //editText Id kita setEnabled'nya false agar tidak bisa diupdate pada kolom ID'nya
            editTextMonsterUpdateId.setEnabled(false);
            //Memberikan data yang ada pada intent ke komponennya masing-masing
            editTextMonsterUpdateId.setText(intent.getStringExtra("id"));
            editTextMonsterUpdateName.setText(intent.getStringExtra("name"));
            editTextMonsterUpdateDescription.setText(intent.getStringExtra("description"));
            editTextMonsterUpdateImage.setText(intent.getStringExtra("image"));
            //Pengkonversian dari link url ke imageView menggunakan library Picasso
            String url = intent.getStringExtra("image");
            Picasso.get().load(url).into(imageViewUpdate);
            //Button add dibuat GONE karena bundle ada datanya, sehingga berfungsi untuk update/delete data
            btnAdd.setVisibility(View.GONE);
            materialToolbar.setTitle("Monster Update");
        } else {
            //editText Id kita setEnabled menjadi true, karena else itu berarti data di bundle kosong
            //Artinya disini untuk Add Data, makanya btnSave dan btnDelete di GONE
            editTextMonsterUpdateId.setEnabled(true);
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            materialToolbar.setTitle("Monster Add");
        }

    }

    //Function request untuk mengambil data dari database, menggunakan StringRequest
    //Data dari database akan diubah kedalam bentuk JSON
    private void requestUpdateData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.requestMonsterUpdate, responses -> {
            Toast.makeText(MonsterUpdateActivity.this, responses, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MonsterUpdateActivity.this, MonsterListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }, error -> {
            Toast.makeText(MonsterUpdateActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            //Function ini digunakan untuk mengirim data ke php dengan key sesiao dengan yang di file php
            //HashMap adalah sebuah class yang berisi sekumpulan pasangan nilai (value) dan kunci (key).
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //params.put memberikan data berpasangan ke file php
                //dengan key, dan value'nya
                params.put("id", String.valueOf(editTextMonsterUpdateId.getText()).trim());
                params.put("monster_name", String.valueOf(editTextMonsterUpdateName.getText()).trim());
                params.put("monster_description", String.valueOf(editTextMonsterUpdateDescription.getText()).trim());
                params.put("monster_image", String.valueOf(editTextMonsterUpdateImage.getText()).trim());
                params.put("action", action);
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}