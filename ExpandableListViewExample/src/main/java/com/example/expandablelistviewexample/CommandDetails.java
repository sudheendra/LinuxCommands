package com.example.expandablelistviewexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.EditText;
import android.widget.TextView;

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
    private String example = "";
    private Vector<String> exampleCommands = new Vector<String>();
    private Vector<String> exampleDetails = new Vector<String>();

    TextView commandDescription;
    TextView commandExample;
    TextView commandTitle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandlayout);

        commandDescription = (TextView) findViewById(R.id.CommandDescription);
        commandExample = (TextView) findViewById(R.id.Examples);
        commandTitle = (TextView) findViewById(R.id.CommandTitle);

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
        Integer j =  new Integer(0);
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            Log.i("CommandDetails", "eventType: " + eventType);
            Log.i("CommandDetails","While Loop: "+ j.toString());
            j++;
            if(eventType == XmlPullParser.START_DOCUMENT)
            {
            }
            else if(eventType == XmlPullParser.END_DOCUMENT)
            {
            }
            if(eventType == XmlPullParser.START_TAG)
            {
                String tag = parser.getName();
                Log.i("CommandDetails:", parser.getName());
                if (tag.equals("Description"))
                {
                    description = parser.nextText();
                    Log.i("CommandDetails", "Description: " + description);
                }

                else if (tag.equals("Example"))
                {
                }
                else if(tag.equals("ExampleCommand"))
                {
                    exampleCommands.add(parser.nextText());
                }
                else if (tag.equals("ExampleDetails"))
                {
                    exampleDetails.add(parser.nextText());
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
                break;
            }
        }

        commandTitle.setText(command);
        commandDescription.setText(description);
        for (int i = 0; i < exampleCommands.size(); i++) {

            example += exampleCommands.get(i) + " :    " + exampleDetails.get(i) + "\n";
        }
        commandExample.setText(example);
    }
}