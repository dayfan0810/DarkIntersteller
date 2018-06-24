package cn.intersteller.darkintersteller.innerfragment.firstinnerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.intersteller.darkintersteller.R;

/**
 * Created by Limuyang on 2016/7/7.
 */
public class AnchorFragment extends Fragment {
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v != null) {
            return v;
        }
        View v = inflater.inflate(R.layout.anchorfragment, container, false);
        return v;
    }
}
