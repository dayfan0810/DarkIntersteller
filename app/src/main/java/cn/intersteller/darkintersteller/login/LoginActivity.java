package cn.intersteller.darkintersteller.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.intersteller.darkintersteller.MainActivity;
import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.login.presenter.ILoginPresenter;
import cn.intersteller.darkintersteller.login.presenter.LoginPresenterCompl;
import cn.intersteller.darkintersteller.login.view.ILoginView;
import cn.intersteller.darkintersteller.utils.SharedPreferenceUtils;


public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button skip_login_login;
    ILoginPresenter loginPresenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //find view
        editUser = (EditText) this.findViewById(R.id.et_login_username);
        editPass = (EditText) this.findViewById(R.id.et_login_password);
        btnLogin = (Button) this.findViewById(R.id.btn_login_login);
        progressBar = (ProgressBar) this.findViewById(R.id.progress_login);
        skip_login_login = (Button) this.findViewById(R.id.skip_login_login);

        //set listener
        btnLogin.setOnClickListener(this);
        skip_login_login.setOnClickListener(this);

        //init
        loginPresenter = new LoginPresenterCompl(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btnLogin.setEnabled(false);
                loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.toggleSoftInput(0, 0);
//                }
                break;
            case R.id.skip_login_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    @Override
    public void onClearText() {
        editUser.setText("");
        editPass.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code, long userid) {
        loginPresenter.setProgressBarVisiblity(View.GONE);
        btnLogin.setEnabled(true);
        if (code == 200) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
            //保存用户ID到本地,给后面会使用
            SharedPreferenceUtils.removeByKey(this, "login_user_id");
            SharedPreferenceUtils.savePref("login_user_id", userid);
            finish();
        } else
            Toast.makeText(this, "登录失败，code = " + code, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

}
