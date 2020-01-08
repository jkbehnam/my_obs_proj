package com.game.behnam.myapplication.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.game.behnam.myapplication.ForegroundService;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.activities.Observer.eventList.FragmentEventList;
import com.game.behnam.myapplication.activities.mainManager.FragmentManagerEventList.FragmentEventListManager;
import com.game.behnam.myapplication.base.BaseActivity;
import com.game.behnam.myapplication.login.LoginActivity;
import com.game.behnam.myapplication.utils.PrefManager;

public class Main extends BaseActivity {
    public static String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.background)); //status bar or the time bar at the top

        }

        //startService();




        PrefManager pm = new PrefManager(this);

        String session = pm.getUserDetails().get("session");
        if (pm.getUserDetails().get("name") .equals("")) {

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            this.finish();
        } else {
            user_id = pm.getUserDetails().get("u_id");
        }


        if (!session.equals("manager")) {

            Fragment fragment = FragmentEventList.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        } else {

            Fragment fragment = FragmentEventListManager.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }


    }

    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");

        ContextCompat.startForegroundService(this, serviceIntent);
    }

}
