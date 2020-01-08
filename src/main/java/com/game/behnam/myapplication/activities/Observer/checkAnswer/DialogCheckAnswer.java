package com.game.behnam.myapplication.activities.Observer.checkAnswer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.activities.Observer.questionList.ObsQuestionListPresenter;
import com.game.behnam.myapplication.utils.Event;
import com.game.behnam.myapplication.utils.Question;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DialogCheckAnswer extends Dialog implements View.OnClickListener {
    Event event;
    Context context;
    @BindView(R.id.tv_question)
    TextView tv_question;
    @BindView(R.id.tv_gamer_answer)
    TextView tv_gamer_answer;
    @BindView(R.id.tv_obs_answer)
    EditText tv_obs_answer;
    @BindView(R.id.btn_accept)
    Button btn_accept;
    @BindView(R.id.btn_reject)
    Button btn_reject;
    ImageView btn_ok_alert;
    EditText et_price_alert;
    AlertDialog ad;
    Dialog dialog;
    String prof;
    FragmentActivity fragmentActivity;
    Question question;
    String u_id;
    //@BindView(R.id.fragment_multi_choice_cv_question)
    // CardView cv_answer;
    // @BindView(R.id.scrollv)
    // ScrollView scrollv;
    ObsQuestionListPresenter obsQuestionListPresenter;
    int position;
    CheckAnswerPresenter checkAnswerPresenter;

    public DialogCheckAnswer(@NonNull Context context) {

        super(context);
    }


    @SuppressLint("ResourceAsColor")
    public Dialog CheckAnswer(Context context, Question question, int position, String u_id, ObsQuestionListPresenter obsQuestionListPresenter) {
        this.question = question;
        this.obsQuestionListPresenter = obsQuestionListPresenter;
        checkAnswerPresenter = new CheckAnswerPresenter(this);
        this.context = context;
        this.event = event;
        this.position = position;
        this.fragmentActivity = fragmentActivity;
        // ald_insert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.u_id = u_id;

        dialog = new Dialog(context, android.R.style.Theme_Holo_Light_NoActionBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_request_details);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ButterKnife.bind(this, dialog);

        Toolbar toolbar = dialog.findViewById(R.id.toolbar);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        ((AppCompatActivity) context).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView tvToolbarTitle = (TextView) dialog.findViewById(R.id.txttoolbar);

        //  Typeface typeface3 = Typeface.createFromAsset(a.getAssets(), "font/vazirbold.ttf");
        //  tvToolbarTitle.setTypeface(typeface3, Typeface.BOLD);
        tvToolbarTitle.setText("بررسی پاسخ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(dialog.getContext().getResources().getColor(R.color.status_bar));
            //  dialog.getWindow().setNavigationBarColor(dialog.getContext().getResources().getColor(R.color.game_nav));
        }

        setTextViews(question);
        btn_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        // answerQuestionPresenter.getAnswerLenght();
        //  connectToServer.getQuestionScore(answerQuestionPresenter, question.getId());

/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(dialog.getContext().getResources().getColor(R.color.white));
            dialog.getWindow().setNavigationBarColor(dialog.getContext().getResources().getColor(R.color.bottonNav));
        }
        */

        return dialog;
    }

    public void setTextViews(Question q) {
        tv_question.setText(q.getQ_title());
        tv_gamer_answer.setText(q.getG_answer());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_accept:

                checkAnswerPresenter.answerToRequest(question.getGet_question_id(), question.getDesc_id(), "true", tv_obs_answer.getText().toString());
                break;
            case R.id.btn_reject:
                checkAnswerPresenter.answerToRequest(question.getGet_question_id(), question.getDesc_id(), "false", tv_obs_answer.getText().toString());


                break;
        }
    }
public void showMessage(String message){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

}

    public void removeItem() {
        obsQuestionListPresenter.removeItem(position);
    }
}
