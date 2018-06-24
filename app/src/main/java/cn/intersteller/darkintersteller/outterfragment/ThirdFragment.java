package cn.intersteller.darkintersteller.outterfragment;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Day on 2017/1/2.
 */

public class ThirdFragment extends BaseFragment {
    private static ThirdFragment thirdFragment;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public static ThirdFragment newInstance() {
        if (thirdFragment != null) {
            thirdFragment = new ThirdFragment();
        }
        return thirdFragment;
    }
}
