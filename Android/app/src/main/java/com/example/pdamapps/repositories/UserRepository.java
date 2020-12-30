package com.example.pdamapps.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.model.LoginResponse;
import com.example.pdamapps.model.NasabahResponse;
import com.example.pdamapps.model.RegisterRequest;
import com.example.pdamapps.networking.API;
import com.example.pdamapps.networking.RetrofitService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository userRepository;
    private API api;

    public static UserRepository getInstance(){
        if(userRepository==null){
            userRepository=new UserRepository();
        }
        return userRepository;
    }

    public UserRepository(){
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<APIResponse> postRegister(RegisterRequest registerRequest){
        String request = new Gson().toJson(registerRequest);
        System.out.println("request : " + request);
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.register(registerRequest).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> responses) {
                response.setValue(responses.body());
                System.out.println("test : " + responses.body());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                System.out.println("test ");
                System.out.println("Isian: " + t.getMessage());
            }
        });
        return response;
    }

    public MutableLiveData<LoginResponse> postLogin(LoginRequest loginRequest) {
        MutableLiveData<LoginResponse> loginResult = new MutableLiveData<>();
        api.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResult.setValue(null);
            }
        });
        return loginResult;
    }


    public MutableLiveData<NasabahResponse> getNasabah(String string) {
        MutableLiveData<NasabahResponse> saldoRequest = new MutableLiveData<>();
        Log.d("Test 1", "Test 1");
        api.getNasabah(string).enqueue(new Callback<NasabahResponse>() {
            @Override
            public void onResponse(Call<NasabahResponse> call, Response<NasabahResponse> response) {
                saldoRequest.setValue(response.body());
                System.out.println("Test : ");
            }

            @Override
            public void onFailure(Call<NasabahResponse> call, Throwable t) {
                saldoRequest.setValue(null);
                System.out.println("Test Failure");
            }
        });
        return saldoRequest;
    }
}
