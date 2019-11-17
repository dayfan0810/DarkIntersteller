package cn.intersteller.darkintersteller.fragment.innerfragment.thirdinnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.heaven7.android.dragflowlayout.DragAdapter;
import com.heaven7.android.dragflowlayout.DragFlowLayout;
import com.heaven7.android.dragflowlayout.IDraggable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.intersteller.darkintersteller.R;
import cn.intersteller.darkintersteller.custview.BubbleSortViewByAddView;

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
    private LinearLayout ll_sort_result;
    private BubbleSortViewByAddView anim_sort;
    private View down_divider;
    private ScrollView scroll_view;


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
        ll_sort_result = (LinearLayout) v.findViewById(R.id.ll_sort_result);
        scroll_view = (ScrollView) v.findViewById(R.id.scroll_view);
        anim_sort = (BubbleSortViewByAddView) v.findViewById(R.id.anim_sort);
        down_divider = (View) v.findViewById(R.id.down_divider);
        bt_bubble_get_arr1 = (Button) v.findViewById(R.id.bt_bubble_get_arr1);
        bt_bubble_get_arr2 = (Button) v.findViewById(R.id.bt_bubble_get_arr2);
        bt_bubble_get_arr3 = (Button) v.findViewById(R.id.bt_bubble_get_arr3);
        bt_bubble_get_arr1.setOnClickListener(this);
        bt_bubble_get_arr2.setOnClickListener(this);
        bt_bubble_get_arr3.setOnClickListener(this);
        down_divider.setVisibility(View.GONE);
        initView();
        return v;
    }

    private void initView() {
        mDragflowLayout.setOnItemClickListener(new DragFlowLayout.OnItemClickListener() {
            @Override
            public boolean performClick(DragFlowLayout dragFlowLayout, View child,
                                        MotionEvent event, int dragState) {
                //检查是否点击了关闭按钮(iv_close控件)。点击了就删除
                //ViewStatusUtils.isViewUnderInScreen 判断点击事件是否是你需要的.
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
                        dragState != DragFlowLayout.DRAG_STATE_IDLE
                                && data.draggable ? View.VISIBLE : View.INVISIBLE);
            }

            @NonNull
            @Override
            public ArrayBean getData(View itemView) {
                return (ArrayBean) itemView.getTag();
            }
        });
        mDragflowLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    scroll_view.requestDisallowInterceptTouchEvent(false);
                }else {
                    scroll_view.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bubble_get_arr1:
                generateRandomArry();
                break;
            case R.id.bt_bubble_get_arr2:
                // 开始排序,先清空结果view
                ll_sort_result.removeAllViews();
                anim_sort.startBond(mArray);
                break;
            case R.id.bt_bubble_get_arr3:
                resetView();
                break;
        }
    }

    private void generateRandomArry() {
        DragFlowLayout.DragItemManager dragItemManager = mDragflowLayout.getDragItemManager();
        int itemCount = dragItemManager.getItemCount();
        if (itemCount > 0){
            Toast.makeText(getContext(),"请先重置view",Toast.LENGTH_SHORT).show();
            return;
        }
        Random random = new Random();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            mArray[i] = random.nextInt(88) + 10;
        }
        List arrayList1 = new ArrayList();
        for (int i = 0; i < SIEZ_ARRAY; i++) {
            final ArrayBean bean = new ArrayBean("" + mArray[i]);
            arrayList1.add(bean);
        }
        dragItemManager.addItems(0, arrayList1);
        down_divider.setVisibility(View.VISIBLE);

    }

    private void resetView() {
        DragFlowLayout.DragItemManager dragItemManager = mDragflowLayout.getDragItemManager();
        int itemCount = dragItemManager.getItemCount();
        int childCount = anim_sort.getChildCount();
        if (itemCount == 0 && childCount == 0) {
            return;
        }
        anim_sort.removeAllViews();
        mDragflowLayout.removeAllViews();
        down_divider.setVisibility(View.GONE);
    }

    private static class ArrayBean implements IDraggable {
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
