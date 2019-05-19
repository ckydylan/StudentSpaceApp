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
import com.example.studentspaceapp.activity.ChangeInfoActivity;

public class MyFragment extends Fragment {
    CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        cardView = view.findViewById(R.id.cv_change_info);
        cardView.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
