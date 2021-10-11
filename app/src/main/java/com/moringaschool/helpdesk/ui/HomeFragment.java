package com.moringaschool.helpdesk.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.RecentPostsRecyclerAdapter;

import java.util.ArrayList;

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

        TextView post = view.findViewById(R.id.post);

        ArrayList<String> cardTitle = new ArrayList<>();
        ArrayList<String> cardBody = new ArrayList<>();
        ArrayList<String> readMore = new ArrayList<>();

        cardTitle.add("Am having trouble integrating Heroku...");
        cardBody.add("Am having trouble integrating Heroku with Python Flask");
        readMore.add("Read More...");

        cardTitle.add("Am having trouble integrating Django...");
        cardBody.add("Am having trouble integrating Heroku with Python Django");
        readMore.add("Read More...");

        cardTitle.add("Am having trouble integrating Android...");
        cardBody.add("Am having trouble integrating Android with Firebase");
        readMore.add("Read More...");

        RecyclerView recentPostItemsRecyclerview = view.findViewById(R.id.recent_posts_recyclerview);
        RecentPostsRecyclerAdapter recentPostsRecyclerAdapter = new RecentPostsRecyclerAdapter(getContext(), cardTitle, cardBody, readMore);
        recentPostItemsRecyclerview.setAdapter(recentPostsRecyclerAdapter);
        recentPostItemsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
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
}
