package com.base.maxb.base;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.base.R;


/**
 * Created by Administrator on 2016/8/2.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {
    public String TAG = BaseActivity.class.getSimpleName();
    private static final int MSG_SET_ALIAS = 1001;
    private SparseArray<View> mViews;

    public abstract void initViews();
    public abstract void initData();
    public abstract void initListener();
    public abstract void processClick(View v);

    public void onClick(View v){
        processClick(v);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        this.TAG = getClass().getSimpleName();
        setStatusBarColor(R.color.white_0);
        initViews();
//        initView();
        initData();
        initListener();
    }

    public BaseActivity getContext() {
        return this;
    }

//    public abstract void initView();


    protected  void setStatusBarColor(int id) {

        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(this,true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(id);//通知栏所需颜色

        }
    }
    /**
     * 设置透明状态栏
     *
     * @param activity 需要设置的Activity
     * @param on       设置开关
     */
    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);

    }
    /***
     * 通过ID找到view
     *
     */
    public <E extends View> E findView(int viewId){
        E view = (E) mViews.get(viewId);
        if (view == null){
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    protected Dialog loadingDialog = null;

    public void showProgress() {
        if (null == loadingDialog) {
            loadingDialog = createProgressDialog(this, getString(R.string.loading_waiting));
        }
        loadingDialog.show();
        loadingDialog.setCancelable(false);
    }

    public static Dialog createProgressDialog(Context context, String hint) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_progress, null);
        TextView msgView = (TextView) view.findViewById(R.id.tv_msg);

        if (!TextUtils.isEmpty(hint)) {
            msgView.setVisibility(View.VISIBLE);
            msgView.setText(hint);
        }
//        else msgView.setVisibility(View.GONE);
        Dialog loading = new Dialog(context, R.style.CustomProgressDialog);
        WindowManager.LayoutParams lp = loading.getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        loading.setContentView(view, lp);
        return loading;

    }
    public void dismissProgress() {
        if ((loadingDialog != null) && (loadingDialog.isShowing()))
            loadingDialog.dismiss();
        loadingDialog = null;
    }


    /**
     * view设置点击事件
     *
     */

    public <E extends View> void  setOnclick (E View){
        View.setOnClickListener(this);
    }
    /**
     * 直接打开Activity
     *
     * @param clazz class文件
     */
    public void openActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    public void openActivity(Class clazz, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
