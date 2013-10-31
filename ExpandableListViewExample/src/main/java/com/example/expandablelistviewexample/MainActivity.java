package com.example.expandablelistviewexample;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListGroup> ExpListItems;
    private ExpandableListView ExpandList;

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
            return false;
        }
    };

    public ArrayList<ExpandListGroup> SetStandardGroups() {
        ArrayList<ExpandListGroup> expandListGroups = new ArrayList<ExpandListGroup>();
        ArrayList<ExpandListChild> expandListChildren = new ArrayList<ExpandListChild>();

        String[] basicCommands = getResources().getStringArray(R.array.BasicCommands);
        String[] sshCommands = getResources().getStringArray(R.array.SSHCommands);

        ExpandListGroup gru1 = new ExpandListGroup();
        gru1.setName("Basic Commands");
        for (int i = 0; i < basicCommands.length; i++)
        {
            ExpandListChild item = new ExpandListChild();
            item.setName(basicCommands[i]);
            item.setTag(null);
            expandListChildren.add(item);
        }
        gru1.setItems(expandListChildren);


        expandListChildren = new ArrayList<ExpandListChild>();
        ExpandListGroup gru2 = new ExpandListGroup();
        gru2.setName("SSH Commands");
        for (int i = 0; i < sshCommands.length; i++)
        {
            ExpandListChild item = new ExpandListChild();
            item.setName(sshCommands[i]);
            item.setTag(null);
            expandListChildren.add(item);
        }
        gru2.setItems(expandListChildren);

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
