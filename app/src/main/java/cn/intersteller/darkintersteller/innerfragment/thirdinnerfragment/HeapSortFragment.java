package cn.intersteller.darkintersteller.innerfragment.thirdinnerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.intersteller.darkintersteller.R;

public class HeapSortFragment extends Fragment {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("deng","HeapSortFragment");
        View v = inflater.inflate(R.layout.listfragment, container, false);
        return v;
    }
}
