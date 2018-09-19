package cn.intersteller.darkintersteller.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

public class SpalashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 利用消息处理器实现延迟跳转到登录窗口
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 启动登录窗口
                startActivity(new Intent(SpalashActivity.this, MainActivity.class));
                // 关闭启动画面
                finish();
            }
        }, 1500);

    }
}
