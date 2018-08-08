package serio.tim.android.com.broadcastreceiverdynamicexample;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
        By registering in onStart() and unregistering in onStop(),
        this BroadcastReceiver is only listening for events while the
        app is in the foreground. To keep listening for events even when app goes into background,
        register in onCreate() and unregister in onDestroy(). To listen for events as long as the
        application is running register it in onCreate() and unregister it in onDestroy()
        of the Application class.
     */

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
    }
}
