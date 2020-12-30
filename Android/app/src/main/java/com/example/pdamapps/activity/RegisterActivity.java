package com.example.pdamapps.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pdamapps.R;
import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.RegisterRequest;
import com.example.pdamapps.viewsmodels.UserViewModel;
import com.example.pdamapps.databinding.ActivityRegisterBinding;

import java.time.LocalDate;

public class RegisterActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        onClickGroup();
    }

    void onClickGroup(){
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }

    private void init(){
        binding.fullNameGet.setText("yogayulanda");
        binding.emailGet.setText("yoga@gmail.com");
        binding.usernameGet.setText("yoga");
        binding.passwordGet.setText("yoga");
        binding.noTelpGet.setText("082286369596");
        binding.addressGet.setText("Jakarta");
        binding.genderGet.setText("Laki-Laki");
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.init();
    }

    private void doRegister(){
        String fullName =  binding.fullNameGet.getText().toString();
        String email =  binding.emailGet.getText().toString();
        String username =  binding.usernameGet.getText().toString();
        String password =  binding.passwordGet.getText().toString();
        String noTelp =  binding.noTelpGet.getText().toString();
        String address = binding.addressGet.getText().toString();
        String gender = binding.genderGet.getText().toString();
        String saldo = binding.addressGet.getText().toString();
        String tglLahir = binding.genderGet.getText().toString();

        if (password.equals("")){
            Toast.makeText(getApplicationContext(),"Password Harus Di Isi", Toast.LENGTH_SHORT).show();
        } else {

            RegisterRequest registerRequest = new RegisterRequest( fullName, email,  username,  password,  noTelp, gender, address);
            userViewModel.postRegister(registerRequest).observe(this, nasabahResponse -> {
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

    public void Back(View view){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}