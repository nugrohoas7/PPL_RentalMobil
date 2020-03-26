package com.example.recycleview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgbarang;
    private TextView txtnama, txtharga, txtstock;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.recycleview";

    ArrayList<HashMap<String, String>> list_data;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String url = "http://192.168.43.187/getdata.php"; //sesuaikan dengan ip pc anda
        imgbarang = findViewById(R.id.image);
        txtnama = findViewById(R.id.txtnama);
        txtharga = findViewById(R.id.txtharga);
        txtstock = findViewById(R.id.txtstock);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        requestQueue = Volley.newRequestQueue(DetailActivity.this);

        Map<String,List<String>> map = new HashMap<String, List<String>>();
        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("ppb");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("kd_brg", json.getString("kd_brg"));
                        map.put("nm_brg", json.getString("nm_brg"));
                        map.put("image", json.getString("image"));
                        map.put("harga", json.getString("harga"));
                        map.put("stok", json.getString("stok"));
                        list_data.add(map);
                    }
                    Glide.with(getApplicationContext())
                            .load("http://192.168.43.187/gambare/" + list_data.get(0).get("image"))
                            .transition(withCrossFade())
                            .placeholder(R.drawable.a_bunga2)
                            .into(imgbarang);
                    txtnama.setText(list_data.get(0).get("nm_brg"));
                    txtharga.setText(list_data.get(0).get("harga"));
                    txtstock.setText(list_data.get(0).get("stok"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
