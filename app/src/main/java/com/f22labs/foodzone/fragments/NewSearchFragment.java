package com.f22labs.foodzone.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.foodzone.R;
import com.f22labs.foodzone.activities.MainActivity;
import com.f22labs.foodzone.objects.Category;
import com.f22labs.foodzone.objects.Restaurant;
import com.f22labs.foodzone.utils.Constants;
import com.f22labs.foodzone.utils.Search_adapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewSearchFragment extends BaseFragment {


    @BindView(R.id.btn_click_me)
    Button btnClickMe;
    @BindView(R.id.food_cat)
    HorizontalScrollView horizontalScrollView;
    @BindView(R.id.image_container)
    LinearLayout linearLayout;
    @BindView(R.id.res_near)
    ListView res_near;
    int fragCount;
    ArrayList<Category> categories;
    ArrayList<Restaurant> restaurants;
    Constants constants = new Constants();

    public static NewSearchFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        NewSearchFragment fragment = new NewSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public NewSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ButterKnife.bind(this, view);
        //LinearLayout topLinearLayout = new LinearLayout(getActivity());
        //topLinearLayout.setOrientation(LinearLayout.HORIZONTAL);




        //horizontalScrollView.addView(topLinearLayout);
        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }
        getFoodByCat();
        getRestaurantsNearYou("navrangpura","ahmedabad");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFragmentNavigation != null) {
                    mFragmentNavigation.pushFragment(NewSearchFragment.newInstance(fragCount + 1));

                }
            }
        });


        ( (MainActivity)getActivity()).updateToolbarTitle((fragCount == 0) ? "Home" : "Sub Home "+fragCount);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getFoodByCat() {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/categories.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    {
                        categories = new ArrayList<>();
                        JSONArray jsonElements = jsonObject.getJSONArray("categories");
                        for(int i=0; i < jsonElements.length();i++)
                        {
                            Gson gson = new Gson();
                            Category category = gson.fromJson(jsonElements.getJSONObject(i).toString(), Category.class);
                            ImageView imageView = new ImageView(getContext());
                            imageView.setId(category.getId());
                            imageView.setPadding(10, 5, 10, 5);

                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            Picasso.get()
                                    .load(constants.getImage_baseurl()+category.getCategoryIcon())
                                    .into(imageView);
                            linearLayout.addView(imageView);
                            Log.d("tag", "onResponse: "+category.getCategoryIcon());
                            categories.add(category);
                        }


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
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void getRestaurantsNearYou(final String locality, final String city) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/search-by-locality.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Volley Result", "" + response); //the response contains the result from the server, a json string or any other object returned by your server
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("success")==1)
                    {
                        restaurants = new ArrayList<>();
                        //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                        JSONArray jsonElements = jsonObject.getJSONArray("restaurant");
                        for(int i=0; i < jsonElements.length();i++)
                        {
                            Gson gson = new Gson();
                            Restaurant restaurant = gson.fromJson(jsonElements.getJSONObject(i).toString(), Restaurant.class);
                            Log.d("tag", "onResponse: "+restaurant.getPhoneNumber());
                            restaurants.add(restaurant);
                        }
                        res_near.setAdapter(new Search_adapter(getActivity(),restaurants));
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
                postMap.put("locality", locality);
                postMap.put("city", city);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
}
