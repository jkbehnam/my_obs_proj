package com.game.behnam.myapplication.activities.mainManager.FragmentManagerObsList;

import androidx.fragment.app.FragmentTransaction;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.activities.mainManager.createEvent.FragmentCreateObs;
import com.game.behnam.myapplication.utils.Observer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_DELETE_OBSERVER;
import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_OBS_lIST;

public class ObsListPresenter {
    FragmentObsListManager fragmentObsListManager;

    ObsListPresenter(FragmentObsListManager fragmentObstListManager) {
        this.fragmentObsListManager = fragmentObstListManager;
    }

    public void getEventList(String admin_id) {
fragmentObsListManager.showLoading_base();
        Map<String, String> param = new HashMap<String, String>();

        param.put("admin_id", admin_id);
        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                fragmentObsListManager.hideLoading_base();
                reciveRequeset(result);
            }
        }, param, URL_MANAGER_OBS_lIST);

    }

    public void reciveRequeset(String response) throws JSONException {

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

        fragmentObsListManager.setItems(events2, 0);

    }

    public void deleteObs(String e_id,String admin_id) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("o_id", e_id);

        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                getEventList( admin_id);
                fragmentObsListManager.hideLoading_base();
            }
        }, param, URL_MANAGER_DELETE_OBSERVER);
        fragmentObsListManager.showLoading_base();
    }

    public void editObs(Observer e) {
        Gson gson = new Gson();
        String json = gson.toJson(e);
        FragmentCreateObs fragment = FragmentCreateObs.newInstance("", json);
        // fragment.setItems(e);
        FragmentTransaction transaction = fragmentObsListManager.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}

