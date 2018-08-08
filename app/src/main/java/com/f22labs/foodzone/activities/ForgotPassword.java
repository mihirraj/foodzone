package com.f22labs.foodzone.activities;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.f22labs.foodzone.R;

import butterknife.BindInt;
import butterknife.BindView;

public class ForgotPassword extends AppCompatActivity {


    @BindView(R.id.send_opt)
    ImageView send_opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        send_opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_opt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomSheetDialog dialog = new BottomSheetDialog(ForgotPassword.this);
                        dialog.setContentView(R.layout.dialog_otp);
                        dialog.show();
                    }
                });
            }
        });

    }
}
