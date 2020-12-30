package com.example.pdamapps.networking;

import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.model.LoginResponse;
import com.example.pdamapps.model.NasabahResponse;
import com.example.pdamapps.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("/add/")
    Call<APIResponse> register (@Body RegisterRequest registerRequest);

    @POST("/login/")
    Call<LoginResponse> login (@Body LoginRequest loginRequest);

    @GET("/nasabah/{idNasabah}")
    Call<NasabahResponse> getNasabah(@Path("idNasabah")String checkSaldo);

//    @GET("/saldo")
//    Call<APIResponse> getSaldo();

}