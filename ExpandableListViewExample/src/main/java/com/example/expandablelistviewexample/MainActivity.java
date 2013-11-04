package com.example.expandablelistviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;

    public static final String EXTRA_MESSAGE_1 = "GroupPos";
    public static final String EXTRA_MESSAGE_2 = "ChildPos";

    public static Map<Integer, String[]> xmlMap = new HashMap<Integer, String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandList = (ExpandableListView) findViewById(R.id.listView);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(MainActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        ExpandList.setOnChildClickListener(HandleChildClick);
    }


    ExpandableListView.OnChildClickListener HandleChildClick = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPos, int childPos, long rowId) {
            Intent i = new Intent(getApplicationContext(), CommandDetails.class);
            i.putExtra(EXTRA_MESSAGE_1, groupPos);
            i.putExtra(EXTRA_MESSAGE_2, childPos);
            startActivity(i);
            return false;
        }
    };

    public ArrayList<ExpandListGroup> SetStandardGroups() {
        ArrayList<ExpandListGroup> expandListGroups = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> expandListChildren = new ArrayList<ExpandListChild>();

        String[] basicCommands = getResources().getStringArray(R.array.BasicCommands);
        String[] sshCommands = getResources().getStringArray(R.array.SSHCommands);

        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Section 1");
        for (int i = 0; i < basicCommands.length; i++)
        {
            ExpandListChild item = new ExpandListChild();
            item.setName(basicCommands[i]);
            item.setTag(null);
            expandListChildren.add(item);
        }
        gru1.setItems(expandListChildren);
        xmlMap.put(0, basicCommands);


        expandListChildren = new ArrayList<ExpandListChild>();
        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("Section2");
        for (int i = 0; i < sshCommands.length; i++)
        {
            ExpandListChild item = new ExpandListChild();
            item.setName(sshCommands[i]);
            item.setTag(null);
            expandListChildren.add(item);
        }
        gru2.setItems(expandListChildren);
        xmlMap.put(1, sshCommands);

        expandListGroups.add(gru1);
        expandListGroups.add(gru2);

        return expandListGroups;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
