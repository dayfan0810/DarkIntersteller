package cn.intersteller.darkintersteller.innerfragment.secondinnerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.intersteller.darkintersteller.R;


public class NewsFragment extends Fragment implements View.OnClickListener {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
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
