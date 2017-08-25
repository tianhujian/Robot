package com.robot.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.base.maxb.base.BaseActivity;
import com.base.maxb.base.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

import com.robot.R;
import com.robot.fragment.CenterFragment;
import com.robot.fragment.HomePageFragment;
import com.robot.fragment.StoreFragment;
import com.robot.fragment.TaskHallFragment;

public class MainActivity extends BaseActivity {
    private RadioGroup group;
    private RadioButton homePage,Store,taskHall,Center;
    private HomePageFragment homePageFragment;
    private StoreFragment storeFragment;
    private TaskHallFragment taskHallFragment;
    private CenterFragment centerFragment;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        setStatusBarColor(R.color.white);
        group = findView(R.id.group);
        homePage = findView(R.id.rb_homepage);
        Store = findView(R.id.rb_store);
        taskHall = findView(R.id.rb_task_hall);
        Center = findView(R.id.rb_center);
        group.check(R.id.rb_homepage);

        homePageFragment = new HomePageFragment();
        storeFragment = new StoreFragment();
        taskHallFragment = new TaskHallFragment();
        centerFragment = new CenterFragment();

        switchFragment(homePageFragment);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        setOnclick(homePage);
        setOnclick(Store);
        setOnclick(taskHall);
        setOnclick(Center);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.rb_homepage:
                switchFragment(homePageFragment);
                break;
            case R.id.rb_store:
                switchFragment(storeFragment);
                break;
            case R.id.rb_task_hall:
                switchFragment(taskHallFragment);
                break;
            case R.id.rb_center:
                switchFragment(centerFragment);
                break;
        }
    }

    public void switchFragment(BaseFragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment)
                .commit();
    }

    /**
     * 返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByClickTwice(); //调用双击退出函数
        }

        return false;
    }

    /**
     * 双击退出程序
     */
    private static Boolean isExit = false;

    private void exitByClickTwice() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序喔", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 3000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
