package com.sunshineinc.stampus.googleimagesearch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//URL url = new URL("https://ajax.googleapis.com/ajax/services/search/images?" + "v=1.0&q=barack%20obama&userip=INSERT-USER-IP");
public class ImageGridActivity extends ActionBarActivity {
    private EditText etQuery;
    private GridView gvGrid;
    AsyncHttpClient client;
    ArrayList<ImageResult> image;
    ImageGridAdapter aImageResut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);
        setupViews();
        image = new ArrayList<ImageResult>();
        aImageResut = new ImageGridAdapter(this, image);
        gvGrid.setAdapter(aImageResut);
    }

    private void setupViews(){
        etQuery=(EditText)findViewById(R.id.etQuery);
        gvGrid=(GridView)findViewById(R.id.gvGrid);
        gvGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ImageGridActivity.this, ImageDisplayActivity.class);
                ImageResult result = image.get(position);
                i.putExtra("url", result.getImageUrl());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onImageSearch(View view){
        String query = etQuery.getText().toString();
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        client = new AsyncHttpClient();
        String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="+query+"rsz=8";
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray array = null;
                try{
                    array = response.getJSONObject("responseData").getJSONArray("results");
                    image.clear();
                    aImageResut.addAll(ImageResult.fromJSONArray(array));
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Log.i("debug", image.toString());
    }
}
