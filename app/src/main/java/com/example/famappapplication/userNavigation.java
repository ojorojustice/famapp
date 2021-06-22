package com.example.famappapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class userNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navigation);


        ActionBarDrawerToggle aToggle;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.navmenu);
        DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawer);
        aToggle = new ActionBarDrawerToggle(this, dLayout, toolbar, R.string.open, R.string.close);
        System.out.println(dLayout);
//        dLayout.addDrawerListener(aToggle);
        aToggle.syncState();


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.famapp_home:
                        Toast.makeText(getApplicationContext(), "Home panel here", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.famapp_add_family:
                        Toast.makeText(getApplicationContext(), "Add your family", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_chat:
                        Toast.makeText(getApplicationContext(), "Chat with an Expert", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.famapp_consultant:
                        Toast.makeText(getApplicationContext(), "Send mail to a child development Professor", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_findhelp:
                        Toast.makeText(getApplicationContext(), "find help immediately online", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_profile:
                        Toast.makeText(getApplicationContext(), "update your profile", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_report:
                        Toast.makeText(getApplicationContext(), "report an abuse", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_share:
                        Toast.makeText(getApplicationContext(), "You can share an experience", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_exit:
                        Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_LONG).show();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }

        });

    }

}