package com.apolis.lanny.notificationproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SecActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 111, i, PendingIntent.FLAG_UPDATE_CURRENT);

//                Builder builder = new Builder(MainActivity.this, "456");
//                builder.setContentTitle("TITLE!!!!");
//                builder.setContentText("My notification text");
//                builder.setSmallIcon(R.drawable.ic_launcher_background);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "456")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("NO TITLE!!!")
                        .setContentText("L O O K  H E R E ! ! ! ")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                builder.setContentIntent(pi);
                builder.setAutoCancel(true);

                
                builder.addAction(R.drawable.ic_launcher_foreground, "CLICK ME", pi);
                builder.addAction(R.drawable.done1, "GET ME", pi);

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (VERSION.SDK_INT >= VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("456", "myNotification",NotificationManager.IMPORTANCE_LOW);
                    manager.createNotificationChannel(notificationChannel);
                }
                manager.notify(123, builder.build());
            }
        });
    }
}
