package cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.utils.ScreenUtils;

public class BubbleSortFragment extends Fragment {

    private static final String TAG = "BubbleSortFragment";
    private View v;
    private static final int SIEZ_ARRAY=100;

    private Button bt_bubble_get_arr;
    //柱状图维护数据
    int screenWidth=0,screenHeight=0;          //单位：px，屏幕的宽、高
    int columnWidth=0;             //单位：px，柱状View的宽度,计算一次用全局变量存储下次就不需要再计算了
    List<View> mViews=new ArrayList<View>();
    int[] mArray = new int[SIEZ_ARRAY];
    private LinearLayout ll_bubble_sort;
    //下面两个值是为界面便于根据数组大小动态设置每个柱状View宽度定义的
    public static final int paddingLR=6;     //单位：dp 这个是柱状View外层LinearLayout的左右padding大小
    public static final int intervalColumn=1;//单位：dp 这个是每个柱状View相互间的间隔

    double columnPixPerNum=0.0;                //单位：px/1  这个是高度上单位数字所表示的像素。


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bubblesortfragment, container, false);
        ll_bubble_sort = (LinearLayout) v.findViewById(R.id.ll_bubble_sort);
        bt_bubble_get_arr = (Button) v.findViewById(R.id.bt_bubble_get_arr1);
        bt_bubble_get_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomArry();
                addCharView();
            }
        });
        return v;
    }



    private void generateRandomArry() {
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            mArray[i] = random.nextInt(200) + 10;//10-200
            count++;
        }
//        Toast.makeText(getContext(),"已生成 "+count+" 个数组",Toast.LENGTH_LONG).show();
//        arrayToString("arraybefore");//输出原始数组

    }

    public void addCharView() {
        Toast.makeText(getContext(),"11111",Toast.LENGTH_LONG).show();
        if(mViews.size() <= 0){
            for (int i = 0; i < mArray.length; i++) {
                View view = new View(getContext());
                ll_bubble_sort.addView(view);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                columnWidth = (screenWidth - ScreenUtils.dp2px(getContext(), paddingLR * 2)) / mArray.length
                        -ScreenUtils.dp2px(getContext(), intervalColumn);
                layoutParams.setMargins(ScreenUtils.dp2px(getContext(), intervalColumn), 0, 0, 0);
                layoutParams.height = (int) (mArray[i] * pixPerNum());
                layoutParams.width = columnWidth;
                view.setLayoutParams(layoutParams);
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.chartColor));
                mViews.add(view);
            }
        }
    }

    //获得在高度上，单位数字所代表的像素，由于屏幕高度是像素，而我们的排序为int数字，要想形象化绘制成柱状图
    //就要计算出单位数字的像素，然后通过数组中的数字相乘即可得到柱状view的高度了
    private double pixPerNum(){
        columnPixPerNum=(double) screenHeight*0.6/(Max(mArray));
        return columnPixPerNum;
    }

    //获得数组中最大数字，仅仅用于@pixPerNum方法中
    private int Max(int array[]){
        int max=0;
        for(int i=0;i<array.length;i++){
            if(max<array[i]){
                max=array[i];
            }
        }
        return max;
    }
    /**
     * 输出数组
     *
     * @param tag
     */
    private void arrayToString(String tag) {
        String result = "";
        for (int i = 0; i < mArray.length; i++) {
            result = result + " " + mArray[i];
            if (i % 5 == 0) {
                result = result + "\n";
            }

            Log.i(TAG, tag + result);
        }
    }
}
