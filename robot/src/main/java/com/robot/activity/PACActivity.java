package com.robot.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.maxb.base.BaseActivity;
import com.robot.R;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class PACActivity extends BaseActivity {
    private ImageView back;
    private TextView title;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_pac);
        setStatusBarColor(R.color.main_text_check);
        back = findView(R.id.go_backs);
        title = findView(R.id.tob_titles);
        title.setText("个人认证");


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        setOnclick(back);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.go_backs:
                finish();
                break;
        }

    }
}
