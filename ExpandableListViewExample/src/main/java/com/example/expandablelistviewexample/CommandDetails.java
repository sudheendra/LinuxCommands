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
    private Vector<String> exampleCommands = new Vector<String>();
    private Vector<String> exampleDetails = new Vector<String>();

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
            Log.i("CommandDetails","While Loop -4");
            if(eventType == XmlPullParser.START_DOCUMENT)
            {
            }
            else if(eventType == XmlPullParser.END_DOCUMENT)
            {
            }
            if(eventType == XmlPullParser.START_TAG)
            {
                String tag = parser.getName();
                if (tag.equals("Description"))
                {
                    description = parser.nextText();
                    Log.i("CommandDetails", "Description: " + description);
                }

                else if (tag.equals("Examples"))
                {
                    Log.i("CommandDetails", "I am here -3");
                    int count = parser.getAttributeCount();
                    Log.i("CommandDetails", "Attr Count: " + count);
                    Log.i("CommandDetails", "command syntax: " + parser.getAttributeValue(0));
                    Log.i("CommandDetails", "command Details: " + parser.getAttributeValue(1));

                    if (count > 0)
                        exampleCommands.add(parser.getAttributeValue(0));

                    if (count > 1)
                        exampleDetails.add(parser.getAttributeValue(1));
                }
                else
                {
                    command = tag;
                }
            }
            else if(eventType == XmlPullParser.END_TAG)
            {
            }
            else if(eventType == XmlPullParser.TEXT)
            {
            }
            try
            {
                eventType = parser.next();
            }
            catch (IOException ioEx)
            {
                Log.i("CommandDetails", "I am here -2");
            }
        }

        Log.i("CommandDetails", "I am here -1");
        Log.i("CommandDetails", "exampleCommands: " + exampleCommands.size());
        Log.i("CommandDetails", "exampleCommands: " + exampleDetails.size());
        for (int i = 0; i < exampleCommands.size(); i++) {
            Log.i("CommandDetails", "Command: " + exampleCommands.get(i));
        }

        for (int i = 0; i < exampleDetails.size(); i++) {
            Log.i("CommandDetails", "Details: " + exampleDetails.get(i));
        }
    }
}