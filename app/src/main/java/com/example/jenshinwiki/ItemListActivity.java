package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

//Activity ini difokuskan untuk menampilkan gambar dan nama Item menggunakan ListView
//Ada juga button untuk menambahkan Item, namun harus admin yang ada button tersebut
public class ItemListActivity extends AppCompatActivity {
    ListView mItemView;
    ArrayList<Item> mListItems = new ArrayList<>();
    FloatingActionButton floatingActionButtonAddItem;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mItemView = findViewById(R.id.listviewItem); //Mendeklarasikan ListView untuk Monster

        //Memberikan pengecualian button Add Item menggunakan floatingbutton
        //Hanya (temp_status = admin) yang akan terlihat floating buttonnya
        //Selain admin, floating button kita set GONE
        floatingActionButtonAddItem = findViewById(R.id.fab_addItem);
        if (TempLoginData.Temp_Status.equals("admin")) {
            floatingActionButtonAddItem.setVisibility(View.VISIBLE);
        } else {
            floatingActionButtonAddItem.setVisibility(View.GONE);
        }
        request();

        //Membuat intent pada ListView, dimana kalau admin, akan di intent ke halaman ItemUpdate
        //dan selain admin, akan di intent ke halaman ItemDescription, tidak bisa add maupun edit/delete
        mItemView.setOnItemClickListener((parent, view, position, id) -> {
            if (TempLoginData.Temp_Status.equals("admin")) {
                //intent.putExtra berfungsi untuk mengirimkan data ke halaman ItemUpdateActivity
                //key : id, name, descriptiom, dan image. digunakan untuk pemanggilan di ItemUpdateActivity
                Intent intent = new Intent(ItemListActivity.this, ItemUpdateActivity.class);
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getItem_name());
                intent.putExtra("description", mListItems.get(position).getItem_description());
                intent.putExtra("image", mListItems.get(position).getItem_image());
                startActivity(intent);
            } else {
                //intent.putExtra berfungsi untuk mengirimkan data ke halaman ItemDescriptionActivity
                //key : id, name, descriptiom, dan image. digunakan untuk pemanggilan di ItemDescriptionActivity
                Intent intent = new Intent(ItemListActivity.this, ItemDescriptionActivity.class);
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getItem_name());
                intent.putExtra("description", mListItems.get(position).getItem_description());
                intent.putExtra("image", mListItems.get(position).getItem_image());
                startActivity(intent);
            }
        });

        //Memberikan intent pada floatingButton ke halaman ItemUpdateActivity
        floatingActionButtonAddItem.setOnClickListener(view -> {
            Intent intent = new Intent(ItemListActivity.this, ItemUpdateActivity.class);
            startActivity(intent);
        });
    }

    //Function request untuk mengambil data dari database, menggunakan StringRequest
    //Data dari database akan diubah kedalam bentuk JSON
    private void request() {
        StringRequest strReq = new StringRequest(Request.Method.GET, Config.requestItemList, responses -> {
            try {
                JSONObject response = new JSONObject(responses);
                JSONObject header = response.getJSONObject("data");
                //Perulangan untuk mengambil data dari JSONObject yang disimpan kedalam variable header
                Iterator<String> iterator = header.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject data = (JSONObject) header.get(key);

                    //Mengirim data dari variable 'data' di JSONObject ke dalam Item.java menggunakan Setter
                    Item model = new Item();
                    model.setId(data.getString("id"));
                    model.setItem_name(data.getString("item_name"));
                    model.setItem_description(data.getString("item_description"));
                    model.setItem_image(data.getString("item_image"));
                    //memasukkan data yang sudah diambil kedalam ArrayList Item
                    mListItems.add(model);
                }
                //Memasukkan data kedalam ListView lewat ItemAdapter yang memiliki
                //2 parameter yaitu, ItemListActivity, ArrayList Item (mListItems)
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