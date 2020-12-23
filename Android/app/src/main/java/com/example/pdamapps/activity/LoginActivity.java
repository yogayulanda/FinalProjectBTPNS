package com.example.pdamapps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.pdamapps.R;
import com.example.pdamapps.databinding.ActivityLoginBinding;
import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.viewsmodels.UserViewModel;

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

    private void doLogin(){
        String username =  binding.usernameGet.getText().toString();
        String password =  binding.passwordGet.getText().toString();

        if (password.equals("")){
            Toast.makeText(getApplicationContext(),"Password Harus Di Isi", Toast.LENGTH_SHORT).show();
        } else {
            LoginRequest loginRequest = new LoginRequest(username, password);
            userViewModel.postLogin(loginRequest).observe(this, nasabahResponse -> {
                System.out.println("atas response : " + nasabahResponse.getMessage());
                APIResponse response = nasabahResponse;
                if (response.getResponse() == 200) {
                    moveToMessageActivity("Sukses", "Selamat Anda Berhasil Terdaftar", 200);
                }
            });
        }

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
