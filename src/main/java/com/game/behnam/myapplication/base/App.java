package com.game.behnam.myapplication.base;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

public class App extends Application {

    private final String TAG = "App";

    // for the sake of simplicity. use DI in real apps instead
    public static LocaleManager localeManager;

    @Override
    protected void attachBaseContext(Context base) {
        localeManager = new LocaleManager(base);
        super.attachBaseContext(localeManager.setLocale(base));
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
        Log.d(TAG, "onConfigurationChanged: " + newConfig.locale.getLanguage());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = manager.getNotificationChannel("12345");
            if (channel == null) {
                channel = new NotificationChannel("1234",
                        "examp",
                        NotificationManager.IMPORTANCE_LOW);
                manager.createNotificationChannel(channel);
            }
        }
    }
}