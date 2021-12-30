package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import java.util.HashMap;
import java.util.Map;

// RegisterActivity digunakan untuk melakukan registrasi user, dimana user yang sudah terdaftar
// akan dapat melakukan Login pada halaman Login.
// Data yang harus diinput user berupa nama, email, username, dan password.
// Registrasi tidak dapat dilakukan menggunakan email, dan username yang sama.
// Apabila registrasi berhasil, user akan diarahkan ke halaman login untuk melakukan login.
public class RegisterActivity extends AppCompatActivity {

    //Deklarasi komponen dalam Layout Register activity.
    TextInputLayout textInputLayoutRegisterName;
    TextInputLayout textInputLayoutRegisterEmail;
    TextInputLayout textInputLayoutRegisterUsername;
    TextInputLayout textInputLayoutRegisterPassword;
    EditText editTextRegisterName;
    EditText editTextRegisterEmail;
    EditText editTextRegisterUsername;
    EditText editTextRegisterPassword;
    Button buttonRegister;
    TextView textViewToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews(); //Memanggil method initViews

        //Menyatakan fungsi kepada button Register.
        //Apabila button ditekan, aplikasi akan memanggil method requestUserRegister.
        buttonRegister.setOnClickListener(v -> {
            requestUserRegister();
        });

        //Menyatakan fungsi kepada textViewToLogin.
        //Apabila text view ini ditekan, aplikasi akan mengarahkan user ke halaman Login.
        textViewToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    //Method initViews digunakan untuk meng-inisialisasikan komponen - komponen dalam
    // layout sehingga java dapat mengakses komponen tersebut.
    private void initViews() {
        textInputLayoutRegisterName = findViewById(R.id.textInputLayoutRegisterName);
        textInputLayoutRegisterEmail = findViewById(R.id.textInputLayoutRegisterEmail);
        textInputLayoutRegisterUsername = findViewById(R.id.textInputLayoutRegisterUsername);
        textInputLayoutRegisterPassword = findViewById(R.id.textInputLayoutRegisterPassword);
        editTextRegisterName = findViewById(R.id.editTextRegisterName);
        editTextRegisterEmail = findViewById(R.id.editTextRegisterEmail);
        editTextRegisterUsername = findViewById(R.id.editTextRegisterUsername);
        editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        textViewToLogin = findViewById(R.id.textViewToLogin);
    }

    //Method requestUserRegister akan mencoba untuk mengirimkan data berupa
    // nama, email, username dan password untuk dilakukan registrasi pada webservices.
    // Setelah itu aplikasi akan menerima respon dari webservices. Respon ini digunakan untuk
    // memberitahu user apakah registrasi berhasil dilakukan atau tidak.
    private void requestUserRegister() {
        // Memvalidasi nama menggunakan method validateName.
        // Memvalidasi email menggunakan method validateEmail.
        // Memvalidasi username menggunakan method validateUsername.
        // Memvalidasi password menggunakan method validatePassword.
        if (validateName() && validateEmail() && validateUsername() && validatePassword()) {
            // StringRequest merupakan sebuah bentuk request / permintaan kepada webservices.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.userRegister, responses -> {
                // Apabila respon yang diberikan dari webservices berupa "Registrasi Berhasil!" maka user
                // akan di arahkan ke halaman Login dan mendapat pesan "Registrasi Berhasil!".
                if (responses.equals("Registrasi Berhasil!")) {

                    //Menampilkan pesan yang diterima dari webservices.
                    Toast.makeText(RegisterActivity.this, responses, Toast.LENGTH_SHORT).show();

                    //Melakukan pemindahan halaman dari halaman Register ke halaman Login.
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Apabila respon yang diberikan dari webservices bukan berupa "Registrasi Berhasil!"
                    // aplikasi akan menampilkan error tersebut.
                    Toast.makeText(RegisterActivity.this, responses, Toast.LENGTH_SHORT).show();
                }
            }, error -> {
                //Apabila aplikasi gagal terhubung dengan webservices, pada aplikasi akan menampilkan
                // informasi kepada user.
                Toast.makeText(RegisterActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
            }) {
                //Baris kode berikut digunakan sebagai data - data yang akan dikirimkan ke webservices
                // untuk diproses. Dalam Register, data yang dikirim berupa nama, email, username, dan password
                // yang akan digunakan untuk melakukan registrasi.
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", String.valueOf(editTextRegisterName.getText()).trim());
                    params.put("email", String.valueOf(editTextRegisterEmail.getText()).trim());
                    params.put("username", String.valueOf(editTextRegisterUsername.getText()).trim());
                    params.put("password", String.valueOf(editTextRegisterPassword.getText()).trim());
                    return params;
                }
            };
            //Menjalankan permintaan untuk melakukan koneksi dengan webservices.
            AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
    }

    //Method validateName akan memvalidasi input nama.
    // apabila kolom nama kosong, maka akan menampilkan error "Nama Kosong!"
    private boolean validateName() {
        boolean validname = true;
        if (editTextRegisterName.getText().toString().isEmpty()) {
            validname = false;
            textInputLayoutRegisterName.setError("Nama kosong!");
        } else {
            textInputLayoutRegisterName.setError(null);
        }
        return validname;
    }

    //Method validateEmail akan memvalidasi input Email.
    // apabila kolom email kosong, maka akan menampilkan error "Email Kosong!"
    // apabila kolom email bukan berisi email, maka akan menampilkan error "Format emaiil tidak valid!"
    private boolean validateEmail() {
        boolean validemail = true;
        if (editTextRegisterEmail.getText().toString().isEmpty()) {
            validemail = false;
            textInputLayoutRegisterEmail.setError("Email kosong!");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(editTextRegisterEmail.getText().toString()).matches()) {
            validemail = false;
            textInputLayoutRegisterEmail.setError("Format Email tidak Valid!");
        } else {
            textInputLayoutRegisterEmail.setError(null);
        }
        return validemail;
    }

    //Method validateUsername akan memvalidasi input Username.
    // apabila kolom username kosong, maka akan menampilkan error "username Kosong!"
    // apabila kolom username berisi kurang dari 5 karakter, maka akan menampilkan error "USername minimal 5 karakter!"
    private boolean validateUsername() {
        boolean validusername = true;
        if (editTextRegisterUsername.getText().toString().isEmpty()) {
            validusername = false;
            textInputLayoutRegisterUsername.setError("Username kosong!");
        } else {
            if (editTextRegisterUsername.getText().toString().length() < 5) {
                validusername = false;
                textInputLayoutRegisterUsername.setError("Username minimal 5 karakter!");
            } else {
                textInputLayoutRegisterUsername.setError(null);
            }
        }
        return validusername;
    }

    //Method validatePassword akan memvalidasi input Password
    // apabila kolom password kosong, akan menampilkan error "Password Kosong!"
    // apabila password berisi kurang dari 8 karakter, akan menampilkan error "Password minimal 8 karakter!".
    private boolean validatePassword() {
        boolean validpass = true;
        if (editTextRegisterPassword.getText().toString().isEmpty()) {
            validpass = false;
            textInputLayoutRegisterPassword.setError("Password kosong!");
        } else {
            if (editTextRegisterPassword.getText().toString().length() < 8) {
                validpass = false;
                textInputLayoutRegisterPassword.setError("Password minimal 8 karakter!");
            } else {
                textInputLayoutRegisterPassword.setError(null);
            }
        }
        return validpass;
    }
}