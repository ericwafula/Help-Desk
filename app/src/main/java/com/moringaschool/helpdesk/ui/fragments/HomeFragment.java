package com.moringaschool.helpdesk.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.AltRecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.models.Questions;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.viewmodel.QuestionsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener{
    private View rootView;
    private List<Result> resultsList;
    private AltRecentPostsRecyclerAdapter recentPostsRecyclerAdapter;
    QuestionsListViewModel questionsListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String tag = getTag();

        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);


        questionsListViewModel = new ViewModelProvider(this).get(QuestionsListViewModel.class);
        questionsListViewModel.makeApiCall();
        questionsListViewModel.getQuestionsListObserver().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultsList = results;
                recentPostsRecyclerAdapter = new AltRecentPostsRecyclerAdapter(getActivity(), resultsList);
                RecyclerView recyclerView = rootView.findViewById(R.id.recent_posts_recyclerview);
                recyclerView.setAdapter(recentPostsRecyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);

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
