package com.example.martinservice2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onCreate");
        super.onCreate();

        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(("message"))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("title")
                .setContentText("text")
                .setContentIntent(pi)
                .setAutoCancel(false)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onDestroy");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
        super.onDestroy();
    }

}