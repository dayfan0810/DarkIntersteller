package cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heaven7.android.dragflowlayout.DragAdapter;
import com.heaven7.android.dragflowlayout.DragFlowLayout;
import com.heaven7.android.dragflowlayout.IDraggable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.intersteller.darkintersteller.R;

public class BubbleSortBeautyFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "BubbleSortFragment";
    private View v;
    private static final int SIEZ_ARRAY = 6;

    private Button bt_bubble_get_arr1;
    private Button bt_bubble_get_arr2;
    private Button bt_bubble_get_arr3;
    //柱状图维护数据
    int screenWidth = 0, screenHeight = 0;          //单位：px，屏幕的宽、高
    int columnWidth = 30;             //单位：px，柱状View的宽度,计算一次用全局变量存储下次就不需要再计算了
    List<View> mViews = new ArrayList<View>();
    int[] mArray = new int[SIEZ_ARRAY];
    private DragFlowLayout mDragflowLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bubblesort_beauty, container, false);
        mDragflowLayout = (DragFlowLayout) v.findViewById(R.id.sort);
        bt_bubble_get_arr1 = (Button) v.findViewById(R.id.bt_bubble_get_arr1);
        bt_bubble_get_arr2 = (Button) v.findViewById(R.id.bt_bubble_get_arr2);
        bt_bubble_get_arr3 = (Button) v.findViewById(R.id.bt_bubble_get_arr3);
        bt_bubble_get_arr1.setOnClickListener(this);
        bt_bubble_get_arr2.setOnClickListener(this);
        bt_bubble_get_arr3.setOnClickListener(this);
        initView();
        return v;
    }

    private void initView() {
        mDragflowLayout.setOnItemClickListener(new DragFlowLayout.OnItemClickListener() {
            @Override
            public boolean performClick(DragFlowLayout dragFlowLayout, View child,
                                        MotionEvent event, int dragState) {
                //检查是否点击了关闭按钮(iv_close控件)。点击了就删除
                //ViewUtils.isViewUnderInScreen 判断点击事件是否是你需要的.
                //dragState 是拖拽状态。
                boolean performed = dragState != DragFlowLayout.DRAG_STATE_IDLE;
                if (performed) {
                    dragFlowLayout.removeView(child);
                }
                //点击事件
                return performed;
            }
        });

        mDragflowLayout.setDragAdapter(new DragAdapter<ArrayBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_drag_flow;
            }
            @Override
            public void onBindData(View itemView, int dragState, ArrayBean data) {
                itemView.setTag(data);

                TextView tv = (TextView) itemView.findViewById(R.id.tv_text);
                tv.setText(data.text);
                //iv_close是关闭按钮。只有再非拖拽空闲的情况吓才显示
                itemView.findViewById(R.id.iv_close).setVisibility(
                        dragState!= DragFlowLayout.DRAG_STATE_IDLE
                                && data.draggable ? View.VISIBLE : View.INVISIBLE);
            }
            @NonNull
            @Override
            public ArrayBean getData(View itemView) {
                return (ArrayBean) itemView.getTag();
            }
        });
    }

    private void generateRandomArry() {
        Random random = new Random();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            mArray[i] = random.nextInt(99) + 10;//10-200
        }
        List arrayList1 = new ArrayList();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            Log.i("deng", String.valueOf(mArray[i]));
            final ArrayBean bean = new ArrayBean( ""+mArray[i]);
            arrayList1.add(bean);
        }
        DragFlowLayout.DragItemManager dragItemManager = mDragflowLayout.getDragItemManager();
        int itemCount = dragItemManager.getItemCount();
        dragItemManager.addItems(0,arrayList1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bubble_get_arr1:
                generateRandomArry();
                break;
            case R.id.bt_bubble_get_arr2:
                break;
            case R.id.bt_bubble_get_arr3:
                break;
        }
    }

    private static class  ArrayBean  implements IDraggable{
        String text;
        boolean draggable = true;
        public ArrayBean(String text) {
            this.text = text;
        }
        @Override
        public boolean isDraggable() {
            return draggable;
        }

    }
}
