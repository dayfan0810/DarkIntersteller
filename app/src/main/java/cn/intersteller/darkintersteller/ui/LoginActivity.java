package cn.intersteller.darkintersteller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import cn.intersteller.darkintersteller.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private ImageButton delete_username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        delete_username = findViewById(R.id.delete_username);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                break;
        }

    }
}
