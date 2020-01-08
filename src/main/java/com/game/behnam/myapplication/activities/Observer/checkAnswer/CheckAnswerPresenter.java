package com.game.behnam.myapplication.activities.Observer.checkAnswer;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.VolleyCallback;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import static com.game.behnam.myapplication.utils.URLs.URL_OBS_ANSWER;

public class CheckAnswerPresenter {
    DialogCheckAnswer dialogCheckAnswer;
    CheckAnswerPresenter(DialogCheckAnswer dialogCheckAnswer){
        this.dialogCheckAnswer=dialogCheckAnswer;
    }
    void answerToRequest(String question_id,String Desc_id,String opinion,String obs_answer){
        Map<String, String> param = new HashMap<String, String>();
        param.put("id", question_id);
        param.put("desc_id", Desc_id);
        param.put("o_opinion", opinion);
        param.put("o_answer", obs_answer);


        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                reciveRequeset(result);
                dialogCheckAnswer.showMessage("پاسخ ارسال شد");
            }
        }, param, URL_OBS_ANSWER);
    }

    public void reciveRequeset(String result){

        if(result.equals("send")){
            dialogCheckAnswer.dismiss();
            dialogCheckAnswer.removeItem();

        }
    }
}
