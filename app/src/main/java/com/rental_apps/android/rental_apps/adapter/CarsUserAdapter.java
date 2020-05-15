package com.rental_apps.android.rental_apps.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.api.client;
import com.rental_apps.android.rental_apps.helper.DrawableColor;
import com.rental_apps.android.rental_apps.model.model_detail_history.DataDetailHistory;
import com.rental_apps.android.rental_apps.model.model_mobil.DataCars;
import com.rental_apps.android.rental_apps.user.ActivityDetailUserCars;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Ujang Wahyu on 04/01/2018.
 */

public class CarsUserAdapter extends RecyclerView.Adapter<CarsUserAdapter.MyViewHolder>{
    private List<DataCars> carsList;
    private int lastPosition=-1;
    private Context mContext;
    DataCars dataCars;
    private View mView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView carName;
        private TextView price;
        public TextView status;
        private ImageView imgCar;
        private View view;

            public MyViewHolder(View view) {
            super(view);
            mView=view;
            carName = (TextView) view.findViewById(R.id.carName);
            price=(TextView)view.findViewById(R.id.price);
            status=(TextView)view.findViewById(R.id.status);
            imgCar=(ImageView)view.findViewById(R.id.imgCar);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();

                        String car = gson.toJson(carsList.get(getAdapterPosition()));
                        Intent i=new Intent(view.getContext(),ActivityDetailUserCars.class);
                        i.putExtra("car", car);
                        view.getContext().startActivity(i);
                }
            });
            this.view=view;
        }

        public void bindItem(DataCars cars) {
            carName.setText(cars.getNAMAMOBIL().toString());
           // bensin.setText(cars.getBENSINMOBIL().toString());
            price.setText("Rp. "+String.format("%,.2f", Double.parseDouble(cars.getHARGAMOBIL().toString())));


            if (Integer.parseInt(cars.getSTATUSSEWA().toString())==1){
                status.setText("Sedang Disewa");
              //  status.setBackgroundColor(Color.parseColor("#da4749"));
                status.setBackgroundResource(R.drawable.roundred);
            }
            if (Integer.parseInt(cars.getSTATUSSEWA().toString())==0) {
                status.setText("Tersedia");
                status.setBackgroundResource(R.drawable.round);
            }

            if (cars.getIMAGE().size()>0)
                Picasso.with(view.getContext()).load(client.getBaseImg()+"mobil/"+cars.getIMAGE().get(0)).into(imgCar);
            //Picasso.with(view.getContext()).load(client.getBaseImg()+"mobil/"+cars.getIMAGE().get(0)).resize(150,150).centerCrop().into(imgCar);




        }
    }


    public CarsUserAdapter(List<DataCars> carsList) {
        this.carsList = carsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_cars,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarsUserAdapter.MyViewHolder holder, int position) {
        holder.bindItem(carsList.get(position));
        setAnimation(mView, position);

    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    private void setAnimation(View viewToAnimate,int position) {
        if (position > lastPosition) {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slide_left_to_right);
            viewToAnimate.startAnimation(animation);
        }
    }
}

