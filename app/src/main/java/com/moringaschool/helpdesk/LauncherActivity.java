package com.moringaschool.helpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LauncherActivity extends AppCompatActivity implements View.OnClickListener{
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mButton = (Button) findViewById(R.id.get_started);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mButton){
            startActivity(new Intent(LauncherActivity.this, SignupActivity.class));
        }
    }
}