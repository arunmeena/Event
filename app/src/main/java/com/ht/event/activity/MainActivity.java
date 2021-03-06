package com.ht.event.activity;


import android.content.Intent;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.ht.event.R;
import com.ht.event.application.AppController;
import com.ht.event.fragments.AboutFragment;
import com.ht.event.fragments.ExploreFragment;
import com.ht.event.fragments.MyScheduleFragment;

import com.ht.event.fragments.NaviMapFragment;

import com.ht.event.fragments.SettingsFragment;
import com.ht.event.model.User;
import com.ht.event.utils.EventsPreferences;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FragmentManager fragmentManager;
    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;
    private String imageUrl;
    private com.nostra13.universalimageloader.core.ImageLoader imgLoader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        //Setting toolbar
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting Drawer layout
       drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
       ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        //hambager menu icon
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        // Setting navigation view
         NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(this);
        nvDrawer.getMenu();

        //default fragment
        ExploreFragment explore = new ExploreFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, explore);
        fragmentTransaction.commit();

        //setting profile page
          View headerView=nvDrawer.getHeaderView(0);
        User user = EventsPreferences.getUser(this);
        CircleImageView profileImage =(CircleImageView) headerView.findViewById(R.id.circleImageViewProfile);
        imageUrl = user.getImage();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imgLoader.init(config);

        RelativeLayout drawerImage =(RelativeLayout)headerView.findViewById(R.id.containerDrawerImage);
        if (user.getEmail()!=null) {
            drawerImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                    startActivity(intent);
                }
            });
        }
        //Setting User name
         TextView userName = (TextView) headerView.findViewById(R.id.textViewUserName);

        if(user.getImage()!= null){
            imgLoader.displayImage(imageUrl,profileImage);
            userName.setText(user.getName());


        }




    }
    @Override
    public void onBackPressed() {

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

        switch(id){
            case android.R.id.home:
            if (drawerLayout.isDrawerOpen(GravityCompat.START))  {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
                break;

            case R.id.filter:
            {
                Intent intent =new Intent(this,FilterActivity.class);
                startActivity(intent);
                    return true;
            }

            case R.id.map:
            {
                Intent intent =new Intent(this,MapActivity.class);
                startActivity(intent);
                return true;
            }

        }
            return super.onOptionsItemSelected(item);

        }


    @Override
    public boolean onNavigationItemSelected(MenuItem menu) {
        int ids = menu.getItemId();

        switch (ids) {

            case R.id.myschedule_item:
                MyScheduleFragment myschedule = new MyScheduleFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, myschedule);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.explore_item:
                ExploreFragment explore = new ExploreFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, explore);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.map_item:
                NaviMapFragment navimap = new NaviMapFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, navimap);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.settings_item:
                SettingsFragment settings = new SettingsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, settings);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
            case R.id.about_item:
                AboutFragment about = new AboutFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, about);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;


        default:Toast.makeText(this, "Not Implemented!", Toast.LENGTH_SHORT).show();
            break;
        }

        menu.setChecked(true);
        setTitle(menu.getTitle());
        drawerLayout.closeDrawers();

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


