package serio.tim.android.com.broadcastsenderexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
    }

    private void sendBroadcast(View v) {
        Intent intent = new Intent("serio.tim.android.com.EXAMPLE_ACTION");
        intent.putExtra("serio.tim.android.com.EXTRA_TEXT", "Broadcast Received");
        sendBroadcast(intent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String receivedText = intent.getStringExtra("serio.tim.android.com.EXTRA_TEXT");
            textView.setText(receivedText);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("serio.tim.android.com.EXAMPLE_ACTION");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
