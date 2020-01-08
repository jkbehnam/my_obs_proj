package com.game.behnam.myapplication.activities.mainManager.createEvent;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.utils.Observer;
import com.game.behnam.myapplication.utils.QuestionPackage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.activities.Main.user_id;
import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_OBS_lIST;
import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_PACKAGE_LIST;


public class CreateEventGamePresenter {
    ICreateEventGame iCreateEvent;

    public CreateEventGamePresenter(ICreateEventGame iCreateEvent) {
        this.iCreateEvent = iCreateEvent;
    }

    public static String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9")
                .replace("-", "-");
    }


    public void getPackageList() {
        Map<String, String> param = new HashMap<String, String>();


        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                recivePackageRequeset(result);
            }
        }, param, URL_MANAGER_PACKAGE_LIST);
    }

    public void recivePackageRequeset(String response) throws JSONException {

        final GsonBuilder builder = new GsonBuilder();

        final Gson gson = builder.create();
        // final Reader data = new InputStreamReader(LoginActivity.class.getResourceAsStream("user"), "UTF-8");
        JSONObject obj = new JSONObject(response);
        ArrayList<QuestionPackage> events2 = new ArrayList<>();
        try {
            final QuestionPackage[] events = gson.fromJson(obj.getString("package"), QuestionPackage[].class);

            events2.addAll(Arrays.asList(events));
        } catch (Exception e) {

        }


        iCreateEvent.showDialog_multiselect( events2, "بسته سوالات");
    }

    public void getObserverList() {
        Map<String, String> param = new HashMap<String, String>();

        param.put("admin_id", user_id);
        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                reciveObserverRequeset(result);
            }
        }, param, URL_MANAGER_OBS_lIST);
    }

    public void reciveObserverRequeset(String response) throws JSONException {

        final GsonBuilder builder = new GsonBuilder();

        final Gson gson = builder.create();
        // final Reader data = new InputStreamReader(LoginActivity.class.getResourceAsStream("user"), "UTF-8");
        JSONObject obj = new JSONObject(response);
        ArrayList<Observer> events2 = new ArrayList<>();
        try {
            final Observer[] events = gson.fromJson(obj.getString("event"), Observer[].class);

            events2.addAll(Arrays.asList(events));
        } catch (Exception e) {

        }
        iCreateEvent.showDialog_multiselect_obs(events2, "داوران");
    }

}
