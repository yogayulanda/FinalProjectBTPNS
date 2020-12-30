package com.example.pdamapps.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdamapps.R;
import com.example.pdamapps.databinding.ActivityLoginBinding;
import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.model.User;
import com.example.pdamapps.viewsmodels.UserViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    public void Register(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    void onClickGroup(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void init(){
        binding.usernameGet.setText("yoga");
        binding.passwordGet.setText("yoga");
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
    }

    void doLogin() {
        String username = binding.usernameGet.getText().toString();
        String password = binding.passwordGet.getText().toString();
        LoginRequest loginRequest = new LoginRequest(username, password);
        userViewModel.postLogin(loginRequest).observe(this, apiResponse -> {

            if (apiResponse.getResponse()== 200) {
                User user = apiResponse.getData();
                System.out.println(user.getIdNasabah());
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.pdamapps", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("com.example.pdamapps.login", binding.usernameGet.getText().toString());
                editor.putString("com.example.pdamapps.idNasabah", String.valueOf(user.getIdNasabah()));
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Gagal Login")
                        .setMessage("Username atau Password salah")
                        .setNegativeButton("close", null)
                        .show();
            }
        });
    }
    void moveToMessageActivity(String status, String message, int code){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("status", status);
        bundle.putString("message", message);
        bundle.putInt("code", code);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
