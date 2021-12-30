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
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MonsterUpdateActivity extends AppCompatActivity {
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
    String action;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_update);

        initViews();
        receiveData();
        checkLinkImage.setOnClickListener(v -> {
            String url = String.valueOf(editTextMonsterUpdateImage.getText());
            Picasso.get().load(url).into(imageViewUpdate);
        });

        btnAdd.setOnClickListener(v -> {
            action = "add";
            requestUpdateData();
        });

        btnSave.setOnClickListener(v -> {
            action = "edit";
            requestUpdateData();
        });

        btnDelete.setOnClickListener(v -> {
            action = "delete";
            requestUpdateData();
        });
    }

    private void initViews() {
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

        intent = getIntent();
        bundle = intent.getExtras();
    }

    private void receiveData() {
        if (bundle != null) {
            Intent intent = getIntent();
            editTextMonsterUpdateId.setEnabled(false);
            editTextMonsterUpdateId.setText(intent.getStringExtra("id"));
            editTextMonsterUpdateName.setText(intent.getStringExtra("name"));
            editTextMonsterUpdateDescription.setText(intent.getStringExtra("description"));
            editTextMonsterUpdateImage.setText(intent.getStringExtra("image"));
            String url = intent.getStringExtra("image");
            Picasso.get().load(url).into(imageViewUpdate);
            btnAdd.setVisibility(View.GONE);
        } else {
            editTextMonsterUpdateId.setEnabled(true);
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }

    }

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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(editTextMonsterUpdateId.getText()));
                params.put("monster_name", String.valueOf(editTextMonsterUpdateName.getText()));
                params.put("monster_description", String.valueOf(editTextMonsterUpdateDescription.getText()));
                params.put("monster_image", String.valueOf(editTextMonsterUpdateImage.getText()));
                params.put("action", action);
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}