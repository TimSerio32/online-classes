package serio.tim.android.com.redditapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExtractXml {

    private static final String TAG = "ExtractXml";

    private String xml;
    private String tag;

    public ExtractXml(String xml, String tag) {
        this.xml = xml;
        this.tag = tag;
    }

    public List<String> start() {
        List<String> result = new ArrayList<>();
        String[] splitXml = xml.split(tag + "\"");
        int count = splitXml.length;
        for(int i = 1; i < count; i++) {
            String temp = splitXml[i];
            int index = temp.indexOf("\"");
//            Log.d(TAG, "start: index: " + index);
//            Log.d(TAG, "start: extracted: " + temp);

            temp = temp.substring(0, index);
//            Log.d(TAG, "start: snipped: " + temp);
            result.add(temp);
        }
        return result;
    }
}
