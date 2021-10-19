package com.moringaschool.helpdesk.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginClient {
    public static LoginApi LoginUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://helpdeskapps.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(LoginApi.class);
    }
}
