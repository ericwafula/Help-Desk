package com.moringaschool.helpdesk.network;

import com.moringaschool.helpdesk.models.Login;
import com.moringaschool.helpdesk.models.PostQuestion;
import com.moringaschool.helpdesk.models.QuestionObject;
import com.moringaschool.helpdesk.models.Questions;
import com.moringaschool.helpdesk.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostQuestionApi {
    @POST("question/")
    Call<QuestionObject> postQuestion(@Body PostQuestion question);
}
