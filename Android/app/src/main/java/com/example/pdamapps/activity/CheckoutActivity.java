package com.example.pdamapps.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdamapps.R;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void Back(View view){
        Intent intent = new Intent(CheckoutActivity.this, CheckoutActivity.class);
        startActivity(intent);
    }
}