package com.example.pdamapps.viewsmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pdamapps.model.APIResponse;
import com.example.pdamapps.model.LoginRequest;
import com.example.pdamapps.model.RegisterRequest;
import com.example.pdamapps.repositories.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<APIResponse> mutableLiveData;
    public void init(){
        if(mutableLiveData!=null){
            return;
        }
        userRepository = userRepository.getInstance();
    }
    public LiveData<APIResponse> postRegister(RegisterRequest registerRequest){
        if(mutableLiveData==null){
            userRepository = userRepository.getInstance();
        }
        mutableLiveData = userRepository.postRegister(registerRequest);
        return mutableLiveData;
    }

    public LiveData<APIResponse> postLogin(LoginRequest loginRequest){
        if(mutableLiveData==null){
            userRepository = userRepository.getInstance();
        }
        mutableLiveData = userRepository.postLogin(loginRequest);
        return mutableLiveData;
    }
}


