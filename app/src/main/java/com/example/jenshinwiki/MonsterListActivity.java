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

//Activity ini difokuskan untuk menampilkan gambar dan nama Monster menggunakan ListView
//Ada juga button untuk menambahkan Monster, namun harus admin yang ada button tersebut
public class MonsterListActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<Monster> mListItems = new ArrayList<>();
    FloatingActionButton floatingActionButtonAddMonster;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_list);

        mListView = findViewById(R.id.listviewMonster); //Mendeklarasikan ListView untuk Monster

        //Memberikan pengecualian button Add Monster menggunakan floatingbutton
        //Hanya (temp_status = admin) yang akan terlihat floating buttonnya
        //Selain admin, floating button kita set GONE
        floatingActionButtonAddMonster = findViewById(R.id.fab_addMonster);
        if (TempLoginData.Temp_Status.equals("admin")) {
            floatingActionButtonAddMonster.setVisibility(View.VISIBLE);
        } else {
            floatingActionButtonAddMonster.setVisibility(View.GONE);
        }

        request(); //Memanggil function request

        //Membuat intent pada ListView, dimana kalau admin, akan di intent ke halaman MonsterUpdate
        //dan selain admin, akan di intent ke halaman MonsterDescription, tidak bisa add maupun edit/delete
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            if (TempLoginData.Temp_Status.equals("admin")) {
                Intent intent = new Intent(MonsterListActivity.this, MonsterUpdateActivity.class);
                //intent.putExtra berfungsi untuk mengirimkan data ke halaman MonsterUpdateActivity
                //key : id, name, descriptiom, dan image. digunakan untuk pemanggilan di MonsterUpdateActivity
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getMonster_name());
                intent.putExtra("description", mListItems.get(position).getMonster_description());
                intent.putExtra("image", mListItems.get(position).getMonster_image());
                startActivity(intent);
            } else {
                //intent.putExtra berfungsi untuk mengirimkan data ke halaman MonsterDescriptionActivity
                //key : id, name, descriptiom, dan image. digunakan untuk pemanggilan di MonsterDescriptionActivity
                Intent intent = new Intent(MonsterListActivity.this, MonsterDescriptionActivity.class);
                intent.putExtra("id", mListItems.get(position).getId());
                intent.putExtra("name", mListItems.get(position).getMonster_name());
                intent.putExtra("description", mListItems.get(position).getMonster_description());
                intent.putExtra("image", mListItems.get(position).getMonster_image());
                startActivity(intent);
            }
        });

        //Memberikan intent pada floatingButton ke halaman MonsterUpdateActivity
        floatingActionButtonAddMonster.setOnClickListener(view -> {
            Intent intent = new Intent(MonsterListActivity.this, MonsterUpdateActivity.class);
            startActivity(intent);
        });
    }

    //Function request untuk mengambil data dari database, menggunakan StringRequest
    //Data dari database akan diubah kedalam bentuk JSON
    private void request() {
        StringRequest strReq = new StringRequest(Request.Method.GET, Config.requestMonsterList, responses -> {
            try {
                JSONObject response = new JSONObject(responses);
                JSONObject header = response.getJSONObject("data");
                //Perulangan untuk mengambil data dari JSONObject yang disimpan kedalam variable header
                Iterator<String> iterator = header.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject data = (JSONObject) header.get(key);

                    //Mengirim data dari variable 'data' di JSONObject ke dalam Monster.java menggunakan Setter
                    Monster model = new Monster();
                    model.setId(data.getString("id"));
                    model.setMonster_name(data.getString("monster_name"));
                    model.setMonster_description(data.getString("monster_description"));
                    model.setMonster_image(data.getString("monster_image"));
                    //memasukkan data yang sudah diambil kedalam ArrayList Monster
                    mListItems.add(model);
                }
                //Memasukkan data kedalam ListView lewat MonsterAdapter yang memiliki
                //2 parameter yaitu, MonsterListActivity, ArrayList Monster (mListItems)
                mListView.setAdapter(new MonsterAdapter(MonsterListActivity.this, mListItems));
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