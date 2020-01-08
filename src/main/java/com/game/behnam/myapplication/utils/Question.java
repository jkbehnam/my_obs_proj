package com.game.behnam.myapplication.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {


    @SerializedName("desc_id")
    @Expose
    private String desc_id;
    @SerializedName("get_question_id")
    @Expose
    private String get_question_id;
    @SerializedName("q_id")
    @Expose
    private String q_id;

    @SerializedName("q_title")
    @Expose
    private String q_title;
    @SerializedName("q_type")
    @Expose
    private String q_type;
    @SerializedName("q_level")
    @Expose
    private String q_level;
    @SerializedName("q_score")
    @Expose
    private
    String q_score;
    @SerializedName("q_img")
    @Expose
    private String Q_img;
    @SerializedName("q_package")
    @Expose
    private String Q_package;
    @SerializedName("g_answer")
    @Expose
    private String g_answer;
    @SerializedName("o_answer")
    @Expose
    private String o_answer;
    @SerializedName("o_opinion")
    @Expose
    private String o_opinion;
    @SerializedName("send_timestamp")
    @Expose
    private String o_send_timestamp;
    @SerializedName("get_timestamp")
    @Expose
    private String o_get_timestamp;


    public String getGet_question_id() {
        return get_question_id;
    }

    public void setGet_question_id(String get_question_id) {
        this.get_question_id = get_question_id;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getQ_title() {
        return q_title;
    }

    public void setQ_title(String q_title) {
        this.q_title = q_title;
    }

    public String getQ_type() {
        return q_type;
    }

    public void setQ_type(String q_type) {
        this.q_type = q_type;
    }

    public String getQ_level() {
        return q_level;
    }

    public void setQ_level(String q_level) {
        this.q_level = q_level;
    }

    public String getQ_score() {
        return q_score;
    }

    public void setQ_score(String q_score) {
        this.q_score = q_score;
    }

    public String getQ_img() {
        return Q_img;
    }

    public void setQ_img(String q_img) {
        Q_img = q_img;
    }

    public String getQ_package() {
        return Q_package;
    }

    public void setQ_package(String q_package) {
        Q_package = q_package;
    }


    public String getO_answer() {
        return o_answer;
    }

    public void setO_answer(String o_answer) {
        this.o_answer = o_answer;
    }

    public String getO_opinion() {
        return o_opinion;
    }

    public void setO_opinion(String o_opinion) {
        this.o_opinion = o_opinion;
    }

    public String getO_send_timestamp() {
        return o_send_timestamp;
    }

    public void setO_send_timestamp(String o_send_timestamp) {
        this.o_send_timestamp = o_send_timestamp;
    }

    public String getO_get_timestamp() {
        return o_get_timestamp;
    }

    public void setO_get_timestamp(String o_get_timestamp) {
        this.o_get_timestamp = o_get_timestamp;
    }

    public String getG_answer() {
        return g_answer;
    }

    public void setG_answer(String g_answer) {
        this.g_answer = g_answer;
    }

    public String getDesc_id() {
        return desc_id;
    }

    public void setDesc_id(String desc_id) {
        this.desc_id = desc_id;
    }
}
