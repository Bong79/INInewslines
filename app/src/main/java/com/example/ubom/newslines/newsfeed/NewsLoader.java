package com.example.ubom.newslines.newsfeed;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by ubom on 7/24/18.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    /**
     * Constructor for a new {@link NewsLoader}.
     * @param context of the activity
     */

    public NewsLoader(Context context) {
        super(context);
    }

    //override methods
    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        List<News> listOfNews = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            listOfNews = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
        }
        return listOfNews;
    }
}
