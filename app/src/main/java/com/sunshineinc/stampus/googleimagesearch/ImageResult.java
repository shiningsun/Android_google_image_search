package com.sunshineinc.stampus.googleimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by CasualHero on 3/18/2015.
 */
public class ImageResult {

    private String imageUrl;
    private int height;
    private int width;
    private String tbUrl;
    private String title;

    public ImageResult(JSONObject json){
        try {
            this.imageUrl = json.getString("url");
            this.tbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
            this.height =json.getInt("height");
            this.width = json.getInt("width");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJSONArray (JSONArray jsonArray){
        ArrayList<ImageResult> results = new ArrayList<ImageResult>();
        for(int i=0; i<jsonArray.length(); i++) {
            try {
                results.add(new ImageResult(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
