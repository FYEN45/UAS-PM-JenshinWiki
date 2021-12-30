package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//LoginActivity.java difokuskan untuk memberikan akses kepada user untuk melakukan Login
// agar dapat mengakses halaman lain dalam Aplikasi.
// Pada kelas ini, user akan diminta untuk melakukan input berupa username dan password yang akan
// dikirimkan ke webservices untuk dilakukan verifikasi.
// Pada kelas ini juga, data user yang berhasil login akan disimpan secara temporary untuk keperluan lebih lanjut.
public class LoginActivity extends AppCompatActivity {

    //Deklarasi komponen dalam Layout Login Activity.
    TextInputLayout textInputLayoutLoginUsername;
    TextInputLayout textInputLayoutLoginPassword;
    EditText editTextLoginUsername;
    EditText editTextLoginPassword;
    Button buttonLogin;
    TextView textViewToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews(); //Memanggil method initViews;

        //Menyatakan fungsi ketika button Login ditekan.
        // Saat button ditekan, validasi Username dan Password akan dijalankan, lalu
        // aplikasi akan menjalankan method requestLoginVerification.
        buttonLogin.setOnClickListener(v -> {
            if (validateUsername() && validatePassword()) {
                requestLoginVerification(); //Memanggil method requestLoginVerification.
            }
        });

        //Menyatakan fungsi ketika textViewToRegister ditekan.
        // Saat text view ini ditekan, tampilan interface akan diarahkan ke halaman Register.
        textViewToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    //Method initViews digunakan untuk meng-inisialisasikan komponen - komponen dalam
    // layout sehingga java dapat mengakses komponen tersebut.
    private void initViews() {
        textInputLayoutLoginUsername = findViewById(R.id.textInputLayoutLoginUsername);
        textInputLayoutLoginPassword = findViewById(R.id.textInputLayoutLoginPassword);
        editTextLoginUsername = findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewToRegister = findViewById(R.id.textViewToRegister);
    }

    //Method requestLoginVerification akan mencoba untuk mengirimkan data berupa
    // username dan password untuk dilakukan verifikasi pada webservices.
    // Setelah itu aplikasi akan menerima respon dari webservices. Respon ini digunakan untuk
    // menentukan apakah user diijinkan untuk masuk ke halaman Home atau tidak.
    private void requestLoginVerification() {
        //StringRequest merupakan sebuah bentuk request / permintaan kepada webservices.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.userLoginVerification, response -> {
            try {
                JSONObject jsonObjectResponse = new JSONObject(response);

                //Menampilkan pesan yang diterima dari webservices.
                Toast.makeText(LoginActivity.this, jsonObjectResponse.getString("pesan"), Toast.LENGTH_SHORT).show();

                //Memeriksa respon dari webservices.
                // Apabila respon pesan berupa "Login Berhasil!", maka user akan mendapat akses untuk
                // berpindah ke halaman Home.
                if (jsonObjectResponse.getString("pesan").equals("Login Berhasil!")) {
                    JSONObject headerUser = jsonObjectResponse.getJSONObject("user");

                    //Kelas TempLoginData disini digunakan untuk menyimpan data secara Temporary.
                    // Data - data yang disimpan adalah data user yang berhasil melakukan login, dimana
                    // data tersebut berupa Nama, Username, dan Status dari user.
                    TempLoginData.Temp_Name = headerUser.getString("name");
                    TempLoginData.Temp_Username = headerUser.getString("username");
                    TempLoginData.Temp_Status = headerUser.getString("status");

                    //Melakukan pemindahan halaman dari halaman Login ke halaman Home.
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException exception) {
                exception.printStackTrace(); //Menangkap error yang terjadi pada JSON dan mencetaknya dalam Log.
            }
        }, error -> {
            //Apabila aplikasi gagal terhubung dengan webservices, pada aplikasi akan menampilkan
            // informasi kepada user.
            Toast.makeText(LoginActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
            //Baris kode berikut digunakan sebagai data - data yang akan dikirimkan ke webservices
            // untuk diproses. Dalam Login, username dan password dikirim untuk melakukan verifikasi.
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", String.valueOf(editTextLoginUsername.getText()));
                params.put("password", String.valueOf(editTextLoginPassword.getText()));
                return params;
            }
        };
        //Menjalankan permintaan untuk melakukan koneksi dengan webservices.
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    // Memvalidasi input Username,
    //apabila kolom input username kosong, maka akan menampilkan error "Username kosong!"
    private boolean validateUsername() {
        boolean validuser = true;
        String Username = editTextLoginUsername.getText().toString();
        if (Username.isEmpty()) {
            validuser = false;
            textInputLayoutLoginUsername.setError("Username kosong!");
        }
        return validuser;
    }

    // Memvalidasi input Password,
    //apabila kolom input password kosong, maka akan menampilkan error "Password kosong!"
    private boolean validatePassword() {
        boolean validpass = true;
        String Username = editTextLoginPassword.getText().toString();
        if (Username.isEmpty()) {
            validpass = false;
            textInputLayoutLoginPassword.setError("Password kosong!");
        }
        return validpass;
    }
}