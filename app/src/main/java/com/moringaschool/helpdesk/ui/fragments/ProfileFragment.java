package com.moringaschool.helpdesk.ui.fragments;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.adapters.RecentPostsRecyclerAdapter;
import com.moringaschool.helpdesk.ui.LoginActivity;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements PostQuestionDialog.PostQuestionDialogListener {

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

        FirebaseAuth mAuth;
        FirebaseAuth.AuthStateListener mAuthListener;
        FirebaseUser currentUser;

        TextView post = view.findViewById(R.id.post);
        ImageView logout = view.findViewById(R.id.ic_logout);
        TextView username = view.findViewById(R.id.username);

        ArrayList<String> cardTitle = new ArrayList<>();
        ArrayList<String> cardBody = new ArrayList<>();
        ArrayList<String> readMore = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        assert currentUser != null;
        username.setText(currentUser.getDisplayName());

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
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
    }
}
