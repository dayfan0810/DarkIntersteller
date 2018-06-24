package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

import cn.intersteller.darkintersteller.R;

/**
 * Created by Day on 2017/1/2.
 */

public class StockFragment extends Fragment implements View.OnClickListener {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewUtils.inject(this, view);
            return view;
        }
        view = inflater.inflate(R.layout.recomment_fragment,container,false);
        addView();
        return view;
    }

    private void addView() {

    }

    @Override
    public void onClick(View v) {

    }
}
