package com.f22labs.foodzone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.f22labs.foodzone.R;
import com.f22labs.foodzone.activities.MainActivity;
import com.f22labs.foodzone.objects.Restaurant;
import com.f22labs.foodzone.utils.Search_adapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchFragment extends BaseFragment{



    @BindView(R.id.search_adapter)
    ListView listView;
    final ArrayList<Restaurant> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Search");
        getRestaurants("food");
        return view;
    }

    private void getRestaurants(final String name) {

        String requestUrl = "http://testbud.in/foodzfun_bkup/api4.0_3.0_111/search-by-cat-restro-name.php";
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
                            Restaurant restaurant = gson.fromJson(jsonElements.getJSONObject(i).toString(), Restaurant.class);
                            Log.d("tag", "onResponse: "+restaurant.getPhoneNumber());
                            list.add(restaurant);
                        }
                        listView.setAdapter(new Search_adapter(getActivity(),list));
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
                postMap.put("cat_restro", name);
                //..... Add as many key value pairs in the map as necessary for your request
                return postMap;
            }
        };
        //make the request to your server as indicated in your request url
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }


}
