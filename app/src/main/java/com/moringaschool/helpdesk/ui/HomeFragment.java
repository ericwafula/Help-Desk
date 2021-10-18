package com.moringaschool.helpdesk.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.AltRecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.adapters.RecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.models.Questions;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.network.GeneralQuestionsApi;
import com.moringaschool.helpdesk.network.GeneralQuestionsClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String tag = getTag();

        ImageView post = view.findViewById(R.id.post);

        ArrayList<String> cardTitle = new ArrayList<>();
        ArrayList<String> cardBody = new ArrayList<>();
        ArrayList<String> readMore = new ArrayList<>();

        RecyclerView recentPostItemsRecyclerview = view.findViewById(R.id.recent_posts_recyclerview);

        cardTitle.add("Am having trouble integrating Heroku...");
        cardBody.add("Am having trouble integrating Heroku with Python Flask");
        readMore.add("Read More...");

        cardTitle.add("Am having trouble integrating Django...");
        cardBody.add("Am having trouble integrating Heroku with Python Django");
        readMore.add("Read More...");

        cardTitle.add("Am having trouble integrating Android...");
        cardBody.add("Am having trouble integrating Android with Firebase");
        readMore.add("Read More...");


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                getResponses(recentPostItemsRecyclerview);
            }
        });
    }

    public void openDialog(){
        PostQuestionDialog postQuestionDialog = new PostQuestionDialog();
        postQuestionDialog.setTargetFragment(HomeFragment.this, 1);
        assert getFragmentManager() != null;
        postQuestionDialog.show(getFragmentManager(), "question dialog");
    }

    @Override
    public void applyQuestion(String title, String body) {
        Toast.makeText(getActivity(), "Toast Works: " + title + ", " + body, Toast.LENGTH_SHORT).show();
    }

    public void getResponses(RecyclerView recyclerView){
        GeneralQuestionsApi generalQuestionsApi = GeneralQuestionsClient.generalQuestions();
        Call<Questions> call = generalQuestionsApi.getGeneralQuestions();

        call.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(@NonNull Call<Questions> call, @NonNull Response<Questions> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Result> results = response.body().getResults();

                    AltRecentPostsRecyclerAdapter recentPostsRecyclerAdapter = new AltRecentPostsRecyclerAdapter(getActivity(), results);
                    recyclerView.setAdapter(recentPostsRecyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    Toast.makeText(getActivity(), "Response unsuccessful: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Questions> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
