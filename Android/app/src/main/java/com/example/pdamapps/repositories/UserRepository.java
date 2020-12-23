package com.example.pdamapps.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
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

    public MutableLiveData<APIResponse> postLogin(LoginRequest loginRequest){
        String request = new Gson().toJson(loginRequest);
        System.out.println("request : " + request);
        MutableLiveData<APIResponse> response = new MutableLiveData<>();
        api.login(loginRequest).enqueue(new Callback<APIResponse>() {
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
}
