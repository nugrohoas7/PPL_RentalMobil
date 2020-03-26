package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {
    public interface ItemClickListener {
        void onClick(View view, int position);
    }
    private ArrayList<Produk> dataList;
    private String KEY_TOT="TOT";
    public double tot=0;
    int gambar[] = {R.drawable.anggrekaksara, R.drawable.anggrekbibir, R.drawable.anggrekbulan, R.drawable.anggrekcattleya, R.drawable.anggrekhitam
    ,R.drawable.anggrekjamrud,R.drawable.anggrekjingga,R.drawable.anggrekmonyet};
    public ProdukAdapter(ArrayList<Produk> dataList)
    {
        this.dataList = dataList;
    }
    private ItemClickListener clickListener;
    @Override
    public ProdukViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ProdukViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ProdukViewHolder holder, int position){
        holder.txtNama.setText(dataList.get(position).getNama());
        holder.txtKode.setText(dataList.get(position).getKode());
        holder.txtHarga.setText(dataList.get(position).getHarga()
        );
        holder.icon.setImageResource(gambar[position]);
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    public void setClickListener(ItemClickListener
                                         clickListener) {
        this.clickListener = clickListener;
    }
    public class ProdukViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNama, txtKode, txtHarga;
        private ImageView icon;
        public ProdukViewHolder(View itemView) {

            super(itemView);
            txtNama = (TextView)
                    itemView.findViewById(R.id.nm_brg);
            txtKode = (TextView)
                    itemView.findViewById(R.id.kd_brg);
            txtHarga = (TextView)
                    itemView.findViewById(R.id.harga);
            icon=(ImageView)
                    itemView.findViewById(R.id.image);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            txtNama.setOnClickListener(this);
            icon.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(clickListener != null)
                clickListener.onClick(view,
                        getAdapterPosition());
        }
    }
    public double getTot()
    {
        return tot;
    }
    public void setTot(double tot)
    {
        this.tot=tot;
    }
}
