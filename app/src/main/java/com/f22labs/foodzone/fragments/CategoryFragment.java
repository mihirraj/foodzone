package com.f22labs.foodzone.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f22labs.foodzone.R;
import com.f22labs.foodzone.activities.MainActivity;

import butterknife.ButterKnife;


public class CategoryFragment extends BaseFragment{




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categoryname, container, false);

        ButterKnife.bind(this, view);


        return view;
    }



}
