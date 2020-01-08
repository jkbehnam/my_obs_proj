package com.game.behnam.myapplication.activities.mainManager.createEvent;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.utils.QuestionPackage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_PACKAGE_LIST;


public class CreateEventGatheringPresenter {
    ICreateEventGathering iCreateEvent;

    public CreateEventGatheringPresenter(ICreateEventGathering iCreateEvent) {
        this.iCreateEvent = iCreateEvent;
    }

    public void getPackageList(){
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

       // .setItems(events2, 0);

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
}
