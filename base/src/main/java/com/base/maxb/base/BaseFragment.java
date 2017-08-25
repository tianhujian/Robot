package com.base.maxb.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.base.R;


/**
 * Created by Administrator on 2016/8/2.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private View view;
    public Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = initView(inflater);
        setStatusBarColor(R.color.white_0);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public View getView() {
        return view;
    }

    /**
     * 初始化view
     *
     * @param inflater 界面打气桶
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 初始化数据
     */
    public abstract void initData();
    protected Dialog loadingDialog = null;

    public void showProgress(String strMsg) {
        if (null == loadingDialog) {
            loadingDialog = createProgressDialog(getActivity(), strMsg + "");
        }
        loadingDialog.show();
        loadingDialog.setCancelable(false);
    }

    public void showProgress() {
        if (null == loadingDialog) {
            loadingDialog = createProgressDialog(getActivity(), getString(R.string.loading_waiting));
        }
        loadingDialog.show();
        loadingDialog.setCancelable(false);
    }

    public void showError(String strMsg) {
    }

    public void dismissProgress() {
        if ((loadingDialog != null) && (loadingDialog.isShowing()))
            loadingDialog.dismiss();
        loadingDialog = null;
    }

    public void hideProgress() {
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
    protected  void setStatusBarColor(int id) {

        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(getActivity(),true);
            SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
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

}