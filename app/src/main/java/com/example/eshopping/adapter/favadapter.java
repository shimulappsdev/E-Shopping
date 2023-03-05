package com.example.eshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.OfflineStorage;
import com.example.eshopping.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class favadapter extends RecyclerView.Adapter<favadapter.favviewholder> {
    Context context;
    ArrayList<String> favlist;

    public favadapter(Context context, ArrayList<String> favlist) {
        this.context = context;
        this.favlist = favlist;
    }

    @NonNull
    @Override
    public favviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new favadapter.favviewholder(LayoutInflater.from(context).inflate(R.layout.fav_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull favviewholder holder, int position) {
       // Item_Product_Model product_list = product_modelList.get(position);
       //  Picasso.get().load(favlist.get(position)).into(holder.favimg);
         holder.favname.setText(favlist.get(position));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OfflineStorage offlineStorage =new OfflineStorage(context);
             //   offlineStorage.remove();
           // favlist.remove(position);
            notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return favlist.size();
    }

    public class favviewholder extends RecyclerView.ViewHolder {
            TextView favname,favprice;
            ImageView delete,favimg;
        public favviewholder(@NonNull View itemView) {
            super(itemView);
            favname= itemView.findViewById(R.id.favname);
            favprice= itemView.findViewById(R.id.favprice);
            delete= itemView.findViewById(R.id.delete);
            favimg= itemView.findViewById(R.id.favimg);
        }
    }
}
