package com.f22labs.foodzone.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.foodzone.R;
import com.f22labs.foodzone.activities.MainActivity;
import com.f22labs.foodzone.objects.Order;
import com.f22labs.foodzone.objects.Restaurant;
import com.f22labs.foodzone.objects.User;
import com.f22labs.foodzone.utils.Search_adapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragment extends BaseFragment{


    ArrayList<Order> list = new ArrayList<>();
    @BindView(R.id.my_account)
    TextView my_account;
    @BindView(R.id.pastOrderList)
    ListView listView;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_phone)
    TextView user_phone;
    @BindView(R.id.user_email)
    TextView user_email;
    int fragCount;
    User user;
    public static ProfileFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        //( (MainActivity)getActivity()).updateToolbarTitle("Profile");
        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }

        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragmentNavigation != null) {
                    mFragmentNavigation.pushFragment(MyAccountFragment.newInstance(fragCount + 1));


                }
            }
        });

        getUserDetails("1");
        return view;
    }

    private void pastOrders(final String UserId) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/view-user-orders.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    {
                        //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        JSONArray jsonElements = jsonObject.getJSONArray("restaurant");
                        for(int i=0; i < jsonElements.length();i++)
                        {
                            Gson gson = new Gson();
                            Order order = gson.fromJson(jsonElements.getJSONObject(i).toString(), Order.class);
                            //Log.d("tag", "onResponse: "+restaurant.getPhoneNumber());
                            list.add(order);
                        }
                        //listView.setAdapter(new Search_adapter(getActivity(),list));
                    }
                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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
                postMap.put("cat_restro", UserId);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }



    private void getUserDetails(final String UserId) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/user-details.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    {
                        //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        JSONArray jsonElements = jsonObject.getJSONArray("user_info");
                            Gson gson = new Gson();
                            user = gson.fromJson(jsonElements.getJSONObject(0).toString(), User.class);
                            user_name.setText(user.getUserName());
                            user_email.setText(user.getEmail());
                            user_phone.setText(user.getPhone_number());
                        //listView.setAdapter(new Search_adapter(getActivity(),list));
                    }
                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
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
                postMap.put("userId", UserId);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
