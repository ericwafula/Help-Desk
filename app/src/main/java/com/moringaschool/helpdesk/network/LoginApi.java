package com.moringaschool.helpdesk.network;

import com.moringaschool.helpdesk.models.Login;
import com.moringaschool.helpdesk.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("auth/login/")
    Call<User> loginUser(@Body Login login);
}
