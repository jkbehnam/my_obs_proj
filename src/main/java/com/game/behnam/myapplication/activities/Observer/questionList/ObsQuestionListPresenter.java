package com.game.behnam.myapplication.activities.Observer.questionList;


import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.utils.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_OBS_QUESTION_LIST;

public class ObsQuestionListPresenter {
   public IQuestioView iQuestioView;
    ObsQuestionListPresenter(IQuestioView iQuestioView){
        this.iQuestioView=iQuestioView;
    }

    public void removeItem(int i) {
        iQuestioView.removeitem(i);
    }

    public void getEventList(String o_id,String e_id,int page) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("o_id", o_id);
        param.put("e_id", e_id);
        param.put("g_page", String .valueOf(page));

        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                reciveRequeset(result);
            }
        }, param, URL_OBS_QUESTION_LIST);

    }

    public void reciveRequeset(String response) throws JSONException {
      //  fragment_question_list.hideLoading();
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        // final Reader data = new InputStreamReader(LoginActivity.class.getResourceAsStream("user"), "UTF-8");
        JSONObject obj = new JSONObject(response);
        ArrayList<Question> questions2 = new ArrayList<>();
        final Question[] questions = gson.fromJson(obj.getString("questions"), Question[].class);
        questions2.addAll(Arrays.asList(questions));
        iQuestioView.setItems(questions2);
    }

}
