package com.moringaschool.helpdesk.network;

import com.moringaschool.helpdesk.models.Login;
import com.moringaschool.helpdesk.models.Signup;
import com.moringaschool.helpdesk.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("auth/login/")
    Call<User> loginUser(@Body Login login);

    @POST("auth/register/")
    Call<ResponseBody> signupUser(@Body Signup signup);
}
