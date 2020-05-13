package com.rental_apps.android.rental_apps.admin;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.model.model_user.DataUser;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.validate;
import com.squareup.picasso.Picasso;

import customfonts.MyEditText;
import customfonts.MyTextView;
import de.hdodenhof.circleimageview.CircleImageView;


public class ActivityDetailUsers extends AppCompatActivity implements InitComponent {
    private MyTextView name;
    private MyTextView nik;
    private MyTextView email;
    private MyTextView noTelp;
    private MyTextView address;
    private MyTextView gender;
    private MyTextView status;
    private CircleImageView userPhoto;
    Context mContext;
    Toolbar toolbar;
    DataUser user;


    @Override
    protected void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_detail_user);

        Gson gson = new Gson();
        user= gson.fromJson(getIntent().getStringExtra("user"), DataUser.class);

        mContext=this;
        startInit();
    }

    @Override
    public void startInit() {
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar() {
        toolbar=(Toolbar)findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_action_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void initUI() {
        name=(MyTextView)findViewById(R.id.name);
        nik=(MyTextView)findViewById(R.id.nik);
        email=(MyTextView)findViewById(R.id.email);
        noTelp=(MyTextView)findViewById(R.id.notelp);
        address=(MyTextView)findViewById(R.id.address);
        gender=(MyTextView)findViewById(R.id.gender);
        status=(MyTextView)findViewById(R.id.status);
        userPhoto=(CircleImageView)findViewById(R.id.userPhoto);

    }

    @Override
    public void initValue() {
        name.setText(Prefs.getString(SPref.getNAME(),""));
        nik.setText(Prefs.getString(SPref.getNIK(),""));
        email.setText(Prefs.getString(SPref.getEMAIL(),""));
        noTelp.setText(Prefs.getString(SPref.getNoTelp(),""));
        address.setText(Prefs.getString(SPref.getALAMAT(),""));
        // JK=Prefs.getString(SPref.getGender(),"");
        gender.setText(Prefs.getString(SPref.getGender(), "").equalsIgnoreCase(String.valueOf('L')) ? "Laki-laki" : "Perempuan");

        status.setText("Aktif");


    }

    @Override
    public void initEvent() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setPreference(DataUser du){
        Prefs.putInt(SPref.getIdUser(),du.getId_user());
        Prefs.putString(SPref.getNIK(),du.getNik());
        Prefs.putString(SPref.getUSERNAME(),du.getUsername());
        Prefs.putString(SPref.getNAME(),du.getName());
        Prefs.putString(SPref.getEMAIL(),du.getEmail());
        Prefs.putString(SPref.getNoTelp(),du.getNo_telp());
        Prefs.putString(SPref.getGender(),du.getGender().toString());
        Prefs.putString(SPref.getPHOTO(),du.getPhoto());
        Prefs.putString(SPref.getLastUpdate(),du.getLast_update().toString());
        Prefs.putString(SPref.getALAMAT(),du.getAlamat());
        Prefs.putInt(SPref.getGroupUser(),du.getGroup_user());
        Prefs.putString(SPref.getPASSWORD(),du.getPassword().toString());
    }
}
