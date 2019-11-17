package cn.intersteller.darkintersteller.fragment.innerfragment.thirdinnerfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cn.intersteller.darkintersteller.R;

public class HeapSortFragment extends Fragment {
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("deng","HeapSortFragment");
        View v = inflater.inflate(R.layout.cloudpanfragment, container, false);
        return v;
    }
}
