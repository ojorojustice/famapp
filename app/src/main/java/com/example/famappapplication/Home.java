package com.example.famappapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

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
//                    case R.id.famapp_home:
//                        Toast.makeText(getApplicationContext(), "Home panel here", Toast.LENGTH_LONG).show();
//                        dLayout.closeDrawer(GravityCompat.START);
//                        break;

                    case R.id.famapp_add_family:
                        Toast.makeText(getApplicationContext(), "Add your family", Toast.LENGTH_LONG).show();
                        Intent addFamilyIntent = new Intent(Home.this, AddFamilyActivity.class);
                        startActivity(addFamilyIntent);
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_chat:
                        //Toast.makeText(getApplicationContext(), "Chat with an Expert", Toast.LENGTH_SHORT).show();
                        Intent chatIntent = new Intent(Home.this, LiveChat.class);
                        startActivity(chatIntent);
                        dLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.famapp_consultant:
                        Toast.makeText(getApplicationContext(), "Consult a child development Professor", Toast.LENGTH_LONG).show();
                        Intent talkToUs = new Intent(Home.this, TalkToUsActivity.class);
                        startActivity(talkToUs);
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_findhelp:
                        Toast.makeText(getApplicationContext(), "find help immediately online", Toast.LENGTH_LONG).show();
                        Intent learnFromCases = new Intent(Home.this, LearnFromCasesActivity.class);
                        startActivity(learnFromCases);
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
                        Intent shareIntent = new Intent(Home.this, ShareExperience.class);
                        startActivity(shareIntent);
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.famapp_exit:
                        Toast.makeText(getApplicationContext(), "Bye", Toast.LENGTH_LONG).show();
                        finish();
                        dLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }

        });

    }

}