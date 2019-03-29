package com.mytrendin.facetracking;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

@SuppressLint("Registered")
public class MyService extends Service {

    private String CHANNEL_ID = "Analytics";
    private int NOTIFY_ID = 198821;
    private NotificationManager mNotificationManager;

    @android.support.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Sevice Created!",Toast.LENGTH_LONG).show();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Notifikasi(this,"Face Recognition","Camera Active");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
//        Notifikasi(this,"Face Recognition","Camera Stopped");
    }

    private void Notification(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Service Face Recognition",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private void Notifikasi(Context context, String title, String message){
        Intent notificationIntent = new Intent(context, FaceTrackerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,NOTIFY_ID,notificationIntent,0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mNotificationManager.notify(NOTIFY_ID,mBuilder.build());
    }

    private void lookupServer(){
        AsyncHttpClient client = new AsyncHttpClient(true,80,443);
        String BaseServer = EndPoint.server;
        try {
            client.post(BaseServer, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        } catch (Exception ex){

        }
    }
}
