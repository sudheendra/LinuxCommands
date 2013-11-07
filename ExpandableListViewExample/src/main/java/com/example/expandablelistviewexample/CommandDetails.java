package com.example.expandablelistviewexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 * Created by sudheendra.sn on 10/31/13.
 */
public class CommandDetails extends Activity {

    private Integer GroupPos;
    private Integer ChildPos;

    private String description;
    private String command;
    private Vector<String> examples;

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
        Log.i("CommandDetails", "LoadXmlFile");
        Log.i("CommandDetails", "GroupID: " + groupId);
        Log.i("CommandDetails", "ChildID: " + childId);

        if (MainActivity.xmlMap.containsKey(groupId))
        {
            String[] xmlFileList = MainActivity.xmlMap.get(groupId);
            int resourceId[] = new int[xmlFileList.length];
            for (int i = 0; i < xmlFileList.length; i++) {
                int id = getResources().getIdentifier(xmlFileList[i], "raw", "com.example.expandablelistviewexample");
                resourceId[i] = id;
                Log.i("CommandDetails", new Integer(id).toString());
            }

            int childResourceId = resourceId[childId];

            InputStream is = getResources().openRawResource(childResourceId);

            try
            {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
                xpp.setInput(is, null);

                ParseXml(xpp);

            }
            catch (XmlPullParserException xppEx)
            {

            }
            catch (IOException ioEx)
            {

            }
        }
    }

    private void ParseXml(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            if(eventType == XmlPullParser.START_DOCUMENT)
            {
                Log.i("CommandDetails", "Start document");
            }
            else if(eventType == XmlPullParser.END_DOCUMENT)
            {
                Log.i("CommandDetails", "End document");
            }
            else if(eventType == XmlPullParser.START_TAG)
            {
                Log.i("CommandDetails", "Start tag: "+parser.getName());

                String tag = parser.getName();
                if (tag.equals("Description"))
                {
                    description = parser.nextText();
                }

            }
            else if(eventType == XmlPullParser.END_TAG)
            {
                Log.i("CommandDetails", "End tag: "+parser.getName());
            }
            else if(eventType == XmlPullParser.TEXT)
            {
                Log.i("CommandDetials", "Text "+parser.getText());
            }
            try
            {
                eventType = parser.next();
            }
            catch (IOException ioEx)
            {

            }
        }
    }
}