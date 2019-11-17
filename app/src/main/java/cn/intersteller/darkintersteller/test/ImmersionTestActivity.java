package cn.intersteller.darkintersteller.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import cn.intersteller.darkintersteller.R;

public class ImmersionTestActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_immersion);
        //第一种写法
        Button full_screen1 = (Button) findViewById(R.id.full_screen1);
        full_screen1.setOnClickListener(this);

        //第二种写法,不用实现View.OnClickListener
        Button full_screen2 = (Button) findViewById(R.id.full_screen2);
        full_screen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Button full_screen3 = (Button) findViewById(R.id.full_screen3);
        full_screen3.setOnClickListener(this);
        Button full_screen4 = (Button) findViewById(R.id.full_screen4);
        full_screen4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_screen1:
                View decorView1 = this.getWindow().getDecorView();
                decorView1.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );

                break;
            case R.id.full_screen2:
                break;
            case R.id.full_screen3:
                break;
            case R.id.full_screen4:
                break;
        }
    }
}
