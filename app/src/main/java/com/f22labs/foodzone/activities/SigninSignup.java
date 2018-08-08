package com.f22labs.foodzone.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.foodzone.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninSignup extends AppCompatActivity {


    @BindView(R.id.btn_signin)
    ImageView signin;
    @BindView(R.id.btn_signup)
    ImageView signup;
    @BindView(R.id.forgot_password)
    TextView forgot_password;

    Context context = SigninSignup.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
        ButterKnife.bind(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(SigninSignup.this);
                dialog.setContentView(R.layout.dialog_signin);
                final EditText phone_number = (EditText) dialog.findViewById(R.id.phone_number);
                final EditText password = (EditText) dialog.findViewById(R.id.password);
                final ImageView signin = (ImageView) dialog.findViewById(R.id.signin);
                signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSignIn(phone_number.getText().toString(),password.getText().toString());
                    }
                });
                dialog.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(SigninSignup.this);
                dialog.setContentView(R.layout.dialog_signup);
                final EditText name = (EditText) dialog.findViewById(R.id.signup_name);
                final EditText phone = (EditText) dialog.findViewById(R.id.signup_phone);
                final EditText password = (EditText) dialog.findViewById(R.id.signup_password);
                final EditText email = (EditText) dialog.findViewById(R.id.signup_email);
                final ImageView submit = (ImageView) dialog.findViewById(R.id.signup_submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendSignUp(name.getText().toString(),phone.getText().toString(),email.getText().toString(),password.getText().toString());
                    }
                });
                dialog.show();
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninSignup.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private void sendSignUp(final String name,final String phonenumber , final String email,final String password) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject res = new JSONObject(response);
                    if(res.getInt("success") == 1)
                    {
                        openOTPdialog(res.getString("userId"),res.getString("OTP"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mobileNo", phonenumber);
                postMap.put("name", name);
                postMap.put("email", email);
                postMap.put("password", password);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(SigninSignup.this).add(stringRequest);
    }

    private void sendSignIn(final String phonenumber, final String password) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/signin.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    {
                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SigninSignup.this, MainActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("mobileNo", phonenumber);
                postMap.put("password", password);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(SigninSignup.this).add(stringRequest);
    }

    public void openOTPdialog(final String userId, String otp)
    {
        BottomSheetDialog dialog = new BottomSheetDialog(SigninSignup.this);
        dialog.setContentView(R.layout.dialog_otp);
        final EditText edt_otp = (EditText) dialog.findViewById(R.id.edt_otp);
        final ImageView submit = (ImageView) dialog.findViewById(R.id.otp_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(userId,edt_otp.getText().toString());
            }
        });
        dialog.show();
    }
    private void sendOTP(final String userId, final String otp) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/register-otp.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject res = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),res.getString("message"),Toast.LENGTH_SHORT).show();
                    if(res.getInt("success") == 1)
                    {
                        //redirect to main Activity
                        Intent intent = new Intent(SigninSignup.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); //log the error resulting from the request for diagnosis/debugging

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("userId", userId);
                postMap.put("OTP", otp);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(SigninSignup.this).add(stringRequest);
    }

}




