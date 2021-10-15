package com.moringaschool.helpdesk.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.helpdesk.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser currentUser;

    BottomNavigationView bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // ini
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        bottomNav.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
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
            case R.id.nav_posted_question:
                selectedFragment = new PostedQuestionFragment();
//                openDialog();
                break;
//            case R.id.nav_faq:
//                selectedFragment = new FaqFragment();
//                break;
//            case R.id.nav_logout:
//                Toast.makeText(MainActivity.this, "Logging you out", Toast.LENGTH_SHORT).show();
//                selectedFragment = new HomeFragment();
//                logout();
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