package com.game.behnam.myapplication.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Observer  implements Serializable {
    @SerializedName("o_id")
    @Expose
    private
    String o_id;
    @SerializedName("username")
    @Expose
    private
    String username;
    @SerializedName("password")
    @Expose
    private
    String password;
    @SerializedName("phone_num")
    @Expose
    private
    String phone_num;
    @SerializedName("gender")
    @Expose
    private
    String gender;
    @SerializedName("b_date")
    @Expose
    private
    String b_date;
    @SerializedName("name")
    @Expose
    private
    String name;
    @SerializedName("specialty")
    @Expose
    private
    String specialty;

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getB_date() {
        return b_date;
    }

    public void setB_date(String b_date) {
        this.b_date = b_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
