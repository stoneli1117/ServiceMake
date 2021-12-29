package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class samservice extends Service { ;
    private static final String CHANNEL_ID = "是在哭阿";
    private Handler handler1=new Handler();
    private Runnable showTime=new Runnable() {
        @Override
        public void run() {
            Log.i("mylog",new Date().toString()); //Log出目前時間
            handler1.postDelayed(this,1000);   //使此Runnable每1秒重Run一次
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

        super.onCreate();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID )
                .setContentTitle("注意")
                .setContentText("你好歡迎光臨")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int   startId) {
        Log.i("samLog","Enter onStartCommand()");
        handler1.post(showTime);     //開啟此Service時，執行showTime
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return Service.START_STICKY; //一個返回值，如果此Service被異常終止時，將嘗試重新開啟此Service
    }

    @Override
    public void onDestroy() {                    //除非調用stopService()，否則不會進入此區塊
        Log.i("samLog","Enter onDestroy()");
        super.onDestroy();
        handler1.removeCallbacks(showTime);     //利用removeCallbacks()停止handler.post(showTime)
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.i("samLog","Leave onDestroy()");
    }
}



