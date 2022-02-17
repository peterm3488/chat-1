package com.example.amu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
         actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");

        firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView navigationView= findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) selectedListener);

        actionBar.setTitle("Home");
        HomeFragment fragment_home= new HomeFragment();
        FragmentTransaction ft1=getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.navigation,fragment_home, " ");
        ft1.commit();


    }
   private NavigationBarView.OnItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
   {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_Home:
                            //home fragment transaction
                            actionBar.setTitle("Home");
                            HomeFragment fragment_home= new HomeFragment();
                            FragmentTransaction ft1=getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.navigation,fragment_home,"");
                            ft1.commit();
                            return true;

                        case R.id.Profile:
                            //profile fragment transaction
                            actionBar.setTitle("Profile");
                            ProfileFragment fragment2= new ProfileFragment();
                            FragmentTransaction ft2=getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.navigation,fragment2, " ");
                            ft2.commit();
                            return true;


                        case R.id.nav_Users:
                            //profile fragment transaction
                            actionBar.setTitle("Users");
                            UsersFragment fragment3= new UsersFragment();
                            FragmentTransaction ft3=getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.navigation,fragment3, " ");
                            ft3.commit();
                            return true;
                    }
                    return false;
                }
            };



    private void checkUserStatus() {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
           //mProfileTv.setText(user.getEmail());
        } else {
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();


          }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Action_Logout) {
            firebaseAuth.signOut();
            checkUserStatus();

        }
        return super.onOptionsItemSelected(item);
    }

}