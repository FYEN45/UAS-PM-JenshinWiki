package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
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

        initViews();

        buttonRegister.setOnClickListener(v -> {
            requestUserRegister();
        });

        textViewToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initViews(){
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

    private void requestUserRegister(){
        if (validateName() && validateEmail() && validateUsername() && validatePassword()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.userRegister, responses -> {
                if (responses.equals("Registrasi Berhasil!")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this, responses, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, responses, Toast.LENGTH_SHORT).show();
                }
            }, error -> {
                Toast.makeText(RegisterActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", String.valueOf(editTextRegisterName.getText()));
                    params.put("email", String.valueOf(editTextRegisterEmail.getText()));
                    params.put("username", String.valueOf(editTextRegisterUsername.getText()));
                    params.put("password", String.valueOf(editTextRegisterPassword.getText()));
                    return params;
                }
            };
            AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
    }

    private boolean validateName(){
        boolean validname = true;
        if (editTextRegisterName.getText().toString().isEmpty()) {
            validname = false;
            textInputLayoutRegisterName.setError("Nama kosong!");
        } else {
            textInputLayoutRegisterName.setError(null);
        }
        return validname;
    }

    private boolean validateEmail(){
        boolean validemail = true;
        if (editTextRegisterEmail.getText().toString().isEmpty()) {
            validemail = false;
            textInputLayoutRegisterEmail.setError("Email kosong!");
        } else
            if (!Patterns.EMAIL_ADDRESS.matcher(editTextRegisterEmail.getText().toString()).matches()) {
                validemail = false;
                textInputLayoutRegisterEmail.setError("Format Email tidak Valid!");
            } else {
                textInputLayoutRegisterEmail.setError(null);
            }
        return validemail;
    }

    private boolean validateUsername(){
        boolean validusername = true;
        if(editTextRegisterUsername.getText().toString().isEmpty()){
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

    private boolean validatePassword(){
        boolean validpass = true;
        if(editTextRegisterPassword.getText().toString().isEmpty()){
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