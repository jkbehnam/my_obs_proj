package com.game.behnam.myapplication.activities.mainManager.gameDetails;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.game.behnam.myapplication.ConnectToServer;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.VolleyCallback;
import com.game.behnam.myapplication.base.BaseFragment;
import com.game.behnam.myapplication.utils.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.game.behnam.myapplication.utils.URLs.URL_GET_EVENT_STATUS;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGameDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGameDetails extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int currentStep = 0;
    Bitmap bitmap;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.tv_gamer_num)
    TextView tv_gamer_num;
@BindView(R.id.lay_gamer_count)
LinearLayout lay_gamer_count;
    @BindView(R.id.tv_quesion_num)
    TextView tv_quesion_num;


    Event eventedit;

    public FragmentGameDetails() {
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
    public static FragmentGameDetails newInstance(String param1, String param2) {
        FragmentGameDetails fragment = new FragmentGameDetails();
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
        View rootView = inflater.inflate(R.layout.fragment_dashboard_statistics, container, false);
        ButterKnife.bind(this, rootView);
        setFragmentActivity(this.getActivity());

        init(rootView);
        if (eventedit != null) {
        }
        return rootView;
    }

    public void init(View view) {
        getdata();

        lay_gamer_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad;
                DialogEventEnd dt = new DialogEventEnd();
                ad = dt.init(getActivity(),"",eventedit.getE_id());

                ad.show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    public void getdata() {


        Map<String, String> param = new HashMap<String, String>();

        param.put("e_id", eventedit.getE_id());
        ConnectToServer.any_send(new VolleyCallback() {
            @Override
            public void onSuccess(String result) throws JSONException {

                reciveRequeset(result);
            }
        }, param, URL_GET_EVENT_STATUS);
    }

    public void reciveRequeset(String result) throws JSONException {
        JSONObject obj = new JSONObject(result);
        String s = obj.getString("u_count");
        tv_gamer_num.setText(s);

        String s2 = obj.getString("q_count");
        tv_quesion_num.setText(s2);
    }


}
