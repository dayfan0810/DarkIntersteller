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
//                bubbleSortThread = new BubbleSortThread(anim_sort, this);
//                bubbleSortThread.setStarted(false);
//                bubbleSortThread.setData(mArray);
//                getSignature();
                break;
        }
    }

    private void generateRandomArry() {
        Random random = new Random();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            mArray[i] = random.nextInt(100) + 10;
        }
    }

    public void getSignature() {
        PackageManager manager = getContext().getPackageManager();
        StringBuilder builder = new StringBuilder();
        String pkgname = getContext().getPackageName();
        boolean isEmpty = pkgname.isEmpty();
        if (isEmpty) {
            Toast.makeText(getContext(), "应用程序的包名不能为空！", Toast.LENGTH_SHORT);
        } else {
            try {

                PackageInfo packageInfo = manager.getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);

                Signature[] signatures = packageInfo.signatures;
                Signature sign = signatures[0];

                byte[] signByte = sign.toByteArray();
//                Log.e("deng getSingInfo", bytesToHexString(signByte));
                Log.e("deng getSingInfo hash", bytesToHexString(generateSHA1(signByte)));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder buff = new StringBuilder();
        for (byte aByte : bytes) {
            if ((aByte & 0xff) < 16) {
                buff.append('0');
            }
            buff.append(Integer.toHexString(aByte & 0xff));
        }
        return buff.toString();
    }

    public static byte[] generateSHA1(byte[] data) {
        try {
            // 使用getInstance("算法")来获得消息摘要,这里使用SHA-1的160位算法
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // 开始使用算法
            messageDigest.update(data);
            // 输出算法运算结果
            byte[] hashValue = messageDigest.digest(); // 20位字节
            return hashValue;
        } catch (Exception e) {
            Log.e("deng", e.getMessage());
        }
        return null;

    }
}
