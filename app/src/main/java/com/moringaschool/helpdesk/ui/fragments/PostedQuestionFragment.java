package com.moringaschool.helpdesk.ui.fragments;

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
import com.moringaschool.helpdesk.adapters.ResponsesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostedQuestionFragment extends Fragment {
    TextView postedTitle;
    TextView postedBody;
    Bundle bundle;

    ArrayList<String> mResponses =  new ArrayList<>();
    ArrayList<String> mUpVotes =  new ArrayList<>();
    ArrayList<String> mDownVotes = new ArrayList<>();

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

        postedTitle = (TextView) view.findViewById(R.id.p_title);
        postedBody = (TextView) view.findViewById(R.id.p_body);

        bundle = getArguments();
        assert bundle != null;
        String title = bundle.getString("pTitle");
        String body = bundle.getString("pBody");

        postedTitle.setText(title);
        postedBody.setText(body);
        addResponses();

        ResponsesRecyclerAdapter adapter = new ResponsesRecyclerAdapter(getActivity(), mResponses, mUpVotes, mDownVotes);
        RecyclerView postedQuestionRecyclerview = (RecyclerView) view.findViewById(R.id.posted_question_recyclerview);
        postedQuestionRecyclerview.setAdapter(adapter);
        postedQuestionRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void addResponses(){
        mResponses.add("Make sure you have the right dependencies");
        mUpVotes.add("9");
        mDownVotes.add("3");

        mResponses.add("You are using and old version of python");
        mUpVotes.add("1");
        mDownVotes.add("2");

        mResponses.add("You haven't imported the right dependencies");
        mUpVotes.add("4");
        mDownVotes.add("0");

        mResponses.add("Make sure you have the right dependencies");
        mUpVotes.add("9");
        mDownVotes.add("3");
    }
}
