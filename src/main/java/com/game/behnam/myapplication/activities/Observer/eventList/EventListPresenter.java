package com.game.behnam.myapplication.activities.Observer.eventList;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.utils.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_OBS_EVENT_LIST;

public class EventListPresenter {
    FragmentEventList fragmentEventList;
    EventListPresenter(FragmentEventList fragmentEventList){
        this.fragmentEventList=fragmentEventList;
    }
    void getEventList(String O_id){
        Map<String, String> param = new HashMap<String, String>();
        param.put("o_id", O_id);

        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                reciveRequeset(result);
            }
        }, param, URL_OBS_EVENT_LIST);
    }

    public void reciveRequeset(String response) throws JSONException {

        final GsonBuilder builder = new GsonBuilder();

        final Gson gson = builder.create();
        // final Reader data = new InputStreamReader(LoginActivity.class.getResourceAsStream("user"), "UTF-8");
        JSONObject obj = new JSONObject(response);
        ArrayList<Event> events2 = new ArrayList<>();
        try {
            final Event[] events = gson.fromJson(obj.getString("event"), Event[].class);

            events2.addAll(Arrays.asList(events));
        } catch (Exception e) {

        }

        fragmentEventList.setItems(events2, 0);

    }
}
