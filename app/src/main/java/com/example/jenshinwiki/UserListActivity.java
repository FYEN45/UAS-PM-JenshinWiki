package com.example.jenshinwiki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.jenshinwiki.Config.Config;
import com.example.jenshinwiki.Controller.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

// UserListActivity digunakan untuk menampilkan daftar user yang telah terdaftar dalam database
// Genshin Impact Wiki.
public class UserListActivity extends AppCompatActivity {

    //Mendeklarasikan variabel dan komponen yang akan digunakan.
    ListView listViewUser;
    ArrayList<User> arrayListUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        initViews(); //Memanggil method initViews.
        requestUserData(); //Memanggil method requestUserData.

        //Memberikan fungsi kepada list view.
        // Apabila isi dari List View ditekan, maka user akan diperiksa terlebih dahulu apakah user atau admin.
        // Apabila user berupa admin, user akan di arahkan ke halaman UserUpdateActivity.
        listViewUser.setOnItemClickListener((parent, view, position, id) -> {
            if (TempLoginData.Temp_Status.equals("admin")) {
                //Apabila user memiliki status admin, user akan diarahkan ke halaman UserUpdateActivity
                // Halaman userUpdateActivity akan menerima data extra berupa id, nama, email, username, password, dan status
                // dari user yang ditekan pada List View.
                Intent intent = new Intent(UserListActivity.this, UserUpdateActivity.class);
                intent.putExtra("id", arrayListUser.get(position).getId());
                intent.putExtra("name", arrayListUser.get(position).getName());
                intent.putExtra("email", arrayListUser.get(position).getEmail());
                intent.putExtra("username", arrayListUser.get(position).getUsername());
                intent.putExtra("password", arrayListUser.get(position).getPassword());
                intent.putExtra("status", arrayListUser.get(position).getStatus());
                startActivity(intent);
            } else {
                //Apabila user tidak memiliki status admin, maka akan menampilkan error "Anda bukan admin!"
                Toast.makeText(UserListActivity.this, "Anda bukan Admin!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Menginisialisasikan komponen yang digunakan dalam layout user list.
    private void initViews() {
        listViewUser = findViewById(R.id.listViewUser);
    }

    //Method requestUserData digunakan untuk meminta webservices mengirimkan daftar dari user user
    // yang telah terdaftar pada aplikasi Genshin Impact Wiki.
    private void requestUserData() {
        //StringRequest merupakan sebuah bentuk request / permintaan kepada webservices.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.requestUserList, responses -> {
            try {
                JSONObject response = new JSONObject(responses); //Mengambil respon dari webservices dan mengubahnya menjadi JSONObject
                JSONObject header = response.getJSONObject("data"); //Mengambil data dari variabel response yang memiliki header "Data"

                //Meng-iterasikan data yang ada dalam variabel header.
                Iterator<String> iterator = header.keys();

                //while disini akan melakukan looping sebanyak data yang ada didalam iterator.
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONObject item = (JSONObject) header.get(key);

                    //Membuat Object User dan memberikan data user berupa id, nama, email, username,
                    // password, dan status yang didapat dari webservices.
                    User user = new User();
                    user.setId(item.getString("id"));
                    user.setName(item.getString("name"));
                    user.setEmail(item.getString("email"));
                    user.setUsername(item.getString("username"));
                    user.setPassword(item.getString("password"));
                    user.setStatus(item.getString("status"));

                    //Memasukkan objek user ke dalam arrayListUser, yang nantinya akan digunakan untuk
                    // menampilkan user user yang terdaftar.
                    arrayListUser.add(user);
                }
                //Meminta komponen listViewUser untuk menampilkan data user user pada UserListActivity.
                //untuk dapat menampilkan data user user, dibutuhkan bantuan dari kelas UserAdapter.
                listViewUser.setAdapter(new UserAdapter(UserListActivity.this, arrayListUser));
            } catch (JSONException e) {
                e.printStackTrace(); //Menangkap error yang terjadi pada JSON dan mencetaknya dalam Log.
            }
        }, error -> {
            //Apabila aplikasi gagal terhubung dengan webservices, pada aplikasi akan menampilkan
            // informasi kepada user.
            Toast.makeText(UserListActivity.this, "Gagal Terhubung dengan Server!", Toast.LENGTH_SHORT).show();
        });
        //Menjalankan permintaan untuk melakukan koneksi dengan webservices.
        AppController.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}