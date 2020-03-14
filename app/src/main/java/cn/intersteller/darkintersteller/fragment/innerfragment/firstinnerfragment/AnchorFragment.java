package cn.intersteller.darkintersteller.fragment.innerfragment.firstinnerfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.intersteller.darkintersteller.R;

public class AnchorFragment extends Fragment {
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anchorfragment, container, false);
    }
}
