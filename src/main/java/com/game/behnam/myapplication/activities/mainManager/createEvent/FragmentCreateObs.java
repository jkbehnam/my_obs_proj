package com.game.behnam.myapplication.activities.mainManager.createEvent;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.base.BaseFragment;
import com.game.behnam.myapplication.utils.Observer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shuhart.stepview.StepView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

import static com.game.behnam.myapplication.activities.Main.user_id;
import static com.game.behnam.myapplication.utils.URLs.URL_MANAGER_CREATE_OBS;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCreateObs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCreateObs extends BaseFragment implements ICreateEventGathering, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int currentStep = 0;
    Bitmap bitmap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.step_view)
    StepView stepView;
    public static final int REQUEST_IMAGE = 100;
    @BindView(R.id.cv_add_photo)
    CardView cv_add_photo;
 @BindView(R.id.o_username)
 EditText o_username;
 @BindView(R.id.o_password)
         EditText o_password;
    @BindView(R.id.o_name)
    EditText o_name;
    @BindView(R.id.o_phonenum)
            EditText o_phonenum;
    @BindView(R.id.o_gender)
    EditText o_gender;
    @BindView(R.id.o_birthday)
            EditText o_birthday;
    @BindView(R.id.o_specialty)
    EditText o_specialty;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    CreateEventGatheringPresenter createEventPresenter;
    List<RelativeLayout> lays;
    Boolean edit = false;
    String event_id;
    ArrayList<String> packageList;
    Observer eventedit;

    public FragmentCreateObs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCreateEventGathering.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCreateObs newInstance(String param1, String param2) {
        FragmentCreateObs fragment = new FragmentCreateObs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            try {

                if (!mParam2.equals("")) {

                    final GsonBuilder builder = new GsonBuilder();
                    final Gson gson = builder.create();
                    eventedit = gson.fromJson(mParam2, Observer.class);

                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_new_obs, container, false);
        ButterKnife.bind(this, rootView);
        createEventPresenter = new CreateEventGatheringPresenter(this);
        packageList = new ArrayList<>();
        init(rootView);
        if (eventedit != null) {
            setItems(eventedit);
        }
        return rootView;
    }

    public void init(View view) {
        stepView.setStepsNumber(2);
        RelativeLayout first_data = view.findViewById(R.id.RfirstDate);
        RelativeLayout second_data = view.findViewById(R.id.RsecondData);
        lays = new ArrayList<>();

        lays.add(first_data);
        lays.add(second_data);



        //cv_add_photo.setOnClickListener(this);
        o_gender.setOnClickListener(this);
        o_birthday.setOnClickListener(this);
        o_specialty.setOnClickListener(this);

        bt_submit.setOnClickListener(this);
      //  bt_submit2.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.o_birthday:


                showbdateDialog(view);
                break;

            case R.id.o_gender:
                showDialog(view, new String[]{"مرد", "زن"}, "جنسیت");
                break;
            case R.id.o_specialty:
                showDialog(view, new String[]{"کامپیوتر", "ریاضی"}, "تخصص");
                break;



            //  showDialog(view);

            case R.id.bt_submit:

                ArrayList<EditText> editTexts = new ArrayList<>();


                editTexts.add(o_username);
                editTexts.add(o_password);
                editTexts.add(o_phonenum);
                editTexts.add(o_name);
                editTexts.add(o_gender);
                editTexts.add(o_birthday);
                editTexts.add(o_specialty);
/*
                editTexts.add(e_country);
                editTexts.add(e_province);
                editTexts.add(e_city);
                editTexts.add(e_adress);
                editTexts.add(e_start_gathering);
                editTexts.add(e_end_gathering);

 */


                //     if (check_item(editTexts)) {
                if (true) {


                    Observer event = new Observer();
                    event.setUsername(o_username.getText().toString());
                    event.setPassword(o_password.getText().toString());
                    event.setName(o_name.getText().toString());
                    event.setPhone_num(o_phonenum.getText().toString());
                    event.setGender(o_gender.getText().toString());
                    event.setB_date(o_birthday.getText().toString());
                    event.setSpecialty(o_specialty.getText().toString());

                    sendData( event);


                }
                break;


        }
    }
    private void showbdateDialog(final View view) {
        DialogTime2 dt = new DialogTime2();
        AlertDialog ad;
        ad = dt.qrcode_reader(getActivity(), view);
        ad.show();

    }


    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }






    private void showDialog(final View view, CharSequence[] charSequenceArr, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setSingleChoiceItems(charSequenceArr, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) view).setText(charSequenceArr[i]);
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }


    public Boolean check_item(ArrayList<EditText> editTexts) {
        Boolean check = true;
        for (EditText e : editTexts
        ) {
            String chara = e.getText().toString();
            if (TextUtils.isEmpty(chara)) {
                repetitiousUsername(e);
                e.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        e.setError(null);
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                check = false;

            }

        }
        return check;
    }

    public void repetitiousUsername(EditText editText) {
        Drawable myIcon = getResources().getDrawable(R.drawable.cancel);
        myIcon.setBounds(0, 0, myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight());
        editText.setError("لطفا این فیلد را کامل کنید.", myIcon);

    }

    private void sendData(Observer obs) {


        Map<String, String> params = new HashMap<String, String>();


        if (edit) {

            params.put("o_id", event_id);
        } else {
            params.put("o_id", "");
        }
        // params.put("tags", tags);
        params.put("admin_id", user_id);
        params.put("username", obs.getUsername());
        params.put("password", obs.getPassword());
        params.put("name", obs.getName());
        params.put("phone_num", obs.getPhone_num());
        params.put("b_date", obs.getB_date());
        params.put("gender", obs.getGender());
        params.put("specialty", obs.getSpecialty());
        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {
                Toast.makeText(getActivity(), "داور اضافه شد", Toast.LENGTH_SHORT).show();            }
        }, params, URL_MANAGER_CREATE_OBS);


    }

    public void setItems(Observer e) {
        PersianDate pd=new PersianDate();
        PersianDateFormat pdformater = new PersianDateFormat("j F H:i");

        edit = true;



        event_id = e.getO_id();
        o_username.setText(e.getUsername());
        o_password.setText(e.getPassword());
        o_name.setText(e.getName());
        o_phonenum.setText(e.getPhone_num());
        o_gender.setText(e.getGender());
        o_birthday.setText(e.getB_date());
        o_specialty.setText(e.getSpecialty());





    }

    @Override
    public void multiChoiceDialog(ArrayList<Object> o) {
        //  showDialog_multiselect();
    }


}
