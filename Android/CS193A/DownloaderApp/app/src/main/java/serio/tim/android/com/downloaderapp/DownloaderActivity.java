package serio.tim.android.com.downloaderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import stanford.androidlib.SimpleActivity;

public class DownloaderActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("url")) {
            String url = intent.getStringExtra("url");
            Toast.makeText(this, "I'm back! Thanks for downloading " + url, Toast.LENGTH_SHORT).show();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction("downloadcomplete");
        registerReceiver(new MyReceiver(), filter);
    }

    public void getButtonClick(View view) {
        EditText edit = (EditText) findViewById(R.id.the_url);
        String webPageURL = edit.getText().toString();

        EditText delayEditText = (EditText) findViewById(R.id.delay);
        int delayMS = Integer.valueOf(delayEditText.getText().toString());

        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra("url", webPageURL);
        intent.setAction("download");
        startService(intent);

        IntentFilter filter = new IntentFilter();
        filter.addAction("downloadcomplete");
        registerReceiver(new MyReceiver(), filter);

        log("Done downloading " + webPageURL);
        Toast.makeText(this, "Starting download ...", Toast.LENGTH_SHORT).show();
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String url = intent.getStringExtra("url");
            Toast.makeText(context, "Done downloading " + url, Toast.LENGTH_SHORT).show();
            log("Done downloading " + url);
        }
    }

}
