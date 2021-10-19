package com.moringaschool.helpdesk.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringaschool.helpdesk.R;

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
            Intent intent =  new Intent(LauncherActivity.this, SignupActivity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
        }
    }
}