package com.example.expandablelistviewexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by sudheendra.sn on 10/31/13.
 */
public class CommandDetails extends Activity {

    private Integer GroupPos;
    private Integer ChildPos;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandlayout);

        Intent intent = getIntent();
        GroupPos = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_1, 0);
        ChildPos = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_2, 0);

        LoadXmlFile(GroupPos, ChildPos);
    }

    private void LoadXmlFile(int groupId, int childId)
    {
        if (MainActivity.xmlMap.containsKey(groupId))
        {
            String[] xmlFileList = MainActivity.xmlMap.get(groupId);
            for (int i = 0; i < xmlFileList.length; i++) {
                int id = getResources().getIdentifier(xmlFileList[0], "raw", "com.example.expandablelistviewexample");
                Log.i("CommandDetails", new Integer(id).toString());
            }
        }
    }
}