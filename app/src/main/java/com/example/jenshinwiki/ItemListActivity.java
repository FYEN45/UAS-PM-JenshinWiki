package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemListActivity extends AppCompatActivity {
    ListView mItemView;
    ArrayList<Item> mListItems = new ArrayList<>();
    FloatingActionButton floatingActionButtonAddItem;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mItemView = findViewById(R.id.listviewItem);

        floatingActionButtonAddItem = findViewById(R.id.fab_addItem);
        if (TempLoginData.Temp_Status.equals("admin")) {
            floatingActionButtonAddItem.setVisibility(View.VISIBLE);
        }else{
            floatingActionButtonAddItem.setVisibility(View.GONE);
        }
        request();

        mItemView.setOnItemClickListener((parent, view, position, id) -> {
            if (TempLoginData.Temp_Status.equals("admin")) {
                Intent intent = new Intent(ItemListActivity.this, ItemUpdateActivity.class);
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getItem_name());
                intent.putExtra("description", mListItems.get(position).getItem_description());
                intent.putExtra("image", mListItems.get(position).getItem_image());
                startActivity(intent);
            }else{
                Intent intent = new Intent(ItemListActivity.this, ItemDescriptionActivity.class);
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getItem_name());
                intent.putExtra("description", mListItems.get(position).getItem_description());
                intent.putExtra("image", mListItems.get(position).getItem_image());
                startActivity(intent);
            }
        });

        floatingActionButtonAddItem.setOnClickListener(view -> {
            Intent intent = new Intent(ItemListActivity.this, ItemUpdateActivity.class);
            startActivity(intent);
        });
    }

    private void request(){
        StringRequest strReq = new StringRequest(Request.Method.GET, Config.requestItemList, responses -> {
            try {
                JSONObject response = new JSONObject(responses);
                JSONObject header = response.getJSONObject("data");
                Iterator<String> iterator = header.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject data = (JSONObject) header.get(key);

                    Item model = new Item();
                    model.setId(data.getString("id"));
                    model.setItem_name(data.getString("item_name"));
                    model.setItem_description(data.getString("item_description"));
                    model.setItem_image(data.getString("item_image"));
                    mListItems.add(model);
                }
                mItemView.setAdapter(new ItemAdapter(ItemListActivity.this, mListItems));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            System.out.println();
            VolleyLog.d(TAG, "Error: " + error.getMessage());
        });
        AppController.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }
}