package com.example.tust.tmdbmovieviewer.Activity;

import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.TextView;


import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.widget.SearchView;
import com.example.tust.tmdbmovieviewer.R;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends TabActivity{

    private TextView mStatusView;
    private ActionBar actionBar;
    private FragmentTabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       TabHost tabHost = getTabHost();

        //tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        //tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);



       // tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab1"),
        //        PopularActivity.class, null);
        //tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Tab2"),
         //       TopRatedActivity.class, null);
        //tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Tab3"),
         //       UpcomingActivity.class, null);


       TabHost.TabSpec inboxSpec = tabHost.newTabSpec("Popular");
        // Tab Icon
        inboxSpec.setIndicator("Popular");
        Intent inboxIntent = new Intent(this, PopularActivity.class);
        // Tab Content
        inboxSpec.setContent(inboxIntent);

        // Outbox Tab
        TabHost.TabSpec outboxSpec = tabHost.newTabSpec("Top Rated");
        outboxSpec.setIndicator("Top Rated");
        Intent outboxIntent = new Intent(this, TopRatedActivity.class);
        outboxSpec.setContent(outboxIntent);

        // Profile Tab
        TabHost.TabSpec profileSpec = tabHost.newTabSpec("Upcoming");
        profileSpec.setIndicator("Upcoming");
        Intent profileIntent = new Intent(this, UpcomingActivity.class);
        profileSpec.setContent(profileIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxSpec); // Adding Inbox tab
        tabHost.addTab(outboxSpec); // Adding Outbox tab
        tabHost.addTab(profileSpec); // Adding Profile tab

    }


 }


