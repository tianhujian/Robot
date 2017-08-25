package com.robot.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.maxb.base.BaseFragment;
import com.robot.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class HomePageFragment extends BaseFragment {
    private Banner banner;
    private List<String> bimg;
    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_homepage, null);
        setStatusBarColor(R.color.main_text_check);
        banner = (Banner) view.findViewById(R.id.banner);
        bimg = new ArrayList<>();

//        banner.setImages(bimg)
//                .setImageLoader(new GlideImageLoader())
//                .start();
        return view;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

//            Glide.with(context).load(path).into(imageView);

        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
//        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
//        banner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
//        banner.setImages(bimg)
//                .setImageLoader(new GlideImageLoader())
//                .setDelayTime(3500)
//                .start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
