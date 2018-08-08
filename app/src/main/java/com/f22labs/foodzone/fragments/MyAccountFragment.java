package com.f22labs.foodzone.fragments;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.f22labs.foodzone.R;
import com.f22labs.foodzone.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyAccountFragment extends BaseFragment{



    @BindView(R.id.edit_change_pass)
    TextView changepass;
    @BindView(R.id.edit_home)
    TextView edit_home;
    @BindView(R.id.edit_work)
    TextView edit_work;
    @BindView(R.id.edit_other)
    TextView edit_other;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static MyAccountFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);

        ButterKnife.bind(this, view);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.change_password);
                dialog.show();
            }
        });

        edit_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.change_home);
                dialog.show();
            }
        });
        edit_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.change_work);
                dialog.show();
            }
        });
        edit_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.change_other);
                dialog.show();
            }
        });


        return view;
    }



}
