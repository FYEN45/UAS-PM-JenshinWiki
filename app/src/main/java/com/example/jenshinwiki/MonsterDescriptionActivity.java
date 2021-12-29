package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MonsterDescriptionActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textViewID;
    TextView editTextName;
    TextView editTextDescription;

    String action;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_description);

        initViews();
        receiveData();
    }

    private void initViews(){
        imageView = findViewById(R.id.description_monsterImage);
        textViewID = findViewById(R.id.description_monsterId);
        editTextName = findViewById(R.id.description_monsterName);
        editTextDescription = findViewById(R.id.description_monsterDestription);
    }

    private void receiveData(){
        Intent intent = getIntent();
        textViewID.setText(intent.getStringExtra("id"));
        editTextName.setText(intent.getStringExtra("name"));
        editTextDescription.setText(intent.getStringExtra("description"));
        String url = intent.getStringExtra("image");
        Picasso.get().load(url).into(imageView);
    }
}