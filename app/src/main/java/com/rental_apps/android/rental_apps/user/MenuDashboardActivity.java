package com.rental_apps.android.rental_apps.user;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.AboutActivity;
import com.rental_apps.android.rental_apps.ActivityLogin;
import com.rental_apps.android.rental_apps.PrefsApplication;
import com.rental_apps.android.rental_apps.R;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.adapter.CarsAdapter;
import com.rental_apps.android.rental_apps.adapter.CarsUserAdapter;
import com.rental_apps.android.rental_apps.admin.ActivityCreateMobil;
import com.rental_apps.android.rental_apps.admin.ActivityDetailUsers;
import com.rental_apps.android.rental_apps.admin.AdminEditProfile;
import com.rental_apps.android.rental_apps.model.model_user.DataUser;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.move;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MenuDashboardActivity extends Fragment implements InitComponent, View.OnClickListener {
    Context mContext;
    private ImageView menu_mobil;
    private ImageView menu_history;
    private ImageView menu_profil;
    private ImageView menu_logout;
    private View rootView;
    ViewFlipper v_flipper;
    private static final String TEXT_FRAGMENT = "RENTCAR";

    public static MenuDashboardActivity newInstance(String text){
        MenuDashboardActivity mFragment = new MenuDashboardActivity();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mContext=getActivity();
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.activity_menu_dashboard, container, false);
        startInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_icon, menu);
        setItem(menu);
    }

    private void setItem(Menu menu){
        MenuItem menuAdd = menu.findItem(R.id.add);
        menuAdd.setVisible(false);
        MenuItem menuRefresh = menu.findItem(R.id.refresh);
        menuRefresh.setVisible(false);
        MenuItem menuSetting = menu.findItem(R.id.action_settings);
        menuSetting.setVisible(false);
    }

    @Override
    public void startInit() {
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar()  {  getActivity().setTitle(getArguments().getString(TEXT_FRAGMENT)); }

    public void initUI() {
            menu_mobil = (ImageView)rootView.findViewById(R.id.menu_mobil);
            menu_history = (ImageView)rootView.findViewById(R.id.menu_history);
            menu_profil = (ImageView)rootView.findViewById(R.id.menu_profil);
            menu_logout = (ImageView)rootView.findViewById(R.id.menu_logout);
        int images[] = {R.drawable.background2,
                R.drawable.background3, R.drawable.bg_drawer
                , R.drawable.bg};
        v_flipper = (ViewFlipper) rootView.findViewById(R.id.v_flipper);
        for (int i = 0; i < images.length; i++) {
            fliverImages(images[i]);
        }
        for (int image : images)
            fliverImages(image);
        }

    @Override
    public void initValue() {

    }

    @Override
    public void initEvent() {
        menu_history.setOnClickListener(this);
        menu_mobil.setOnClickListener(this);
        menu_profil.setOnClickListener(this);
        menu_logout.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Toasty.success(mContext, "Tambah", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                move.moveActivity(mContext, AboutActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void fliverImages(int images) {
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(images);
        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(mContext, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(mContext, android.R.anim.slide_out_right);
    }
    public void onClick (View v){
            switch (v.getId()){
                case R.id.menu_history:
                    move.moveActivity(mContext, UserMain2.class);
                    break;
                case R.id.menu_mobil:
                    move.moveActivity(mContext,UserMain3.class);
                    break;
                case R.id.menu_profil:
                    move.moveActivity(mContext,ActivityDetailUsers.class);
                    break;
                case R.id.menu_logout:
                    move.moveActivity(mContext,AdminEditProfile.class);
                    break;
            }
        }

}


