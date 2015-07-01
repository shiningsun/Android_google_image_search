package com.sunshineinc.stampus.googleimagesearch;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by CasualHero on 3/18/2015.
 */
public class ImageGridAdapter extends ArrayAdapter<ImageResult> {


    public ImageGridAdapter(Context context, List<ImageResult> images) {
        super(context, R.layout.image_grid_item, images);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        ImageResult image =getItem(position);
        if(view ==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.image_grid_item, parent, false);
        }
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvImageTitle);
        ivImage.setImageResource(0);
        tvTitle.setText(Html.fromHtml(image.getTitle()));
        Picasso.with(getContext()).load(image.getTbUrl()).into(ivImage);
        return view;
    }
}
