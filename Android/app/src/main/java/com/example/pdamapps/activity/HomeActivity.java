package com.example.pdamapps.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pdamapps.R;
import com.example.pdamapps.databinding.ActivityHomeBinding;
import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.Nasabah;
import com.example.pdamapps.model.NasabahResponse;
import com.example.pdamapps.viewsmodels.UserViewModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private UserViewModel userViewModel;
    private String idNasabah;
    SharedPreferences sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
    }

    void init() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
        sharedpref = getSharedPreferences("com.example.pdamapps", Context.MODE_PRIVATE);
        String username = sharedpref.getString("com.example.pdamapps.login", "");
        idNasabah = sharedpref.getString("com.example.pdamapps.idNasabah", "");

        System.out.println("Username : " + username);
        userViewModel.getNasabah(idNasabah).observe(this, apiResponse-> {
            while (apiResponse==null) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(apiResponse.getResponse()==200) {
                Nasabah nasabah = apiResponse.getData();
                DecimalFormat currency = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols Rp = new DecimalFormatSymbols();
                Rp.setCurrencySymbol("Rp. ");
                Rp.setMonetaryDecimalSeparator(',');
                Rp.setGroupingSeparator('.');
                currency.setDecimalFormatSymbols(Rp);
                binding.saldoTextView.setText(currency.format(nasabah.getSaldo()));
                System.out.println("id"+nasabah.getFullname());
            }});
    }

    public void Card(View view){
        Intent intent = new Intent(HomeActivity.this, WalletActivity.class);
        startActivity(intent);
    }

    public void Edit(View view){
        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void Checkout(View view){
        Intent intent = new Intent(HomeActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }
}