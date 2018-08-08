package com.f22labs.foodzone.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.f22labs.foodzone.R;
import com.f22labs.foodzone.objects.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Search_adapter extends BaseAdapter {
    private ArrayList<Restaurant> list;
    private Context context;
    private View view;
    Constants constants = new Constants();
    private Holder holder;

    public Search_adapter(Context context, ArrayList<Restaurant> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.search_row_item, parent, false);

        holder = new Holder();
        holder.ft_image = (ImageView) view.findViewById(R.id.ft_image);
        holder.ft_name = (TextView) view.findViewById(R.id.ft_name);
        holder.ft_cat = (TextView) view.findViewById(R.id.ft_cat);
        holder.ft_minprice = (TextView) view.findViewById(R.id.ft_minprice);
        holder.ft_locality = (TextView) view.findViewById(R.id.ft_locality);
        holder.ft_rating = (TextView) view.findViewById(R.id.ft_rating);
        holder.ft_reviews = (TextView) view.findViewById(R.id.ft_reviews);

        holder.ft_name.setText(list.get(position).getRestroName());
        holder.ft_cat.setText(list.get(position).getCategoryName());
        holder.ft_minprice.setText(list.get(position).getMinOrder());
        holder.ft_locality.setText(list.get(position).getLocality()+", "+list.get(position).getCity());
        holder.ft_rating.setText(list.get(position).getRating());
        holder.ft_reviews.setText(list.get(position).getReviews());

        Picasso.get()
                .load(constants.getImage_baseurl()+list.get(position).getLogo())
                .transform(new RoundedTransformation(50, 4))
                .into(holder.ft_image);

        Log.d("", "getView: "+list.get(position).getMinOrder());
        return view;
    }

    public class Holder {
        ImageView ft_image;
        TextView ft_name;
        TextView ft_cat;
        TextView ft_minprice;
        TextView ft_locality;
        TextView ft_rating;
        TextView ft_reviews;
    }

}