package com.moringaschool.helpdesk.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.models.Constants;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = SignupActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    DatabaseReference mUserReference;
    private FirebaseAuth.AuthStateListener mAuthListener;

    TextView mTextView;
    EditText mName;
    EditText mEmail;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mName = (EditText) findViewById(R.id.editTextTextPersonName);
        mEmail = (EditText) findViewById(R.id.editTexTextEmail);
        mPassword = (EditText) findViewById(R.id.editTexTextPassword);
        mConfirmPassword = (EditText) findViewById(R.id.editTexTextConfirmPassword);
        mSignupButton = (Button) findViewById(R.id.signup);

        mTextView = (TextView) findViewById(R.id.have_an_account);
        mTextView.setOnClickListener(this);
        mSignupButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    public void onClick(View view) {
        if (view == mTextView){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mSignupButton){
            createNewUser();
        }
    }

    public void createNewUser(){
        final String name = mName.getText().toString().trim();
        final String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Authentication successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}