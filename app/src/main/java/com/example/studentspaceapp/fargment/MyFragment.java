package com.example.studentspaceapp.fargment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.studentspaceapp.R;
import com.example.studentspaceapp.activity.ChangeInfoActivity;
import com.example.studentspaceapp.activity.LoginActivity;

import cn.bmob.v3.BmobUser;

public class MyFragment extends Fragment {
    CardView cardView;
    Button btn_exit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        cardView = view.findViewById(R.id.cv_change_info);
        btn_exit = view.findViewById(R.id.btn_exit);
        cardView.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
            startActivity(intent);
        });

        btn_exit.setOnClickListener(v->{
            BmobUser.logOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
