package com.example.event.eventapp;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.event.eventapp.fragments.About;
import com.example.event.eventapp.fragments.Explore;
import com.example.event.eventapp.fragments.My_Schedule;
import com.example.event.eventapp.fragments.NaviMap;
import com.example.event.eventapp.fragments.Settings;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

public FragmentManager fragmentManager;

    private Toolbar mToolbar;
    private NavigationView mNvDrawer;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        //Setting toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Setting Drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        //hambager menu icon
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        // Setting navigation view
        mNvDrawer = (NavigationView) findViewById(R.id.nvView);
        mNvDrawer.setNavigationItemSelectedListener(this);
        mNvDrawer.getMenu();

    }

    @Override
    public void onBackPressed() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolbar)
        {
            return true;
        }
        else if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
            return super.onOptionsItemSelected(item);

        }



    @Override
    public boolean onNavigationItemSelected(MenuItem menu) {
        int ids = menu.getItemId();

        switch (ids) {

            case R.id.myschedule_item:
                My_Schedule myschedule = new My_Schedule();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, myschedule);
                fragmentTransaction.commit();
                break;
            case R.id.explore_item:
                Explore explore = new Explore();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, explore);
                fragmentTransaction.commit();
                break;
            case R.id.map_item:
                NaviMap navimap = new NaviMap();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, navimap);
                fragmentTransaction.commit();
                break;
            case R.id.settings_item:
                Settings settings = new Settings();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, settings);
                fragmentTransaction.commit();

                break;
            case R.id.about_item:
                About about = new About();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, about);
                fragmentTransaction.commit();
                break;
        default: Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
            break;
        }
        menu.setCheckable(true);
        drawerLayout.closeDrawers();
        return true;
    }

}
