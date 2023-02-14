package com.example.eshopping.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eshopping.Model.Item_Categorie_Model;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;

import java.util.List;

public class Products_adapter extends RecyclerView.Adapter<Products_adapter.categorisviewholder> {
    Context context;
    List<Item_Product_Model> product_modelList;

    public Products_adapter(Context context, List<Item_Product_Model> product_modelList) {
        this.context = context;
        this.product_modelList = product_modelList;
    }

    @NonNull
    @Override
    public categorisviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new categorisviewholder(LayoutInflater.from(context).inflate(R.layout.product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Products_adapter.categorisviewholder holder, int position) {
        Item_Product_Model product_list = product_modelList.get(position);
        holder.product_name.setText(Html.fromHtml(product_list.getProduct_name()));
        holder.product_price.setText(Integer.valueOf("BDT"+product_list.getProduct_price()));
        Glide.with(context).load(product_list.getProduct_Image())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return product_modelList.size();
    }

    public class categorisviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView product_name, product_price;

        public categorisviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_img);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);

        }
    }
}
