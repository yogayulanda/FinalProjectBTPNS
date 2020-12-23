package com.example.pdamapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdamapps.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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