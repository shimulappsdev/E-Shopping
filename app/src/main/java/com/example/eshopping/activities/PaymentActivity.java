package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshopping.R;
import com.example.eshopping.fragments.HomeFragment;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class PaymentActivity extends AppCompatActivity implements SSLCTransactionResponseListener {
    String tran_id = "0234325443546";
    String pro_ctg = "it";
    Cart cart;
    double totalcheckoutPrice = 0;
    int tax = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        cart = TinyCartHelper.getCart();

        totalcheckoutPrice =  ((cart.getTotalPrice().intValue()* tax / 100) + cart.getTotalPrice().intValue());

        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization(
                "creat613377970f2ed",
                "creat613377970f2ed@ssl",
                totalcheckoutPrice,
                SSLCCurrencyType.BDT,
                tran_id,
                pro_ctg,
                SSLCSdkType.TESTBOX
        );

        IntegrateSSLCommerz
                .getInstance(PaymentActivity.this)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(this);
    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {
        Intent intent =new Intent(PaymentActivity.this, HomeFragment.class);
        startActivity(intent);
    }

    @Override
    public void transactionFail(String s) {

    }

    @Override
    public void merchantValidationError(String s) {

    }
}