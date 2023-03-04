package com.example.eshopping.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eshopping.CartListener;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.databinding.CartItemLayoutBinding;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
        Context context;
        ArrayList<Item_Product_Model> products;
        CartListener cartListener;
         Cart cart;
    public CartAdapter(Context context, ArrayList<Item_Product_Model> products, CartListener cartListener) {
        this.context = context;
        this.products = products;
        this.cartListener = cartListener;
        cart = TinyCartHelper.getCart();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_layout,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Item_Product_Model product = products.get(position);
        Picasso.get().
                load(product.getProduct_image()).
                into(holder.binding.cartImage);
        holder.binding.productName.setText(product.getProduct_name());
        holder.binding.priceAmount.setText("BDT "+product.getProduct_price());

        holder.binding.incrementBtn.setOnClickListener(view1 -> {
            int quantity=product.getQuantity();
            quantity++;
            if(quantity>product.getProduct_stock()) {
                Toast.makeText(context, "Max stock available: "+ product.getProduct_stock(), Toast.LENGTH_SHORT).show();
                return;
            } else {
                product.setQuantity(quantity);
                holder.binding.quantity.setText(String.valueOf(quantity));
            }
            notifyDataSetChanged();
            cart.updateItem(product,product.getQuantity());
            cartListener.subtotal();

        });

       holder.binding.decrementBtn.setOnClickListener(view1 -> {
            int quantity=product.getQuantity();
            if(quantity > 1)
                quantity--;
            product.setQuantity(quantity);
           holder.binding.quantity.setText(String.valueOf(quantity));
            notifyDataSetChanged();
           cart.updateItem(product,product.getQuantity());
           cartListener.subtotal();

        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        CartItemLayoutBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CartItemLayoutBinding.bind(itemView);
        }
    }
}
