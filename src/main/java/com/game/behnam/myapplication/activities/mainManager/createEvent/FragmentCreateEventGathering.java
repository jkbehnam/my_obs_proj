package com.game.behnam.myapplication.activities.mainManager.createEvent;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.VolleyMultipartRequest;
import com.game.behnam.myapplication.activities.mainManager.createEvent.select_photo.GlideApp;
import com.game.behnam.myapplication.activities.mainManager.createEvent.select_photo.ImagePickerActivity;
import com.game.behnam.myapplication.base.BaseFragment;
import com.game.behnam.myapplication.utils.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.shuhart.stepview.StepView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.game.behnam.myapplication.activities.Main.user_id;
import static com.game.behnam.myapplication.activities.mainManager.createEvent.CreateEventGatheringPresenter.faToEn;
import static com.game.behnam.myapplication.utils.URLs.URL_CREATE_EVENT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCreateEventGathering#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCreateEventGathering extends BaseFragment implements ICreateEventGathering, View.OnClickListener {
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
    @BindView(R.id.iv_add_photo)
    ImageView iv_add_photo;
    @BindView(R.id.e_name)
    EditText e_name;
    @BindView(R.id.e_desc)
    EditText e_desc;
    @BindView(R.id.e_price)
    EditText e_price;
    @BindView(R.id.e_age)
    EditText e_age;
    @BindView(R.id.e_grade)
    EditText e_grade;
    @BindView(R.id.e_sex)
    EditText e_sex;
    @BindView(R.id.e_lang)
    EditText e_lang;
    @BindView(R.id.e_reg_max)
    EditText e_reg_max;
    @BindView(R.id.e_reg_deadline)
    EditText e_reg_deadline;
    @BindView(R.id.e_password)
    EditText e_password;
    @BindView(R.id.e_country)
    EditText e_country;
    @BindView(R.id.e_province)
    EditText e_province;
    @BindView(R.id.e_city)
    EditText e_city;
    @BindView(R.id.e_adress)
    EditText e_adress;
    @BindView(R.id.e_start_gathering)
    EditText e_start_gathering;
    @BindView(R.id.e_end_gathering)
    EditText e_end_gathering;
    @BindView(R.id.bt_submit)
    Button bt_submit;
    @BindView(R.id.bt_submit2)
    Button bt_submit2;
    CreateEventGatheringPresenter createEventPresenter;
    List<RelativeLayout> lays;
    Boolean edit = false;
    String event_id;
    Event eventedit;

    public FragmentCreateEventGathering() {
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
    public static FragmentCreateEventGathering newInstance(String param1, String param2) {
        FragmentCreateEventGathering fragment = new FragmentCreateEventGathering();
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
                    eventedit = gson.fromJson(mParam2, Event.class);

                }
            } catch (Exception e) {
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_event_gathering, container, false);
        ButterKnife.bind(this, rootView);
        createEventPresenter = new CreateEventGatheringPresenter(this);
        setFragmentActivity(this.getActivity());
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

        select_lay(lays, 0);
        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                select_lay(lays, 1 - step);
                stepView.go(1 - step, true);
                currentStep = 1 - step;

            }
        });
        cv_add_photo.setOnClickListener(this);
        e_age.setOnClickListener(this);
        e_grade.setOnClickListener(this);
        e_sex.setOnClickListener(this);
        e_lang.setOnClickListener(this);
        e_reg_max.setOnClickListener(this);
        e_reg_deadline.setOnClickListener(this);
        e_country.setOnClickListener(this);
        e_province.setOnClickListener(this);
        e_city.setOnClickListener(this);
        e_start_gathering.setOnClickListener(this);
        e_end_gathering.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        bt_submit2.setOnClickListener(this);

    }

    public void select_lay(List<RelativeLayout> lays, int currentStep) {
        for (RelativeLayout lay : lays
        ) {
            lay.setVisibility(View.GONE);

        }
        lays.get(currentStep).setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_add_photo:

                Dexter.withActivity(getActivity())
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions();
                                }

                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    showSettingsDialog();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
                break;
            case R.id.e_age:
                showDialog(view, new String[]{"8-10", "10-15", "15-18", "20<"}, "رده سنی");
                break;
            case R.id.e_grade:
                showDialog(view, new String[]{"5", "6", "7", "8", "9", "10", "11", "12", "13"}, "پایه تحصیلی");
                break;
            case R.id.e_sex:
                showDialog(view, new String[]{"مرد", "زن"}, "جنسیت");
                break;
            case R.id.e_lang:
                showDialog(view, new String[]{"fr", "eng"}, "زبان");
                break;
            case R.id.e_reg_max:
                // showDialog(view);
                break;
            case R.id.e_reg_deadline: {
                AlertDialog ad;
                DialogTime dt = new DialogTime();
                ad = dt.qrcode_reader(getActivity(), view);
                ad.show();
            }
            break;
            case R.id.e_country:
                // showDialog(view);
                break;
            case R.id.e_province:
                showProvinceDialog(view);
                break;
            case R.id.e_city:
                showCityDialog(view);
                break;
            case R.id.e_start_gathering: {
                AlertDialog ad;
                DialogTime dt = new DialogTime();
                ad = dt.qrcode_reader(getActivity(), view);
                ad.show();
            }
            //  showDialog(view);
            break;
            case R.id.e_end_gathering: {
                AlertDialog ad;
                DialogTime dt = new DialogTime();
                ad = dt.qrcode_reader(getActivity(), view);
                ad.show();
            }
            //  showDialog(view);
            break;
            case R.id.bt_submit: {
                ArrayList<EditText> editTexts = new ArrayList<>();


                editTexts.add(e_name);
                editTexts.add(e_desc);
                editTexts.add(e_price);
                editTexts.add(e_age);
                editTexts.add(e_grade);
                editTexts.add(e_sex);
                editTexts.add(e_lang);
                editTexts.add(e_reg_max);
                editTexts.add(e_reg_deadline);
                // if (check_item(editTexts)) {
                if (true) {
                    currentStep++;
                    stepView.go(currentStep, true);
                    select_lay(lays, currentStep);

                    if ((currentStep == stepView.getStepCount() - 1)) {
                    }
                }
            }
            break;
            case R.id.bt_submit2:
                ArrayList<EditText> editTexts = new ArrayList<>();
                editTexts.add(e_name);
                editTexts.add(e_desc);
                editTexts.add(e_price);
                editTexts.add(e_age);
                editTexts.add(e_grade);
                editTexts.add(e_sex);
                editTexts.add(e_lang);
                editTexts.add(e_reg_max);
                editTexts.add(e_reg_deadline);

                editTexts.add(e_country);
                editTexts.add(e_province);
                editTexts.add(e_city);
                editTexts.add(e_adress);
                editTexts.add(e_start_gathering);
                editTexts.add(e_end_gathering);


                //     if (check_item(editTexts)) {
                if (true) {
                    /*
                    UserDetail userDetail = new UserDetail();
                    pm = new PrefManager(getApplicationContext());
                    userDetail.setU_id(pm.getUserDetails().get("u_id"));
                    userDetail.setUd_name(ud_name.getText().toString());
                    userDetail.setUd_email(ud_email.getText().toString());
                    userDetail.setUd_birthday(ud_birthday.getText().toString());
                    userDetail.setUd_sex(ud_sex.getText().toString());
                    userDetail.setUd_national_code(ud_national_code.getText().toString());
                    userDetail.setUd_country(ud_country.getText().toString());
                    userDetail.setUd_city(ud_city.getText().toString());
                    userDetail.setUd_grade(ud_grade.getText().toString());
                    userDetail.setUd_school(ud_school.getText().toString());
                    userDetail.setUd_province(ud_province.getText().toString());

                    connectToServer.SetUserDetails(reg_data, userDetail);
*/

                    Event event = new Event();
                    event.setE_name(e_name.getText().toString());
                    event.setE_description(e_desc.getText().toString());
                    event.setE_price(e_price.getText().toString());
                    event.setE_age(e_age.getText().toString());
                    event.setE_grade(e_grade.getText().toString());
                    event.setE_sex(e_sex.getText().toString());
                    event.setE_lang(e_lang.getText().toString());
                    event.setE_Reg_max_num(e_reg_max.getText().toString());
                    event.setE_reg_deadline(e_reg_deadline.getTag().toString());
                    event.setE_password(e_password.getText().toString());
                    event.setE_country(e_country.getText().toString());
                    event.setE_province(e_province.getText().toString());
                    event.setE_city(e_city.getText().toString());
                    event.setE_address(e_adress.getText().toString());
                    event.setE_gather_start_time(e_start_gathering.getTag().toString());
                    event.setE_gather_end_time(e_end_gathering.getTag().toString());
                    event.setE_type("گردهمایی");
                    uploadBitmap(bitmap, event);


                }
                break;


        }
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getActivity(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);


                    // loading profile image from local cache
                    loadProfile(uri.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);
        Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
        GlideApp.with(getActivity())
                .asBitmap()
                .load(url)

                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_add_photo);
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

    private void showProvinceDialog(final View view) {
        ArrayList<String> s = new ArrayList<>();
        try {
            //JSONObject obj = new JSONObject(loadJSONFromAsset());
            //  JSONArray m_jArry = obj.getJSONArray(loadJSONFromAsset());
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                // Log.d("Details-->", jo_inside.getString("formule"));
                String formula_value = jo_inside.getString("name");
                // String url_value = jo_inside.getString("url");
                s.add(formula_value);
                //Add your values in your `ArrayList` as below:
                // m_li = new HashMap<String, String>();
                // m_li.put("formule", formula_value);
                // m_li.put("url", url_value);

                //formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final CharSequence[] charSequenceArr = s.toArray(new CharSequence[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("استان");
        builder.setSingleChoiceItems(charSequenceArr, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) view).setText(charSequenceArr[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getApplication().getAssets().open("Province.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void showCityDialog(final View view) {
        if (e_province != null && !e_province.getText().toString().equals("")) {
            ArrayList<String> s = new ArrayList<>();
            boolean found = false;
            try {
                //JSONObject obj = new JSONObject(loadJSONFromAsset());
                //  JSONArray m_jArry = obj.getJSONArray(loadJSONFromAsset());
                JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
                ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> m_li;

                for (int i = 0; i < m_jArry.length() && !found; i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    String formula_value = jo_inside.getString("name");
                    if (formula_value.equals(e_province.getText().toString())) {
                        JSONArray cities = jo_inside.getJSONArray("Cities");
                        for (int i2 = 0; i2 < cities.length(); i2++) {
                            JSONObject city = cities.getJSONObject(i2);
                            // Log.d("Details-->", jo_inside.getString("formule"));
                            String cityname = city.getString("name");
                            s.add(cityname);
                            found = true;
                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final CharSequence[] charSequenceArr = s.toArray(new CharSequence[0]);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("شهر های استان " + e_province.getText().toString());
            builder.setSingleChoiceItems(charSequenceArr, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((EditText) view).setText(charSequenceArr[i]);
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else Toast.makeText(getActivity(), "استان را انتخاب نکردید!", Toast.LENGTH_SHORT).show();
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

    private void uploadBitmap(final Bitmap bitmap, Event event) {

        //getting the tag from the edittext
        final String tags = "bbc";

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL_CREATE_EVENT,
                new Response.Listener<NetworkResponse>() {

                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            String s = new String(response.data);
                            JSONObject obj = new JSONObject(new String(response.data));
                            hideLoading_base();
                            Toast.makeText(getActivity(), "رویداد ساخته شد", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (edit) {

                    params.put("e_id", event_id);
                } else {
                    params.put("e_id", "");
                }

                params.put("admin_id", user_id);
                params.put("tags", tags);
                params.put("e_name", event.getE_name());
                params.put("e_description", event.getE_description());
                params.put("e_price", faToEn(event.getE_price()));
                params.put("e_age", event.getE_age());
                params.put("e_grade", event.getE_grade());
                params.put("e_sex", event.getE_sex());
                params.put("e_lang", event.getE_lang());
                params.put("e_reg_max_num", event.getE_Reg_max_num());
                params.put("e_reg_deadline", event.getE_reg_deadline());
                params.put("e_password", faToEn(event.getE_password()));
                params.put("e_country", event.getE_country());
                params.put("e_province", faToEn(event.getE_province()));
                params.put("e_city", event.getE_city());
                params.put("e_address", event.getE_address());
                params.put("e_gather_start_time", event.getE_gather_start_time());
                params.put("e_gather_end_time", event.getE_gather_end_time());
                params.put("e_type", "گردهمایی");


                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                try {
                    params.put("pic", new DataPart(imagename + ".jpg", getFileDataFromDrawable(bitmap)));
                }catch (Exception e){}
                return params;
            }
        };
        volleyMultipartRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        //adding the request to volley
        Volley.newRequestQueue(getActivity()).add(volleyMultipartRequest);
        showLoading_base();
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void setItems(Event e) {

        PersianDate pd = new PersianDate();
        PersianDateFormat pdformater = new PersianDateFormat("j F H:i");

        loadProfile(e.getE_img());
        edit = true;
        event_id = e.getE_id();

        e_name.setText(e.getE_name());
        e_desc.setText(e.getE_description());
        e_price.setText(e.getE_price());
        e_age.setText(e.getE_age());
        e_grade.setText(e.getE_grade());
        e_sex.setText(e.getE_sex());
        e_lang.setText(e.getE_lang());
        e_reg_max.setText(e.getE_Reg_max_num());
        e_password.setText(e.getE_password());
        pd = new PersianDate(Long.parseLong(e.getE_reg_deadline()) * 1000);
        e_reg_deadline.setText(pdformater.format(pd));

        e_reg_deadline.setTag(e.getE_reg_deadline());
        e_country.setText(e.getE_country());
        e_province.setText(e.getE_province());
        e_city.setText(e.getE_city());
        e_adress.setText(e.getE_address());

        pd = new PersianDate(Long.parseLong(e.getE_gather_start_time()) * 1000);
        e_start_gathering.setText(pdformater.format(pd));

        e_start_gathering.setTag(e.getE_gather_start_time());

        pd = new PersianDate(Long.parseLong(e.getE_gather_end_time()) * 1000);
        e_end_gathering.setText(pdformater.format(pd));

        e_end_gathering.setTag(e.getE_gather_end_time());


    }

    @Override
    public void multiChoiceDialog(ArrayList<Object> o) {

    }
}
