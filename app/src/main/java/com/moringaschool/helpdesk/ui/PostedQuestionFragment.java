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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.RecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.adapters.ResponsesRecyclerAdapter;

import java.util.ArrayList;

public class PostedQuestionFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posted_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView post = view.findViewById(R.id.post);

        ArrayList<String> mResponses = new ArrayList<>();
        ArrayList<String> mUpVotes = new ArrayList<>();
        ArrayList<String> mDownVotes = new ArrayList<>();

        mResponses.add("Make sure you have the right dependencies");
        mUpVotes.add("25");
        mDownVotes.add("13");

        mResponses.add("You have a newer version of flask installed But you have outdated dependencies");
        mUpVotes.add("79");
        mDownVotes.add("8");

        mResponses.add("The dependencies on your project don't match with the one's you've imported");
        mUpVotes.add("28");
        mDownVotes.add("3");

        RecyclerView responsesRecyclerview = view.findViewById(R.id.posted_question_recyclerview);
        ResponsesRecyclerAdapter responsesRecyclerAdapter = new ResponsesRecyclerAdapter(getContext(), mResponses, mUpVotes, mDownVotes);
        responsesRecyclerview.setAdapter(responsesRecyclerAdapter);
        responsesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        PostQuestionDialog postQuestionDialog = new PostQuestionDialog();
        postQuestionDialog.setTargetFragment(PostedQuestionFragment.this, 1);
        assert getFragmentManager() != null;
        postQuestionDialog.show(getFragmentManager(), "question dialog");
    }

    @Override
    public void applyQuestion(String title, String body) {
        Toast.makeText(getActivity(), "Toast Works: " + title + ", " + body, Toast.LENGTH_SHORT).show();
    }
}
