package com.rental_apps.android.rental_apps.adapter;

/**
 * Created by Ujang Wahyu on 29/01/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;
import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.SPreferenced.SPref;
import com.rental_apps.android.rental_apps.model.model_history.DataHistory;
import com.rental_apps.android.rental_apps.user.ActivityDetailListHistory;

import java.util.List;

import customfonts.MyTextView;



public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{
    private List<DataHistory> historyList;
    private Context mContext;
    private int lastPosition=0;
    private View mView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView almt;
        private TextView name;
        private TextView telepon;
        private TextView kode_transaksi;
        private TextView tglambil;
        private TextView tglkembali;
        private TextView status;
        private TextView total_transaksi;
        private LinearLayout bg_transaksi;
        public MyViewHolder(View view) {
            super(view);
            mView=view;
            kode_transaksi=(TextView) view.findViewById(R.id.kode_transaksi);
            tglambil=(TextView) view.findViewById(R.id.tglambil);
            tglkembali=(TextView) view.findViewById(R.id.tglkembali);
            status=(TextView) view.findViewById(R.id.status);
            almt=(TextView)view.findViewById(R.id.almt);
            name=(TextView)view.findViewById(R.id.name);
            telepon=(TextView)view.findViewById(R.id.telepon);
            total_transaksi=(TextView)view.findViewById(R.id.total_transaksi);
            bg_transaksi=(LinearLayout) view.findViewById(R.id.bg_transaksi);
            this.view=view;
            mContext=view.getContext();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson=new Gson();
                    String transaksi=gson.toJson(historyList.get(getAdapterPosition()));
                    Intent i=new Intent(mContext, ActivityDetailListHistory.class);
                    i.putExtra("transaksi",transaksi);
                    mContext.startActivity(i);
                }
            });
        }

        public void bindItem(DataHistory transaksi) {
            kode_transaksi.setText(transaksi.getKODETRANSAKSI());
            tglambil.setText(transaksi.getTGLORDER());
            almt.setText(Prefs.getString(SPref.getALAMAT(),""));
            name.setText(Prefs.getString(SPref.getNAME(),""));
            telepon.setText(Prefs.getString(SPref.getNoTelp(),""));
            tglkembali.setText(transaksi.getTGLPEMBAYARAN());
            if(transaksi.getSTATUSPEMBAYARAN()=="1")
                status.setText("Lunas");
            else status.setText("Belum Lunas");

            total_transaksi.setText("Rp. "+String.format("%,.2f", Double.parseDouble(transaksi.getTOTALPEMBAYARAN().toString())));

            if (transaksi.getSTATUSPEMBAYARAN().equals("0")){
                bg_transaksi.setBackgroundColor(Color.parseColor("#FF9800"));
                status.setText("Belum Lunas");
            }else{
                bg_transaksi.setBackgroundColor(Color.parseColor("#4CAF50"));
                status.setText("Lunas");
            }
        }
    }


    public HistoryAdapter(List<DataHistory> historyList) {
        this.historyList= historyList;
    }


    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_history,parent,false);
        return new HistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder holder, int position) {
        holder.bindItem(historyList.get(position));
        setAnimation(mView, position);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    private void setAnimation(View viewToAnimate,int position) {
        if (position > lastPosition) {
            lastPosition = position;
            Animation animation = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slide_left_to_right);
            viewToAnimate.startAnimation(animation);
        }
    }
}
