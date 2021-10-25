package com.moringaschool.helpdesk.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.AltRecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.models.Questions;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.viewmodel.QuestionsListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener{
    private View rootView;
    private List<Result> resultsList;
    private AltRecentPostsRecyclerAdapter recentPostsRecyclerAdapter;
    QuestionsListViewModel questionsListViewModel;
    private Context mContext;
    ChipGroup chipGroup;
    Chip chipTechnical, chipLogical, chipOther;
    SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
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
        setHasOptionsMenu(true);

        searchView = view.findViewById(R.id.app_bar_search);
        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        chipTechnical = view.findViewById(R.id.technical);
        chipLogical = view.findViewById(R.id.logical);
        chipOther = view.findViewById(R.id.other);

        CompoundButton.OnCheckedChangeListener checkedChangeListener =  new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    recentPostsRecyclerAdapter.getFilter().filter(compoundButton.getText().toString());
                }
            }
        };

        chipTechnical.setOnCheckedChangeListener(checkedChangeListener);
        chipLogical.setOnCheckedChangeListener(checkedChangeListener);
        chipOther.setOnCheckedChangeListener(checkedChangeListener);


        questionsListViewModel = new ViewModelProvider(this).get(QuestionsListViewModel.class);
        questionsListViewModel.makeApiCall();
        questionsListViewModel.getQuestionsListObserver().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultsList = results;
                Collections.reverse(resultsList);
                recentPostsRecyclerAdapter = new AltRecentPostsRecyclerAdapter(getActivity(), resultsList);
                RecyclerView recyclerView = rootView.findViewById(R.id.recent_posts_recyclerview);
                recyclerView.setAdapter(recentPostsRecyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search_widget, menu);

        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recentPostsRecyclerAdapter.getFilter().filter(s);
//                Toast.makeText(getActivity(), "Filter event", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Toast.makeText(getContext(), "Search Clicked", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
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
