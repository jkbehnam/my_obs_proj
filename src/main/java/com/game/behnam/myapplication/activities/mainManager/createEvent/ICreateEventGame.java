package com.game.behnam.myapplication.activities.mainManager.createEvent;

import com.game.behnam.myapplication.utils.Observer;
import com.game.behnam.myapplication.utils.QuestionPackage;

import java.util.ArrayList;

public interface ICreateEventGame {

    public void showDialog_multiselect_obs(ArrayList<Observer> observers, String s);
    public void showDialog_multiselect(ArrayList<QuestionPackage> questionPackages, String s);
}
