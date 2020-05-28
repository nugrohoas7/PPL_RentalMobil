package com.rental_apps.android.rental_apps.user;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
import com.rental_apps.android.rental_apps.AboutActivity;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.adapter.CarsAdapter;
import com.rental_apps.android.rental_apps.adapter.CarsUserAdapter;
import com.rental_apps.android.rental_apps.adapter.Carts;
import com.rental_apps.android.rental_apps.admin.ActivityCreateMobil;
import com.rental_apps.android.rental_apps.admin.AdminEditProfile;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.helper.DrawableCounter;
import com.rental_apps.android.rental_apps.model.model_mobil.DataCars;
import com.rental_apps.android.rental_apps.model.model_mobil.ResponseCars;
import com.rental_apps.android.rental_apps.myinterface.InitComponent;
import com.rental_apps.android.rental_apps.utils.move;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class UserListCars extends Fragment implements InitComponent{

    //Declate Toolbar Tittle
    private static final String TEXT_FRAGMENT = "RENTCAR";

    //Declare Component View
    private TextView mTxtTitle;
    private View rootView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerCars;
    ViewFlipper v_flipper;
    //Declate Activity Context
    Context mContext;

    //Declare Object Cars
    ResponseCars dataCars;
    List<DataCars> listCars=new ArrayList<>();
    Menu mnn;

    //Declare Adapter
    private CarsUserAdapter mAdapter;

    public static UserListCars newInstance(String text){
        UserListCars mFragment = new UserListCars();
        Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext=getActivity();
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.fragment_admin_cars, container, false);
        startInit();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_user_icon, menu);
        setCart(menu);
    }

    public void setCart(Menu menu){
        MenuItem menuItem = menu.findItem(R.id.cart);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();

        DrawableCounter badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof DrawableCounter) {
            badge = (DrawableCounter) reuse;
        } else {
            badge = new DrawableCounter(mContext);
        }

        badge.setCount(""+Carts.getSize(SPref.getCARTS()));
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);
        MenuItem menuAdd = menu.findItem(R.id.add);
        menuAdd.setVisible(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                getCars();
                return true;
            case R.id.add:
                move.moveActivity(mContext,ActivityCreateMobil.class);
                return true;
            case R.id.cart:
                move.moveActivity(mContext,ActivityListTransaksi.class);
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
    @Override
    public void startInit() {
        initToolbar();
        initUI();
        initValue();
        initEvent();
    }

    @Override
    public void initToolbar() {
        getActivity().setTitle(getArguments().getString(TEXT_FRAGMENT));
    }

    @Override
    public void initUI() {
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        recyclerCars = (RecyclerView)rootView.findViewById(R.id.rCarList);
        int images[] = {R.drawable.promo1,
                R.drawable.promo2, R.drawable.promo3
                , R.drawable.desainmobil};
        v_flipper = (ViewFlipper) rootView.findViewById(R.id.v_flipper);
        for (int i = 0; i < images.length; i++) {
            fliverImages(images[i]);
        }
        for (int image : images)
            fliverImages(image);
    }


    @Override
    public void initValue() {
        prepareCars();
        getCars();
    }

    @Override
    public void initEvent() {

    }

    public void getCars(){
        final Call<ResponseCars> cars= client.getApi().dataMobil();
        cars.enqueue(new Callback<ResponseCars>() {
            @Override
            public void onResponse(Call<ResponseCars> call, Response<ResponseCars> response) {
                if (response.isSuccessful()) {
                    dataCars=response.body();
                    if (dataCars.getStatus()) {
                        listCars.clear();
                        listCars.addAll(dataCars.getData());
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toasty.error(mContext, "gagal", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCars> call, Throwable t) {
                Toasty.error(mContext,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareCars(){
        mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerCars.setLayoutManager(mLayoutManager);
        mAdapter = new CarsUserAdapter(listCars);
        recyclerCars.setHasFixedSize(true);
        recyclerCars.setItemAnimator(new DefaultItemAnimator());
        recyclerCars.setAdapter(mAdapter);
        recyclerCars.setItemAnimator(new SlideLeftAlphaAnimator());
        recyclerCars.getItemAnimator().setAddDuration(500);
        recyclerCars.getItemAnimator().setRemoveDuration(500);
    }

    @Override
    public void onResume(){
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }



}
