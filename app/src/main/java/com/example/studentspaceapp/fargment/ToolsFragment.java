package com.example.studentspaceapp.fargment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.activity.CalculatorActivity;
import com.example.studentspaceapp.activity.ClassManageActivity;
import com.example.studentspaceapp.activity.MusicActivity;

public class ToolsFragment extends Fragment {
    CardView cv_into_tool;
    CardView cv_into_class_manage;
    CardView cv_into_music;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools,container,false);
        cv_into_tool = view.findViewById(R.id.cv_into_tool);
        cv_into_class_manage = view.findViewById(R.id.cv_into_class_manage);
        cv_into_music = view.findViewById(R.id.cv_into_music);

        cv_into_tool.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), CalculatorActivity.class);
            startActivity(intent);
        });

        cv_into_class_manage.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ClassManageActivity.class);
            startActivity(intent);
        });

        cv_into_music.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), MusicActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
