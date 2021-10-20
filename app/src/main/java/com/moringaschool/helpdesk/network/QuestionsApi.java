package com.moringaschool.helpdesk.network;

import com.moringaschool.helpdesk.models.Questions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsApi {
    @GET("question/")
    Call<Questions> getGeneralQuestions();

    @GET("question/user/")
    Call<Questions> getUserQuestions();
}
