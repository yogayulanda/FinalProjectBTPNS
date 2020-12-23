package com.example.pdamapps.networking;

import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @POST("/add/")
    Call<APIResponse> register (@Body RegisterRequest registerRequest);

    @POST("/login/")
    Call<APIResponse> login (@Body LoginRequest loginRequest);

    @GET("/nasabah")
    Call<APIResponse> getNasabah();

    @GET("/saldo")
    Call<APIResponse> getSaldo();

}