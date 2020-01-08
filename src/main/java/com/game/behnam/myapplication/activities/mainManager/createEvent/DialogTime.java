package com.game.behnam.myapplication.activities.mainManager.createEvent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.game.behnam.myapplication.R;
import com.sasanebrahimi.persiandatepicker.PersianDatePicker;
import com.shawnlin.numberpicker.NumberPicker;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;


public class DialogTime {
    Context context;
    AlertDialog.Builder builder;
    View view_alert_dialog_exit;
    AlertDialog ald_exit;
    Button btn_ok;
    PersianDatePicker pdp;
    NumberPicker number_picker_hour,number_picker_min;
    @SuppressLint("ResourceAsColor")
    public AlertDialog qrcode_reader(Context context, View view1) {
        this.context = context;
        // ald_insert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder = new AlertDialog.Builder(context);
        ald_exit = builder.create();
        view_alert_dialog_exit = LayoutInflater.from(context).inflate(R.layout.dialog_time, null);
        ald_exit.setView(view_alert_dialog_exit);
        ald_exit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ald_exit.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn_ok = view_alert_dialog_exit.findViewById(R.id.alert_btn_ok);
        pdp = view_alert_dialog_exit.findViewById(R.id.pdp);

        number_picker_hour=view_alert_dialog_exit.findViewById(R.id.number_picker_hour);
        number_picker_min=view_alert_dialog_exit.findViewById(R.id.number_picker_min);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianDate persianDate = new PersianDate();
                persianDate.setShYear(pdp.getYear());
                persianDate.setShMonth(pdp.getMonth());
                persianDate.setShDay(pdp.getDay());
                persianDate.setHour(number_picker_hour.getValue());
                persianDate.setMinute(number_picker_min.getValue());
                PersianDateFormat pdformater = new PersianDateFormat("j F H:i");
                ((EditText) view1).setText(pdformater.format(persianDate));
                ((EditText) view1).setTag(String.valueOf(persianDate.getTime()/1000));
                ald_exit.hide();
            }
        });
        return ald_exit;
    }

    public void show() {
        ald_exit.show();


    }


}

