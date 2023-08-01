package com.example.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity2 extends TabActivity {

    // Declaring TabHost and TabSpec objects
    TabHost tabHost;
    TabHost.TabSpec spec1;
    TabHost.TabSpec spec2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initializing the Tab Host
        tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        // Creating tab1
        spec1=tabHost.newTabSpec("Tab 1");

        // Using the tab1.xml file for tab1 content
        spec1.setContent(R.id.tab1);

        // Setting a label and an icon for tab1
        spec1.setIndicator("E-MAIL", getResources().getDrawable(R.drawable.ic_baseline_ad_units_24));

        // Calling ActivityOne through intent
        Intent in1=new Intent(this, ActivityOne.class);
        spec1.setContent(in1);

        // Creating tab2
        spec2=tabHost.newTabSpec("Tab 2");
        spec2.setContent(R.id.tab2);

        // Using the tab2.xml file for tab2 content
        spec2.setContent(R.id.tab2);
        // Setting a label and an icon for tab2
        spec2.setIndicator("MESSAGES",getResources().getDrawable(R.drawable.ic_baseline_ad_units_24));

        // Calling ActivityTwo through intent
        Intent in2=new Intent(this, ActivityTwo.class);
        spec2.setContent(in2);

        // Adding tab1 and tab2 to the TabHost
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
    }
}

