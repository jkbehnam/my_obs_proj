package com.game.behnam.myapplication.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionPackage implements Serializable {
    @SerializedName("p_id")
    @Expose
    private
    String p_id;
    @SerializedName("p_desc")
    @Expose
    private
    String p_desc;
    @SerializedName("p_timestamp")
    @Expose
    private
    String p_timestamp;

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getP_timestamp() {
        return p_timestamp;
    }

    public void setP_timestamp(String p_timestamp) {
        this.p_timestamp = p_timestamp;
    }
}
