package com.game.behnam.myapplication.activities.Observer;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class bcr_notfi extends BroadcastReceiver {

    int p;
    Context context;

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        context = arg0;



       // Toast.makeText(arg0, "Alarm received!", Toast.LENGTH_LONG).show();
        //Log.d("heeeey","d");
      //  Intent i = new Intent(context, SensoreService.class);
       // ContextCompat.startForegroundService(context, i);
   /*     Intent service = new Intent(arg0, SocketIOService.class);
        service.putExtra(SocketIOService.EXTRA_EVENT_TYPE, SocketIOService.EVENT_TYPE_JOIN);
        service.putExtra(SocketIOService.EXTRA_USER_NAME, "behnam");
       // service.setPackage(arg0.getPackageName());
        arg0.startService(service);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            context.startForegroundService(service);
        }
        else
        {
            context.startService(service);
        }
*/
   if(!isMyServiceRunning(SocketIOService.class)&&SocketIOService.isrepeat)
   startServiceByAlarm(arg0);

    }
    private void startServiceByAlarm(Context context)
    {
        Intent service = new Intent(context.getApplicationContext(), SocketIOService.class);
        service.putExtra(SocketIOService.EXTRA_EVENT_TYPE, SocketIOService.EVENT_TYPE_JOIN);
        service.putExtra(SocketIOService.EXTRA_USER_NAME, "behnam");
         service.setPackage(context.getPackageName());

       // context.startService(service);
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            ContextCompat.startForegroundService(context,service);

        }
        else
        {
            context.startService(service);
        }

       // Intent i = new Intent(context, ExampleJobIntentService.class);
       // ExampleJobIntentService.enqueueWork(context,i);



        // Get alarm manager.
      /*  AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Create intent to invoke the background service.
        Intent intent = new Intent(context, SocketIOService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long startTime = System.currentTimeMillis();
        long intervalTime = 60*1000;

        String message = "Start service use repeat alarm. ";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        //Log.d(TAG_BOOT_BROADCAST_RECEIVER, message);

        // Create repeat alarm.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);*/
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}


