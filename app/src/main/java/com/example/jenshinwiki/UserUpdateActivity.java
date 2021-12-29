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
        initViews();
        receiveData();

        buttonUserUpdateSave.setOnClickListener(v -> {
            action = "edit";
            requestUpdateData();
        });

        buttonUserUpdateDelete.setOnClickListener(v -> {
            action = "delete";
            requestUpdateData();
        });
    }

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

    private void receiveData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        editTextUserUpdateName.setText(intent.getStringExtra("name"));
        editTextUserUpdateEmail.setText(intent.getStringExtra("email"));
        editTextUserUpdateUsername.setText(intent.getStringExtra("username"));
        editTextUserUpdatePassword.setText(intent.getStringExtra("password"));


        //true = admin, false = user
        toggleButtonUserUpdateStatus.setChecked(intent.getStringExtra("status").equals("admin")); //true = admin, false = user

        //Kalau ngebuka akun yang lagi login, akses buat ganti status dan delete di hilangkan!
        if (TempLoginData.Temp_Username.equals(intent.getStringExtra("username"))) {
            toggleButtonUserUpdateStatus.setEnabled(false);
            buttonUserUpdateDelete.setEnabled(false);
        }
    }

    private void requestUpdateData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.requestUserUpdate, responses -> {
            Toast.makeText(UserUpdateActivity.this, responses, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserUpdateActivity.this, UserListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }, error -> {
            Toast.makeText(UserUpdateActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        }) {
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
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}