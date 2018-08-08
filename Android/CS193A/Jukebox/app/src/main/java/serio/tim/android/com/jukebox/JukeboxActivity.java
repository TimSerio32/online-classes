package serio.tim.android.com.jukebox;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import stanford.androidlib.SimpleActivity;

public class JukeboxActivity extends SimpleActivity {

    private static final int SPEECH_TO_TEXT_REQ_CODE = 1234;

    private MediaPlayer player;
    private TextToSpeech tts;
    private Boolean ttsIsReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jukebox);

        // initialize text to speech here so that you can reuse and its not as expensive
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                ttsIsReady = true;
            }
        });

        ListView list = (ListView) findViewById(R.id.song_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] songTitles = getResources().getStringArray(R.array.song_titles);
                String song = songTitles[i].toLowerCase();
                playSong(song);
            }
        });
    }

    public void playSong(String song) {
        int id = getResources().getIdentifier(song, "raw", getPackageName());

        stopSong();

        player = MediaPlayer.create(JukeboxActivity.this, id);
        player.start();
    }

    public void stopSong() {
        if(player != null) {
            player.stop();
            player = null;
        }
    }

    public void onClickPlay(View view) {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a sentence here");
//        startActivityForResult(intent, SPEECH_TO_TEXT_REQ_CODE);


        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a sentence here");
            startActivityForResult(intent, SPEECH_TO_TEXT_REQ_CODE);
        } catch(ActivityNotFoundException e) {
            String appPackageName = "com.google.android.googlequicksearchbox";
            Intent your_browser_intent = new Intent(Intent.ACTION_VIEW,

                    Uri.parse("https://market.android.com/details?id=" + appPackageName));
            startActivity(your_browser_intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == SPEECH_TO_TEXT_REQ_CODE) {
            ArrayList<String> list = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(list == null || list.isEmpty()) {
                return;
            }
            String text = list.get(0);
        }
    }

    public void onClickStop(View view) {
        stopSong();

        if(ttsIsReady) {
            tts.speak("Hello world", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
