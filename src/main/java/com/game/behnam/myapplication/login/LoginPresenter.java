package com.game.behnam.myapplication.login;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.utils.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_LOGIN;
import static com.game.behnam.myapplication.utils.URLs.URL_OBSERVER_LOGIN;

public class LoginPresenter {
    LoginActivity loginActivity;

    String url;
    String session;
    LoginPresenter(LoginActivity loginActivity){
        this.loginActivity=loginActivity;

    }
    public void login(String username,String password,int i){
        if(i==0){url=URL_MANAGER_LOGIN;
            session="manager";}else if(i==1){url=URL_OBSERVER_LOGIN;session="observer";}

        Map<String, String> param = new HashMap<String, String>();

        param.put("Username", username);
        param.put("Password", password);

        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                reciveRequeset(result);
            }
        }, param, url);
    }

    public void reciveRequeset(String result) throws JSONException {
        JSONObject obj = new JSONObject(result);
        if (obj.getString("message").equals("Login successfull")) {

            obj=new JSONObject(obj.getString("user"));
            PrefManager pm = new PrefManager(loginActivity.getBaseContext());
            pm.createLogin(obj.getString("id"),obj.getString("username"),obj.getString("name"),session);

            loginActivity.loginSuccessful();
        }


    }
}
