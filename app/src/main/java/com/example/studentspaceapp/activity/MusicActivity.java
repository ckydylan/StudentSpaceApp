package com.example.studentspaceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.studentspaceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicActivity extends Activity {
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ListView lv_show = findViewById(R.id.lv_show);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                , getData()
                , R.layout.list_show_music
                , new String[]{"image", "title", "author","rightImg"}
                , new int[]{R.id.my_images, R.id.tv_titles, R.id.tv_authors,R.id.img_right});
        lv_show.setAdapter(simpleAdapter);
        lv_show.setOnItemClickListener((parent, view, position, id)->{
            startActivity(new Intent(MusicActivity.this,ShowMusicActivity.class));
        });
    }

    private ArrayList<Map<String, Object>> getData() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        int[] image = {R.raw.ic_music1,R.raw.ic_music2,R.raw.ic_music3,R.raw.ic_music4,R.raw.ic_music5,R.raw.ic_music6,R.raw.ic_music7,R.raw.ic_music8};
        String[] title = {"陪我长大","身骑白马","倒数","某某","爱殇","寻","Nightingale","Cornfield Chase"};
        String[] author = {"火箭少女101段奥娟","徐佳莹","G.E.M.邓紫棋","孟凡明","小时/龚骏","三亩地","YANI","Hans Zimmer"};
        int rightImg = R.raw.music_img_right;
        for (int i = 0; i < image.length; i++) {
            map = new HashMap<>();
            map.put("image", image[i]);
            map.put("title",title[i]);
            map.put("author",author[i]);
            map.put("rightImg",rightImg);
            list.add(map);
        }
        return list;
    }
}
