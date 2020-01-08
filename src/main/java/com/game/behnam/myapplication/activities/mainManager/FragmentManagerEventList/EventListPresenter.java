package com.game.behnam.myapplication.activities.mainManager.FragmentManagerEventList;

import androidx.fragment.app.FragmentTransaction;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.activities.mainManager.createEvent.FragmentCreateEventGame;
import com.game.behnam.myapplication.activities.mainManager.createEvent.FragmentCreateEventGathering;
import com.game.behnam.myapplication.activities.mainManager.gameDetails.FragmentGameDetails;
import com.game.behnam.myapplication.utils.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_EVENT_LIST;
import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_DELETE_EVENT;

public class EventListPresenter {
    FragmentEventListManager fragmentEventListManager;

    EventListPresenter(FragmentEventListManager fragmentEventListManager) {
        this.fragmentEventListManager = fragmentEventListManager;
    }

    public void getEventList(String admin_id) {
        fragmentEventListManager.showLoading_base();
        Map<String, String> param = new HashMap<String, String>();

        param.put("admin_id", admin_id);
        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                fragmentEventListManager.hideLoading_base();
                reciveRequeset(result);
            }
        }, param, URL_MANAGER_EVENT_LIST);

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

        fragmentEventListManager.setItems(events2, 0);

    }

    public void deleteEvent(String e_id,String admin_id) {
        fragmentEventListManager.showLoading_base();
        Map<String, String> param = new HashMap<String, String>();
        param.put("e_id", e_id);

        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                fragmentEventListManager.hideLoading_base();
                getEventList(admin_id);

            }
        }, param, URL_MANAGER_DELETE_EVENT);

    }

    public void editEvent(Event e) {
        Gson gson = new Gson();
        String json = gson.toJson(e);
        switch (e.getE_type()) {
            case "گردهمایی": {



                FragmentCreateEventGathering fragment = FragmentCreateEventGathering.newInstance("", json);
               // fragment.setItems(e);
                FragmentTransaction transaction = fragmentEventListManager.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
            case "مسابقه": {
                FragmentCreateEventGame fragment = FragmentCreateEventGame.newInstance("",json);
              //  fragment.setItems(e);
                FragmentTransaction transaction = fragmentEventListManager.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
        }

    }
    public void eventDetails(Event e) {
        Gson gson = new Gson();
        String json = gson.toJson(e);
        switch (e.getE_type()) {
            case "گردهمایی": {



                FragmentGameDetails fragment = FragmentGameDetails.newInstance("", json);
                // fragment.setItems(e);
                FragmentTransaction transaction = fragmentEventListManager.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
            case "مسابقه": {
                FragmentGameDetails fragment = FragmentGameDetails.newInstance("",json);
                //  fragment.setItems(e);
                FragmentTransaction transaction = fragmentEventListManager.getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            break;
        }

    }
}

