package com.moringaschool.helpdesk.ui.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.ui.activities.LoginActivity;

public class FaqFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String TAG = getActivity().getClass().getSimpleName().toString();

        ImageView logoutIcon = view.findViewById(R.id.logout_icon);
        TextView logoutText = view.findViewById(R.id.logout_text);
        ImageView callIcon = view.findViewById(R.id.call_icon);
        TextView callText = view.findViewById(R.id.call_text);
        ImageView emailIcon = view.findViewById(R.id.mail_icon);
        TextView emailText = view.findViewById(R.id.email_text);

        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("+254712293878"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "applicationNotFound: : " + e);
                }
            }
        });

        callText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("+254712293878"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "applicationNotFound: : " + e);
                }
            }
        });

        emailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("contact@moringaschool.com"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "applicationNotFound: : " + e);
                }
            }
        });

        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("contact@moringaschool.com"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    Toast.makeText(getActivity(), "Application not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "applicationNotFound: : " + e);
                }
            }
        });
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
