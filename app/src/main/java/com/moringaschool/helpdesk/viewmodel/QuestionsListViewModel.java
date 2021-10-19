package com.moringaschool.helpdesk.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.moringaschool.helpdesk.models.Questions;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.network.GeneralQuestionsApi;
import com.moringaschool.helpdesk.network.GeneralQuestionsClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsListViewModel extends ViewModel {
    MutableLiveData <List<Result>> questionsList;

    public QuestionsListViewModel() {
        questionsList = new MutableLiveData<>();
    }

    public MutableLiveData <List<Result>> getQuestionsListObserver() {
        return questionsList;
    }

    public void makeApiCall() {
        GeneralQuestionsApi generalQuestionsApi = GeneralQuestionsClient.generalQuestions();
        Call <Questions> call = generalQuestionsApi.getGeneralQuestions();

       call.enqueue(new Callback<Questions>() {
           @Override
           public void onResponse(@NonNull Call<Questions> call, @NonNull Response<Questions> response) {
               if (response.isSuccessful()) {
                   assert response.body() != null;
                   questionsList.setValue(response.body().getResults());
                   System.out.println("Success...");
               } else {
                   System.out.println("Not successful...");
               }
           }

           @Override
           public void onFailure(@NonNull Call<Questions> call, @NonNull Throwable t) {
                questionsList.postValue(null);
           }
       });
    }
}
