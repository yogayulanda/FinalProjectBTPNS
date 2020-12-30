package com.example.pdamapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NasabahResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private Nasabah data;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("response")
    @Expose
    private int response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Nasabah getData() {
        return data;
    }

    public void setData(Nasabah data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}


