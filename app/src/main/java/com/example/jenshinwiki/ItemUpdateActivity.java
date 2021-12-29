package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ItemUpdateActivity extends AppCompatActivity {
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
    String action;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);

        initViews();
        receiveData();
        checkLinkImageItem.setOnClickListener(v -> {
            String url = String.valueOf(editTextItemUpdateImage.getText());
            Picasso.get().load(url).into(imageViewUpdateItem);
        });

        btnAddItem.setOnClickListener(v -> {
            action = "add";
            requestUpdateData();
        });

        btnSaveItem.setOnClickListener(v -> {
            action = "edit";
            requestUpdateData();
        });

        btnDeleteItem.setOnClickListener(v -> {
            action = "delete";
            requestUpdateData();
        });
    }

    private void initViews(){
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

        intent = getIntent();
        bundle = intent.getExtras();
    }

    private void receiveData(){
        if(bundle != null){
            Intent intent = getIntent();
            editTextItemUpdateId.setEnabled(false);
            editTextItemUpdateId.setText(intent.getStringExtra("id"));
            editTextItemUpdateName.setText(intent.getStringExtra("name"));
            editTextItemUpdateDescription.setText(intent.getStringExtra("description"));
            editTextItemUpdateImage.setText(intent.getStringExtra("image"));
            String url = intent.getStringExtra("image");
            Picasso.get().load(url).into(imageViewUpdateItem);
            btnAddItem.setVisibility(View.GONE);
        }else{
            editTextItemUpdateId.setEnabled(true);
            btnSaveItem.setVisibility(View.GONE);
            btnDeleteItem.setVisibility(View.GONE);
        }

    }

    private void requestUpdateData(){
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(editTextItemUpdateId.getText()));
                params.put("item_name", String.valueOf(editTextItemUpdateName.getText()));
                params.put("item_description", String.valueOf(editTextItemUpdateDescription.getText()));
                params.put("item_image", String.valueOf(editTextItemUpdateImage.getText()));
                params.put("action", action);
                return params;
            }
        };
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}