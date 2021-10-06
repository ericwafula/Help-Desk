package com.moringaschool.helpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextView = (TextView) findViewById(R.id.no_account);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mTextView){
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }
    }
}