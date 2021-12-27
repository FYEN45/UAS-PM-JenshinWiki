package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

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

        initViews();

        buttonLogin.setOnClickListener(v -> {
            if (validateUsername() && validatePassword()) {
                requestLoginVerification();
            }
        });

        textViewToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        textInputLayoutLoginUsername = findViewById(R.id.textInputLayoutLoginUsername);
        textInputLayoutLoginPassword = findViewById(R.id.textInputLayoutLoginPassword);
        editTextLoginUsername = findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewToRegister = findViewById(R.id.textViewToRegister);
    }

    private void requestLoginVerification() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.userLoginVerification, response -> {
            try {
                JSONObject jsonObjectResponse = new JSONObject(response);
                Toast.makeText(LoginActivity.this, jsonObjectResponse.getString("pesan"), Toast.LENGTH_SHORT).show();

                if (jsonObjectResponse.getString("pesan").equals("Login Berhasil!")) {
                    JSONObject headerUser = jsonObjectResponse.getJSONObject("user");
                    TempLoginData.Temp_Name = headerUser.getString("name");
                    TempLoginData.Temp_Username = headerUser.getString("username");
                    TempLoginData.Temp_Status = headerUser.getString("status");

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }, error -> {
            Toast.makeText(LoginActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", String.valueOf(editTextLoginUsername.getText()));
                params.put("password", String.valueOf(editTextLoginPassword.getText()));
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private boolean validateUsername() {
        boolean validuser = true;
        String Username = editTextLoginUsername.getText().toString();
        if (Username.isEmpty()) {
            validuser = false;
            textInputLayoutLoginUsername.setError("Username kosong!");
        }
        return validuser;
    }

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