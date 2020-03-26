package com.example.recycleview;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PembayaranActivity extends AppCompatActivity {
    private Button buttonTambah;
    private EditText _totTrans;
    private EditText _totBayar;
    private EditText _totKembali;
    private EditText _namaPembeli;
    private EditText _almtPembeli;

    String GetTrans, GetBayar, GetKembali, GetNamaPembeli, GetAlamat;

    //PHP
    String Nama_Pembeli = "Nama_Pembeli";
    String Alamat_Pembeli = "Alamat_Pembeli";
    String Total_Transaksi = "Total_Transaksi";
    String Total_Bayar = "Total_Bayar";
    String Kembali = "Kembali";
    ProgressDialog progressDialog ;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;
    boolean check = true;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.recycleview";
    private int totTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        buttonTambah = (Button)findViewById(R.id.buttonTambah);

        _totTrans = findViewById(R.id.totTrans);
        _totBayar = findViewById(R.id.totBayar);
        _totKembali = findViewById(R.id.totKembali);
        _namaPembeli = findViewById(R.id.namapem);
        _almtPembeli = findViewById(R.id.almtpem);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        totTrans = mPreferences.getInt("total", 0);
        _totTrans.setText(Integer.toString(totTrans));

        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTrans = _totTrans.getText().toString();
                GetBayar = _totBayar.getText().toString();
                GetKembali = _totKembali.getText().toString();
                GetNamaPembeli = _namaPembeli.getText().toString();
                GetAlamat = _almtPembeli.getText().toString();

                int trans = Integer.parseInt(GetTrans);
                int bayar = Integer.parseInt(GetBayar);
                int kembali = Integer.parseInt(GetKembali);

                if(GetNamaPembeli.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Kolom Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if(!GetNamaPembeli.trim().equals("") && !GetBayar.trim().equals("")){
                    Intent pindah = new Intent(getApplicationContext(),PembayaranActivity.class);
                    pindah.putExtra("Nama_Pembeli", GetNamaPembeli.toString());
                    pindah.putExtra("Total_Transaksi", trans);
                    pindah.putExtra("Total_Bayar", bayar);
                    pindah.putExtra("Kembali", kembali);
                    pindah.putExtra("Alamat_Pembeli", GetAlamat.toString());
                    startActivity(pindah);
                    finish();
                    tambahKeranjang();
                }
            }
        });
    }
    public void tambahKeranjang(){
        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(PembayaranActivity.this,"Proses Menambahkan ke Keranjang","Mohon menunggu",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {
                super.onPostExecute(string1);
                progressDialog.dismiss();
                Toast.makeText(PembayaranActivity.this,string1,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                PembayaranActivity.ImageProcessClass imageProcessClass = new ImageProcessClass();
                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(Nama_Pembeli, GetNamaPembeli);
                HashMapParams.put(Total_Transaksi, GetTrans);
                HashMapParams.put(Total_Bayar, GetBayar);
                HashMapParams.put(Kembali, GetKembali);
                HashMapParams.put(Alamat_Pembeli, GetAlamat);

                String FinalData = imageProcessClass.ImageHttpRequest("http://kampoenganggrekdony.000webhostapp.com/PembayaranActivity.php", HashMapParams);
                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                url = new URL(requestURL);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(bufferedWriterDataFN(PData));
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                RC = httpURLConnection.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    String RC2;
                    while ((RC2 = bufferedReader.readLine()) != null){
                        stringBuilder.append(RC2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {
            stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check) {
                    check = false;
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }
            return stringBuilder.toString();
        }
    }

    public void bayar(View view) {
        if(checkInputKosong(_totBayar.getText().toString())){
            Toast.makeText(this, "Input Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return;
        }else {
            int totBayar = Integer.parseInt(_totBayar.getText().toString());
            if(totBayar > totTrans) {
                int kembali = totBayar - totTrans;
                _totKembali.setText(Integer.toString(kembali));
            }else {
                Toast.makeText(this, "Total Bayar Tidak Mencukupi", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean checkInputKosong(String a) {
        if(a.equals("")) {
            return true;
        }
        return false;
    }
}
