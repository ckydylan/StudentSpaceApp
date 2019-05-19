package com.example.studentspaceapp.fargment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.studentspaceapp.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    List<Integer> imageUrlData;
    List<String> contentData;
    Banner myBanner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        myBanner = view.findViewById(R.id.banner);
        initBanner();
        return view;
    }
    private void initBanner() {
        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();
        imageUrlData.add(R.drawable.temppic);
        imageUrlData.add(R.drawable.white_radius);
        imageUrlData.add(R.drawable.temppic);
        imageUrlData.add(R.drawable.white_radius);
        contentData.add("我是雷神");
        contentData.add("我是小美");
        contentData.add("我是雷神二号");
        contentData.add("我是小美二号");
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        myBanner.setImageLoader(new MyLoader());
        myBanner.setImages(imageUrlData);
        myBanner.setBannerTitles(contentData);
        myBanner.setBannerAnimation(Transformer.Default);
        //切换频率
        myBanner.setDelayTime(2000);
        //自动启动
        myBanner.isAutoPlay(true);
        //位置设置
        myBanner.setIndicatorGravity(BannerConfig.CENTER);
        //开始运行
        myBanner.start();
    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }

}