package com.f22labs.foodzone.parser;

import com.f22labs.foodzone.objects.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Category> getCategories(JSONObject jsonObject) throws JSONException {
        List<Category> categoryList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("categories");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            Category category = new Category(jsonobject.getInt("id"),jsonobject.getString("name"),jsonobject.getString("catAlias"),jsonobject.getString("categoryIcon"));
            categoryList.add(category);
        }
        return categoryList;
    }




}
