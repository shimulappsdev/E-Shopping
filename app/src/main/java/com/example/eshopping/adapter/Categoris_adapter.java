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
import com.example.eshopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Categoris_adapter extends RecyclerView.Adapter<Categoris_adapter.categorisviewholder> {
    Context context;
    List<Item_Categorie_Model> categorie_modelList;

    public Categoris_adapter(Context context, List<Item_Categorie_Model> categorie_modelList) {
        this.context = context;
        this.categorie_modelList = categorie_modelList;
    }

    @NonNull
    @Override
    public categorisviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new categorisviewholder(LayoutInflater.from(context).inflate(R.layout.categoris_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull categorisviewholder holder, int position) {
        Item_Categorie_Model cate_list = categorie_modelList.get(position);
        holder.cate_name.setText(Html.fromHtml(cate_list.getCate_name()));
        Picasso.get().load(cate_list.getCate_image()).into(holder.cate_imageView);

    }


    @Override
    public int getItemCount() {
        return categorie_modelList.size();
    }

    public class categorisviewholder extends RecyclerView.ViewHolder {
        ImageView cate_imageView;
        TextView cate_name;
        public categorisviewholder(@NonNull View itemView) {
            super(itemView);
            cate_imageView=itemView.findViewById(R.id.cate_img);
            cate_name=itemView.findViewById(R.id.cate_name);

        }
    }
}
