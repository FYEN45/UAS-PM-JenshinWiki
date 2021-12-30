package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ItemDescriptionActivity extends AppCompatActivity {
    ImageView imageViewItem;
    TextView textViewIDItem;
    TextView textViewNameItem;
    TextView textViewDescriptionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        initViews();
        receiveData();
    }

    private void initViews() {
        imageViewItem = findViewById(R.id.description_itemImage);
        textViewIDItem = findViewById(R.id.description_itemId);
        textViewNameItem = findViewById(R.id.description_itemName);
        textViewDescriptionItem = findViewById(R.id.description_itemDestription);
    }

    private void receiveData() {
        Intent intent = getIntent();
        textViewIDItem.setText(intent.getStringExtra("id"));
        textViewNameItem.setText(intent.getStringExtra("name"));
        textViewDescriptionItem.setText(intent.getStringExtra("description"));
        String url = intent.getStringExtra("image");
        Picasso.get().load(url).into(imageViewItem);
    }
}