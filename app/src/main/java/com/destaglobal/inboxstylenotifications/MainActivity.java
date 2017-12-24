package com.destaglobal.inboxstylenotifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private NotificationManager mNotificationManager;
    private int notificationID = 100;
    NotificationCompat.Builder mBuilder;
    Bitmap remote_picture = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                displayNotification();
            }
        });
        Button cancelBtn = (Button) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cancelNotification();
            }
        });

    }

    protected void displayNotification() {
        Log.i("Start", "notification");
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),
                R.drawable.launcher);
        Intent resultIntent = new Intent(MainActivity.this, NotificationView.class);

        mBuilder = new NotificationCompat.Builder(
                this)
                .setAutoCancel(true)
                .setContentTitle("Event Details")
                .setContentText("Hello...!")
                .setContentIntent(
                        PendingIntent.getActivity(this, 0,
                                resultIntent,
                                PendingIntent.FLAG_ONE_SHOT))
                .setSmallIcon(R.drawable.launcher).setLargeIcon(icon1);

        String[] events = new String[6];

        events[0] = "How are you?";
        events[1] = "Hiii!";
        events[2] = "I am fine..";
        events[3] = "What about you? All is well?";
        events[4] = "Yes Everything Is alright";

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.setBigContentTitle("New Messages");

        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
        mBuilder.setStyle(inboxStyle);

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(notificationID, mBuilder.build());
    }

    protected void cancelNotification() {
        Log.i("Cancel", "notification");
        mNotificationManager.cancel(notificationID);
    }

}
