package serio.tim.android.com.downloaderapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class DownloadService extends IntentService {
    private static final int NOTIFICATION_ID = 1234;
    public static final String DOWNLOAD_BROADCAST_ACTION = "DownloadService_Download_is_complete";

    public DownloadService() {
        super("DownloadService");
    }

    public DownloadService(String name) {
        super(name);
    }

//    @Override
//    public int onStartCommand(final Intent intent, int flags, int startId) {
//
//    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        if(intent.getAction().equals("download")) {
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
                    String url = intent.getStringExtra("url");
                    Log.d("DownloadService", "You want my service to download: " + url);
                    String contents = Downloader.downloadFake(url, 5000);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                            .setContentTitle("Download Complete")
                            .setContentText("I received the file: " + url)
                            .setSmallIcon(R.drawable.download);

                    Intent launch = new Intent(this, DownloaderActivity.class);
                    launch.putExtra("url", url);
                    PendingIntent pend = PendingIntent.getActivity(this, 0, launch, 0);
                    builder.setContentIntent(pend);

                    Notification notification = builder.build();
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(NOTIFICATION_ID, notification);

                    Intent done = new Intent();
                    done.setAction("downloadcomplete");
                    done.putExtra("url", url);
                    done.putExtra("data", contents);
                    sendBroadcast(done);
//                }
//            });
//            thread.start();
        }

        //return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
