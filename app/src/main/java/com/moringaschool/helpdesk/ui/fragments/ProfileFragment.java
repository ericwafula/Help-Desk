package com.moringaschool.helpdesk.ui.fragments;

import android.content.Intent;
import android.media.Image;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.AltRecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.ui.activities.LoginActivity;
import com.moringaschool.helpdesk.viewmodel.QuestionsListViewModel;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProfileFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container_profile);

        FirebaseAuth mAuth;
        FirebaseAuth.AuthStateListener mAuthListener;
        FirebaseUser currentUser;

        ImageView help = view.findViewById(R.id.help);
        TextView username = view.findViewById(R.id.username);
        TextView noPostsFound = view.findViewById(R.id.no_posts_found);
        LottieAnimationView emptyAnimation = view.findViewById(R.id.empty_animation);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        assert currentUser != null;
        username.setText(currentUser.getDisplayName());

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FaqFragment()).commit();
            }
        });


        questionsListViewModel = new ViewModelProvider(this).get(QuestionsListViewModel.class);
        questionsListViewModel.makeUserApiCall();
        questionsListViewModel.getUserQuestionsListObserver().observe(getViewLifecycleOwner(), new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                resultsList = results;
                Collections.reverse(resultsList);
                if (resultsList.size() == 0){
                    noPostsFound.setText("No posts found");
                    emptyAnimation.setVisibility(View.VISIBLE);
                }
                recentPostsRecyclerAdapter = new AltRecentPostsRecyclerAdapter(getActivity(), resultsList);
                RecyclerView recyclerView = view.findViewById(R.id.recent_posts_recyclerview);
                recyclerView.setAdapter(recentPostsRecyclerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        });


        //

    }

    public void openDialog(){
        PostQuestionDialog postQuestionDialog = new PostQuestionDialog();
        postQuestionDialog.setTargetFragment(ProfileFragment.this, 1);
        assert getFragmentManager() != null;
        postQuestionDialog.show(getFragmentManager(), "question dialog");
    }

    @Override
    public void applyQuestion(String title, String body) {
        Toast.makeText(getActivity(), "Toast Works: " + title + ", " + body, Toast.LENGTH_SHORT).show();
    }

    // logs out the user
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
