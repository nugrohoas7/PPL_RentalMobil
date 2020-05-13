package com.rental_apps.android.rental_apps.user;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
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

import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.adapter.CarsAdapter;
import com.rental_apps.android.rental_apps.adapter.CarsUserAdapter;
import com.rental_apps.android.rental_apps.admin.ActivityDetailUsers;
import com.rental_apps.android.rental_apps.admin.AdminEditProfile;
import com.rental_apps.android.rental_apps.model.model_user.DataUser;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.move;

import java.util.List;

public class MenuDashboardActivity extends Fragment implements InitComponent, View.OnClickListener {
    Context mContext;
    private ImageView menu_mobil;
    private ImageView menu_history;
    private ImageView menu_profil;
    private ImageView menu_logout;
    private View rootView;
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


    public void onClick (View v){
            switch (v.getId()){
                case R.id.menu_history:
                    move.moveActivity(mContext, UserMain2.class);
                    break;
                case R.id.menu_mobil:
                    move.moveActivity(mContext,UserMain3.class);
                    break;
                case R.id.menu_profil:
                    move.moveActivity(mContext,AdminEditProfile.class);
                    break;
                case R.id.menu_logout:
                    Prefs.clear();
                    move.moveActivity(mContext,ActivityLogin.class);
                    break;
            }
        }

}


