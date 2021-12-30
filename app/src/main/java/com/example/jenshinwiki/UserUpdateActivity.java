package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

//UserUpdateAcitivity merupakan halaman untuk mengubah maupun menghapus data user.
//Halaman ini memberikan user dengan status admin untuk mengubah data dari user lain, seperti
// data nama, password, dan status.

public class UserUpdateActivity extends AppCompatActivity {
    TextInputLayout textInputLayoutUserUpdateName;
    TextInputLayout textInputLayoutUserUpdateEmail;
    TextInputLayout textInputLayoutUserUpdateUsername;
    TextInputLayout textInputLayoutUserUpdatePassword;
    EditText editTextUserUpdateName;
    EditText editTextUserUpdateEmail;
    EditText editTextUserUpdateUsername;
    EditText editTextUserUpdatePassword;
    ToggleButton toggleButtonUserUpdateStatus;
    Button buttonUserUpdateSave;
    Button buttonUserUpdateDelete;

    String id;
    String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        initViews(); //Memanggil method initViews
        receiveData(); //Memanggil method receiveData

        //Memberikan fungsi kepada button Save untuk memanggil method requestUpdateData
        buttonUserUpdateSave.setOnClickListener(v -> {
            action = "edit";
            requestUpdateData();
        });

        //Memberikan fungsi kepada button Delete untuk memanggil method requestUpdateData
        buttonUserUpdateDelete.setOnClickListener(v -> {
            action = "delete";
            requestUpdateData();
        });
    }

    //Method initViews digunakan untuk meng-inisialisasikan komponen - komponen dalam
    // layout sehingga java dapat mengakses komponen tersebut.
    private void initViews() {
        textInputLayoutUserUpdateName = findViewById(R.id.textInputLayoutUpdateName);
        textInputLayoutUserUpdateEmail = findViewById(R.id.textInputLayoutUpdateEmail);
        textInputLayoutUserUpdateUsername = findViewById(R.id.textInputLayoutUpdateUsername);
        textInputLayoutUserUpdatePassword = findViewById(R.id.textInputLayoutUpdatePassword);
        editTextUserUpdateName = findViewById(R.id.editTextUpdateName);
        editTextUserUpdateEmail = findViewById(R.id.editTextUpdateEmail);
        editTextUserUpdateUsername = findViewById(R.id.editTextUpdateUsername);
        editTextUserUpdatePassword = findViewById(R.id.editTextUpdatePassword);
        toggleButtonUserUpdateStatus = findViewById(R.id.toggleButtonStatus);
        buttonUserUpdateSave = findViewById(R.id.buttonSave);
        buttonUserUpdateDelete = findViewById(R.id.buttonDelete);
    }

    //Method receiveData digunakan untuk menerima data yang dikirim dari UserListActivity pada saat perpindahan
    // halaman. Data - data ini nantinya akan ditampilkan pada halaman UserUpdateActivity.
    private void receiveData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        editTextUserUpdateName.setText(intent.getStringExtra("name"));
        editTextUserUpdateEmail.setText(intent.getStringExtra("email"));
        editTextUserUpdateUsername.setText(intent.getStringExtra("username"));
        editTextUserUpdatePassword.setText(intent.getStringExtra("password"));

        toggleButtonUserUpdateStatus.setChecked(intent.getStringExtra("status").equals("admin"));

        //Kalau ngebuka akun yang lagi login, akses buat ganti status dan delete di hilangkan!

        //Memeriksa apakah user yang melakukan login dan data user yang dibuka merupakan user yang sama.
        //Apabila sama, akses untuk menghapus user dan mengubah status akan dimatikan.
        if (TempLoginData.Temp_Username.equals(intent.getStringExtra("username"))) {
            toggleButtonUserUpdateStatus.setEnabled(false);
            buttonUserUpdateDelete.setEnabled(false);
        }
    }

    //Method requestUpdateData digunakan untuk meminta webservices melakukan update data dari user yang dipilih.
    // Update data ini bisa berupa hanya mengubah data user, atau menghapus user.
    private void requestUpdateData() {
        //StringRequest merupakan sebuah bentuk request / permintaan kepada webservices.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.requestUserUpdate, responses -> {
            //Menampilkan Respon yang diterima dari webservices
            Toast.makeText(UserUpdateActivity.this, responses, Toast.LENGTH_SHORT).show();

            //Melakukan perpindahan halaman dari halaman UserUpdateActivity ke UserListActivity
            //Sekaligus menyatakan UserListActivity merupakan activity utama dan mengakhiri semua activity yang berjalan.
            Intent intent = new Intent(UserUpdateActivity.this, UserListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }, error -> {
            //Apabila aplikasi gagal terhubung dengan webservices, pada aplikasi akan menampilkan
            // informasi kepada user.
            Toast.makeText(UserUpdateActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
            //Baris kode berikut digunakan sebagai data - data yang akan dikirimkan ke webservices
            // untuk diproses. Data tersebut berupa id, name, email, username, password, dan status.
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("name", String.valueOf(editTextUserUpdateName.getText()));
                params.put("email", String.valueOf(editTextUserUpdateEmail.getText()));
                params.put("username", String.valueOf(editTextUserUpdateUsername.getText()));
                params.put("password", String.valueOf(editTextUserUpdatePassword.getText()));
                if (toggleButtonUserUpdateStatus.isChecked()) {
                    params.put("status", "admin");
                } else {
                    params.put("status", "user");
                }
                params.put("action", action);
                return params;
            }
        };
        //Menjalankan permintaan untuk melakukan koneksi dengan webservices.
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}