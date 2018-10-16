package cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.heaven7.android.dragflowlayout.DragFlowLayout;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.custview.BubbleSortViewByCanvas;
import cn.intersteller.darkintersteller.sort.AlgorithmThread;
import cn.intersteller.darkintersteller.sort.BubbleSortThread;

public class BubbleSortBeautyFragmentLast extends Fragment implements View.OnClickListener {

    private static final String TAG = "BubbleSortBeautyFragmentLast";
    private static final int SIEZ_ARRAY = 15;

    private Button bt_bubble_get_arr;
    private Button bt_bubble_start_sort;
    //柱状图维护数据
    int screenWidth = 0, screenHeight = 0;          //单位：px，屏幕的宽、高
    int[] mArray = new int[SIEZ_ARRAY];
    private BubbleSortViewByCanvas anim_sort;
    private BubbleSortThread bubbleSortThread;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        Log.i("deng", "screenWidth = " + screenWidth);
        screenHeight = displayMetrics.heightPixels;
        Log.i("deng", "screenHeight = " + screenHeight);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bubblesort_beauty_last, container, false);
        anim_sort = (BubbleSortViewByCanvas) v.findViewById(R.id.sortView);
        bt_bubble_get_arr = (Button) v.findViewById(R.id.bt_bubble_get_arr);
        bt_bubble_start_sort = (Button) v.findViewById(R.id.bt_bubble_start_sort);
        bt_bubble_get_arr.setOnClickListener(this);
        bt_bubble_start_sort.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bubble_get_arr:
                generateRandomArry();
                anim_sort.setArray(mArray);
                anim_sort.invalidate();
                break;
            case R.id.bt_bubble_start_sort:
                // 开始排序,先清空结果view
                bubbleSortThread = new BubbleSortThread(anim_sort, this);
                bubbleSortThread.setStarted(true);
                bubbleSortThread.setData(mArray);
                bubbleSortThread.sendMessage(AlgorithmThread.COMMAND_START_ALGORITHM);
                break;
        }
    }

    private void generateRandomArry() {
        Random random = new Random();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            mArray[i] = random.nextInt(100) + 10;
        }
    }


}
