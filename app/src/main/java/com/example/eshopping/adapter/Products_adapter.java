package com.example.eshopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.activities.productdetailactivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Picasso.get().load(product_list.getProduct_image()).into(holder.product_imageView);

        holder.product_imageView.setOnClickListener(view -> {
        Intent productintent =new Intent(context, productdetailactivity.class);
            productintent.putExtra("image",product_list.getProduct_image());
            productintent.putExtra("name",product_list.getProduct_name());
            productintent.putExtra("price",product_list.getProduct_price());
            productintent.putExtra("id",product_list.getId());
            context.startActivity(productintent);
        });

        holder.product_save.setOnClickListener(v -> {

            int product_id = product_list.getId();

            FirebaseUser firebaseUser;
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String currentUser = firebaseUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(currentUser).child("loved");

            Map<String, Object> loveMap = new HashMap<>();
            loveMap.put("product_id", product_id);
            loveMap.put("name", product_list.getProduct_name());
            loveMap.put("image", product_list.getProduct_image());
            loveMap.put("status", product_list.getProduct_status());
            loveMap.put("price", product_list.getItemPrice());
            loveMap.put("price_discount", product_list.getProduct_price_discount());
            loveMap.put("stock", product_list.getProduct_stock());

            databaseReference.setValue(loveMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){

                        Toast.makeText(context, "Added to Love", Toast.LENGTH_SHORT).show();

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show();
                }
            });


        });



    }


    @Override
    public int getItemCount() {
        return product_modelList.size();
    }

    public class categorisviewholder extends RecyclerView.ViewHolder {
        ImageView product_imageView,product_save;
        TextView product_name, product_price;

        public categorisviewholder(@NonNull View itemView) {
            super(itemView);
            product_imageView = itemView.findViewById(R.id.product_img);
            product_save = itemView.findViewById(R.id.product_save);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);

        }
    }
}
