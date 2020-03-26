package com.example.recycleview;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.recycleview.util.ServerAPI;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DasboardActivity extends AppCompatActivity implements ProdukAdapter.ItemClickListener {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    ArrayList<Produk> mItems;
    Button btnInsert, btnDelete;
    ProgressDialog pd;
    private SharedPreferences mPreferences;
    private ProdukAdapter adapter;
    private String sharedPrefFile = "com.example.recycleview";
    private ArrayList<Produk> produkArrayList;
    private ArrayList<String> listGambar;
    ViewFlipper v_flipper;
    public int tot = 0;
    private int sumHarga = 0;
    String dataProduk[] = null;
    String dS[] = null;
    TextView txt_id, txt_username;
    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_username = (TextView) findViewById(R.id.txt_username);
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
//image untuk slider, flipper
        int images[] = {R.drawable.a_bunga2,
                R.drawable.bibit_2, R.drawable.anggrek_ka
                , R.drawable.kampung_anggrek};
        v_flipper = findViewById(R.id.v_flipper);
        for (int i = 0; i < images.length; i++) {
            fliverImages(images[i]);
        }
        for (int image : images)
            fliverImages(image);
        mRecyclerview = (RecyclerView)
                findViewById(R.id.recycler_view);
        pd = new ProgressDialog(DasboardActivity.this);
        mItems = new ArrayList<>();
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
// Membaca Semua Barang
        loadJson();
//mManager = newLinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false);
//mRecyclerview.setLayoutManager(mManager);
        mAdapter = new ProdukAdapter(mItems);
        adapter = new ProdukAdapter(mItems);
//mRecyclerview.setAdapter(mAdapter);
//GRID 2 kolom
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//STAGGER 4 KOLOM
// StaggeredGridLayoutManager llm=newStaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(adapter);
//mAdapter.setClickListener(this);
        adapter.setClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to theaction bar
// if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The actionbar will
// automatically handle clicks on the Home/Upbutton, so long
// as you specify a parent activity inAndroidManifest.xml.
        int id2 = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id2 == R.id.miMessage) {
            Uri urisms = Uri.parse("smsto:6285712032051");
            Intent sms = new Intent(Intent.ACTION_SENDTO, urisms);
            sms.putExtra("sms_body", "Ini Pesan");
            startActivity(sms);
            return true;
        }
        if (id2 == R.id.miPhone) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "082134772921"));
            startActivity(intent);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            return true;
        }

        if (id2 == R.id.miLocation) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            // Using the coordinates for Google headquarters.
            String data = getString(R.string.google_mtv_coord_zoom12);
            intent.setData(Uri.parse(data));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            return true;
        }

        if (id2 == R.id.miUpdate) {
            Intent intent = new Intent(this, UpdateActivity.class);
            startActivity(intent);
            return true;
        }

        if (id2 == R.id.miLogOut) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(LoginActivity.session_status, false);
            editor.putString(TAG_ID, null);
            editor.putString(TAG_USERNAME, null);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.clear().apply();
            Toast.makeText(this, "Anda Berhasil LogOut", Toast.LENGTH_LONG).show();
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
//noinspection SimplifiableIfStatement

    }

    public void postTotals() {
        TextView txtTot = (TextView)
                findViewById(R.id.totalPrice);
        DecimalFormat decimalFormat = new
                DecimalFormat("#,##0.00");
        txtTot.setText("Rp. " + decimalFormat.format(tot));

    }

    public void onClick(View view, int position) {
        final Produk mhs = mItems.get(position);
        switch (view.getId()) {
            case R.id.nm_brg:
                Intent i = new Intent(this, DetailActivity.class);
                startActivity(i);
                return;
            case R.id.image:
                tot = tot + Integer.parseInt(mhs.getHarga());
                Toast.makeText(this, "gambare....." +
                        mhs.getNama(), Toast.LENGTH_SHORT).show();
                postTotals();
                return;
            default:
                Toast.makeText(this, "lainnya..... -> " +
                        mhs.getNama() + " Rp. " + tot, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();
        JsonArrayRequest reqData = new
                JsonArrayRequest(Request.Method.POST,
                ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override

                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley", "response : " +
                                response.toString());
                        for (int i = 0; i <
                                response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                Produk md = new Produk();
                                md.setKode(data.getString("kd_brg"));
                                md.setNama(data.getString("nm_brg"));
                                md.setHarga(data.getString("harga"));
                                md.setImg(data.getInt("image"));

                                mItems.add(md);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
//mAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void
                    onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " +
                                error.getMessage());
                    }
                });
        com.example.recycleview.util.AppController.getInstance().addToRequestQueue(reqData);
    }

    public void ambildata() {
        listGambar = new ArrayList<String>();
        listGambar.add("anggrek1");
        listGambar.add("anggrek2");
        listGambar.add("anggrek3");
        listGambar.add("anggrek4");
        listGambar.add("anggrek5");
    }

    public void checkout(View view) {
        DecimalFormat decimalFormat = new
                DecimalFormat("#,##0.00");
        Toast.makeText(this, "Total Rp. " +
                decimalFormat.format(tot), Toast.LENGTH_SHORT).show();
        sumHarga = tot ;
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("total", sumHarga);
        preferencesEditor.apply();
        Intent intent = new Intent(this, PembayaranActivity.class);
        startActivity(intent);
    }

    public void fliverImages(int images) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}

