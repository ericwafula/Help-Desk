package com.moringaschool.helpdesk.network;

import com.moringaschool.helpdesk.models.Questions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GeneralQuestionsApi {
    @GET("question/")
    Call<Questions> getGeneralQuestions();
}
