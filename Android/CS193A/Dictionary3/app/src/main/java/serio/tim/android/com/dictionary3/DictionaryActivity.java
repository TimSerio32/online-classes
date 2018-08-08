package serio.tim.android.com.dictionary3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private int points;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        setTraceLifecycle(true);    // Stanford Library

        points = 0;

        findListView(R.id.word_list).setOnItemClickListener(this);

        dictionary = new HashMap<String, String>();
        list = new ArrayList<String>();
        fiveDefns = new ArrayList<String>();

        readWordsFromFile();

        pickRandomWords();

        SharedPreferences prefs = getSharedPreferences("myprefs", MODE_PRIVATE);
        highScore = prefs.getInt("highScore", 0);

        mp = MediaPlayer.create(this, R.raw.rain);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mp.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state of theWord and fiveDefns
        // since they're the only instance vars that change state
        outState.putInt("points", points);
        outState.putString("theWord", theWord);
        outState.putStringArrayList("fiveDefns", fiveDefns);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getInt("points", 0);

        if (savedInstanceState.containsKey("theWord") && savedInstanceState.containsKey("fiveDefns")) {
            theWord = savedInstanceState.get("theWord").toString();
            // theWord = savedInstanceState.getString("theWord", "");
            fiveDefns = savedInstanceState.getStringArrayList("fiveDefns");

            Log.d("theWord", "theWord = " + theWord);
            Log.d("fiveDefns", "fiveDefns = " + fiveDefns);

            showWhatIPicked();
        }
    }

    private void readWordsFromFile() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.grewords));
        readFileHelper(scan);

        try {
            Scanner scan2 = new Scanner(openFileInput("added_words.txt"));
            readFileHelper(scan2);
        } catch(Exception e) {

        }
    }

    private void readFileHelper(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length >= 2) {
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
        SimpleList.with(this).setItems(listView, fiveDefns);
    }

    @Override
    public void onItemClick(ListView list, int index) {
        String defnClicked = fiveDefns.get(index);
        String rightAnswer = dictionary.get(theWord);
        if (defnClicked.equals(rightAnswer)) {
            points++;
            if(points > highScore) {
                highScore = points;
                SharedPreferences shared = getSharedPreferences("myprefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("highScore", highScore);
                editor.commit();
            }
            Toast.makeText(DictionaryActivity.this, "You are awesome! Score = " + points + ", hi = " + highScore, Toast.LENGTH_SHORT).show();
        } else {
            points--;
            Toast.makeText(DictionaryActivity.this, "YOU SUCK! Score = " + points + ", hi = " + highScore, Toast.LENGTH_SHORT).show();
        }
        pickRandomWords();
    }

    public void addAWordClick(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
    }
}
