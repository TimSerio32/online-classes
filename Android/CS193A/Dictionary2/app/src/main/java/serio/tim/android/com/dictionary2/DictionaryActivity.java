package serio.tim.android.com.dictionary2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class DictionaryActivity extends SimpleActivity {

    private HashMap<String, String> dictionary;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> fiveDefns;
    private String theWord;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        setTraceLifecycle(true);    // Stanford Library

        findListView(R.id.word_list).setOnItemClickListener(this);

        dictionary = new HashMap<String, String>();
        list = new ArrayList<String>();
        fiveDefns = new ArrayList<String>();

        readWordsFromFile();

        pickRandomWords();

//        mp = MediaPlayer.create(this, R.raw.rain);
    }

    // If you want persistence, use SharedPreferences (Lecture 5)

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state of theWord and fiveDefns
        // since they're the only instance vars that change state
        outState.putString("theWord", theWord);
        outState.putStringArrayList("fiveDefns", fiveDefns);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState.containsKey("theWord") && savedInstanceState.containsKey("fiveDefns")) {
            theWord = savedInstanceState.get("theWord").toString();
            // theWord = savedInstanceState.getString("theWord", "");
            fiveDefns = savedInstanceState.getStringArrayList("fiveDefns");

            Log.d("theWord", "theWord = " + theWord);
            Log.d("fiveDefns", "fiveDefns = " + fiveDefns);

            showWhatIPicked();
        }
    }

    //    @Override
//    protected void onPause() {
//        super.onPause();
//        mp.pause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mp.start();
//    }

    private void readWordsFromFile() {
        Scanner scan = new Scanner( getResources().openRawResource(R.raw.grewords) );

        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if(parts.length >= 2) {
                String word = parts[0];
                String defn = parts[1];

                list.add(word);
                dictionary.put(word, defn);
            }
        }
    }

    private void pickRandomWords() {

        ArrayList<String> fiveWords = new ArrayList<String>();
        Collections.shuffle(list);
        for (int i = 0; i < 5; i++) {
            fiveWords.add(list.get(i));
        }

        theWord = fiveWords.get(0);
        // TextView theWordView = (TextView) findViewById(R.id.the_word);
        // TextView theWordView = find(R.id.the_word);
        // theWordView.setText(theWord);

        // One line version of 2 lines above (Stanford Library):
        // findTextView(R.id.the_word).setText(theWord)

        fiveDefns.clear();
        for (String word : fiveWords) {
            fiveDefns.add(dictionary.get(word));
        }
        Collections.shuffle(fiveDefns);
        showWhatIPicked();
    }

    private void showWhatIPicked() {
        ListView listView = (ListView) findViewById(R.id.word_list);

        findTextView(R.id.the_word).setText(theWord);
//        if(adapter == null) {
//            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fiveDefns);
//            listView.setAdapter(adapter);
//        } else {
//            adapter.notifyDataSetChanged();
//        }

        // Simpler version of the above lines (adapter) of code (Stanford Library):
        SimpleList.with(this).setItems(listView, fiveDefns);

//        The following code got moved to the line below and onItemClick outside of pickRandomWords() (Stanford Library)

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                String defnClicked = fiveDefns.get(i);
//                String rightAnswer = dictionary.get(theWord);
//                if(defnClicked.equals(rightAnswer)) {
//                    Toast.makeText(DictionaryActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();
//
//                    // Simpler version of line above (Stanford Library):
//                    // toast("You are awesome!");
//                } else {
//                    Toast.makeText(DictionaryActivity.this, "YOU SUCK!", Toast.LENGTH_SHORT).show();
//
//                    // Simpler version of line above (Stanford Library):
//                    // toast("YOU SUCK!", Toast.LENGTH_SHORT);
//                }
//                pickRandomWords();
//            }
//        });

//        moved to onCreate

//        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(ListView list, int index) {
        String defnClicked = fiveDefns.get(index);
        String rightAnswer = dictionary.get(theWord);
        if(defnClicked.equals(rightAnswer)) {
            Toast.makeText(DictionaryActivity.this, "You are awesome!", Toast.LENGTH_SHORT).show();

            // Simpler version of line above (Stanford Library):
            // toast("You are awesome!");
        } else {
            Toast.makeText(DictionaryActivity.this, "YOU SUCK!", Toast.LENGTH_SHORT).show();

            // Simpler version of line above (Stanford Library):
            // toast("YOU SUCK!", Toast.LENGTH_SHORT);
        }
        pickRandomWords();
    }
}
