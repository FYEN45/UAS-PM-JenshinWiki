package com.example.jenshinwiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UserListActivity extends AppCompatActivity {
    ListView listViewUser;
    ArrayList<User> arrayListUser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        initViews();
        requestUserData();

        listViewUser.setOnItemClickListener((parent, view, position, id) -> {
            if(TempLoginData.Temp_Status.equals("admin")){
                Intent intent = new Intent(UserListActivity.this, UserUpdateActivity.class);
                intent.putExtra("id", arrayListUser.get(position).getId());
                intent.putExtra("name", arrayListUser.get(position).getName());
                intent.putExtra("email", arrayListUser.get(position).getEmail());
                intent.putExtra("username", arrayListUser.get(position).getUsername());
                intent.putExtra("password", arrayListUser.get(position).getPassword());
                intent.putExtra("status", arrayListUser.get(position).getStatus());
                startActivity(intent);
            } else {
                Toast.makeText(UserListActivity.this, "Anda bukan Admin!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews(){
        listViewUser = findViewById(R.id.listViewUser);
    }

    private void requestUserData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.requestUserList, responses -> {
            try {
                JSONObject response = new JSONObject(responses);
                JSONObject header = response.getJSONObject("data");
                Iterator<String> iterator = header.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject item = (JSONObject) header.get(key);

                    User user = new User();
                    user.setId(item.getString("id"));
                    user.setName(item.getString("name"));
                    user.setEmail(item.getString("email"));
                    user.setUsername(item.getString("username"));
                    user.setPassword(item.getString("password"));
                    user.setStatus(item.getString("status"));
                    arrayListUser.add(user);
                }
                listViewUser.setAdapter(new UserAdapter(UserListActivity.this, arrayListUser));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(UserListActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        });
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}