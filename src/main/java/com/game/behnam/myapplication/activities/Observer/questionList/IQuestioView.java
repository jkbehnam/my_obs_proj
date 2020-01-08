package com.game.behnam.myapplication.activities.Observer.questionList;

import com.game.behnam.myapplication.utils.Question;

import java.util.ArrayList;

public interface IQuestioView {
     void setItems(ArrayList<Question> questionsList);

     void removeitem(int position);

     void showLoading();

     void hideLoading();

     void dimissSellAlert();
}
