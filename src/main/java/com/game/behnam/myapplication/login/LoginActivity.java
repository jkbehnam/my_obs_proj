package com.game.behnam.myapplication.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.core.content.ContextCompat;

import com.game.behnam.myapplication.activities.Main;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.alert_btn_ok)
    Button btn_login;

    @BindView(R.id.activity_login_et_username)
    EditText activity_login_et_username;

    @BindView(R.id.activity_login_et_password)
    EditText activity_login_et_password;
    @BindView(R.id.login_opt)
    Spinner login_opt;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.background)); //status bar or the time bar at the top

        }

//create a list of items for the spinner.
        String[] items = new String[]{"مدیر", "داور"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        login_opt.setAdapter(adapter);
        loginPresenter = new LoginPresenter(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.alert_btn_ok:
                loginPresenter.login(activity_login_et_username.getText().toString(), activity_login_et_password.getText().toString(), login_opt.getSelectedItemPosition());

                break;
        }

    }

    void loginSuccessful() {
        Intent intent = new Intent(LoginActivity.this, Main.class);
        Bundle b = new Bundle();
        b.putInt("EXTRA_SESSION_ID", 2); //Your id
        intent.putExtras(b);
        startActivity(intent);
        this.finish();
    }
}
