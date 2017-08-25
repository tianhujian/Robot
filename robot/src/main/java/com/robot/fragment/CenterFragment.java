package com.robot.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.base.maxb.base.BaseFragment;
import com.robot.R;
import com.robot.activity.PACActivity;
import com.robot.activity.PersonalActivity;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class CenterFragment extends BaseFragment {
    private ImageView center;
    private Intent intent;
    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_center, null);
        setStatusBarColor(R.color.main_text_check);
        center = (ImageView) view.findViewById(R.id.iv_head);
        center.setOnClickListener(this);
        intent = new Intent();
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_head:
                intent.setClass(getActivity(),PersonalActivity.class);
                startActivity(intent);
                break;
        }

    }
}
