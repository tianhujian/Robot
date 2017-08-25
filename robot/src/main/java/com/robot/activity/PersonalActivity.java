package com.robot.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.maxb.base.BaseActivity;
import com.robot.APP;
import com.robot.R;
import com.robot.img.FastBlurUtil;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class PersonalActivity extends BaseActivity {
    private ImageView back,gsimg;
    private RelativeLayout pac;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_personal);
        pac = findView(R.id.rl_pac);
        back = findView(R.id.back);
        gsimg = findView(R.id.iv_gsimg);
        Resources res = getResources();
        Bitmap scaledBitmap = BitmapFactory.decodeResource(res, R.mipmap.head);

        //        scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
        Bitmap blurBitmap = FastBlurUtil.toBlur(scaledBitmap, 2);
        gsimg.setScaleType(ImageView.ScaleType.FIT_XY);
        gsimg.setImageBitmap(blurBitmap);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int scaleRatio = 0;
//                //                        下面的这个方法必须在子线程中执行
//                final Bitmap blurBitmap2 = FastBlurUtil.GetUrlBitmap(url, 2);
//
//                //                        刷新ui必须在主线程中执行
//                APP.runOnUIThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        gsimg.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                        gsimg.setImageBitmap(blurBitmap2);
//                    }
//                });
//            }
//        }).start();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        setOnclick(pac);
        setOnclick(back);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.rl_pac:
                openActivity(PACActivity.class);
                break;
        }
    }
}
