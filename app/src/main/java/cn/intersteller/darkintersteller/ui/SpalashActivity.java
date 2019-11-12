package cn.intersteller.darkintersteller.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.intersteller.darkintersteller.MainActivity;

public class SpalashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 利用消息处理器实现延迟跳转到登录窗口

    }


    @Override
    protected void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // 启动登录窗口
                startActivity(new Intent(SpalashActivity.this, MainActivity.class));
                // 关闭启动画面
                finish();
            }
        });
    }
}
