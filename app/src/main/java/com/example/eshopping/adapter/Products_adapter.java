package com.example.eshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.activities.productdetailactivity;

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
    public void onBindViewHolder(@NonNull categorisviewholder holder, int position) {
        Item_Product_Model product_list = product_modelList.get(position);
        holder.product_name.setText(Html.fromHtml(product_list.getProduct_name()));
        holder.product_price.setText("BDT"+product_list.getProduct_price());
        Glide.with(context).load(product_list.getProduct_Image())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
        Intent productintent =new Intent(context, productdetailactivity.class);
            productintent.putExtra("image",product_list.getProduct_Image());
            productintent.putExtra("name",product_list.getProduct_name());
            productintent.putExtra("price",product_list.getProduct_price());
            productintent.putExtra("id",product_list.getId());
            productintent.putExtra("description",product_list.getProduct_description());
            context.startActivity(productintent);
        });
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
