package serio.tim.android.com.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {

    // Implicit Broadcast: ExampleBroadcastReceiver isn't called directly.
    // Explicit Broadcast: ExampleBroadcastReceiver is called directly.
    // Static Broadcast: Receiver is registered in manifest file so that apps can
    //                   always listen for events even when not in use. Strict
    //                   restrictions on devices running API 24 and higher.
    // Dynamic Broadcast: Receiver is registered in the context of our application.
    //                    This means that our app will only listen for events
    //                    for as long as our app is running (App Context), or as long as our app
    //                    is in the foreground (Activity Context).

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "Boot Completed", Toast.LENGTH_SHORT).show();
        }

        /*
            Devices with API 24 and higher won't be able to receive this because
            receiver is statically registered in manifest file. This was deprecated to save
            resources (Several apps could be always listening for this even if they aren't in use).
        */
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "Connectivity Changed", Toast.LENGTH_SHORT).show();
        }
    }
}
