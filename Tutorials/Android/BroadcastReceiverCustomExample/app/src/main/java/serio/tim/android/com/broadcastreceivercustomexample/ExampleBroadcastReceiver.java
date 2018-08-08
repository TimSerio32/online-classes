package serio.tim.android.com.broadcastreceivercustomexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*
            Namespace for broadcast actions is global: your action string
            has to be unique, otherwise it will conflict with other apps.
            Use your package name to generate a unique action string.
         */

        if("serio.tim.android.com.EXAMPLE_ACTION".equals(intent.getAction())) {
            String receivedText = intent.getStringExtra("serio.tim.android.com.EXTRA_TEXT");
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show();
        }
    }
}
