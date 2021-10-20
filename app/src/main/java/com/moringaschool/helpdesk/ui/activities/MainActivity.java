package com.moringaschool.helpdesk.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.helpdesk.R;
import com.moringaschool.helpdesk.constants.Constants;
import com.moringaschool.helpdesk.models.PostQuestion;
import com.moringaschool.helpdesk.models.QuestionObject;
import com.moringaschool.helpdesk.models.Result;
import com.moringaschool.helpdesk.network.PostQuestionApi;
import com.moringaschool.helpdesk.network.PostQuestionClient;
import com.moringaschool.helpdesk.ui.fragments.HomeFragment;
import com.moringaschool.helpdesk.ui.fragments.PostQuestionDialog;
import com.moringaschool.helpdesk.ui.fragments.PostQuestionDialogSheet;
import com.moringaschool.helpdesk.ui.fragments.ProfileFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, PostQuestionDialogSheet.BottomSheetListener {
    private final String TAG = MainActivity.class.getSimpleName();
    private String mTitle;
    private String mBody;
    private String mCategory;
    private String mLanguage;

    private SharedPreferences mSharedPreferences;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser currentUser;
    List<Result> results;

    FloatingActionButton fab;
    BottomNavigationView bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Constants.authToken = mSharedPreferences.getString(Constants.AUTH_TOKEN_PREFERENCE, null);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        // ini
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        bottomNav.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    public void onClick(View view) {
        if (view == fab){
            PostQuestionDialogSheet postQuestionDialogSheet = new PostQuestionDialogSheet();
            postQuestionDialogSheet.show(getSupportFragmentManager(), "Post Question Dialog Sheet");
        }
    }

    @Override
    public void onFloatingActionButtonSubmit(String category, String title, String body, String language) {

        PostQuestionApi postQuestionClient = PostQuestionClient.postQuestion();
        PostQuestion postQuestion = new PostQuestion(category, title, body, language);

        Call<QuestionObject> call = postQuestionClient.postQuestion(postQuestion);
        call.enqueue(new Callback<QuestionObject>() {
            @Override
            public void onResponse(@NonNull Call<QuestionObject> call, @NonNull Response<QuestionObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Question Posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Unable to post question", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, category + ", " + language, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<QuestionObject> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_widget, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch(item.getItemId()){
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_profile:
                selectedFragment = new ProfileFragment();
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    }

    // logs out the user
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void openDialog(){
        PostQuestionDialog postQuestionDialog = new PostQuestionDialog();
        postQuestionDialog.show(getSupportFragmentManager(), "question dialog");
    }
}