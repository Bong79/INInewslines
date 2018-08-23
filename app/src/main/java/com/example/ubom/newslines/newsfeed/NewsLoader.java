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
     *
     * @param context of the activity
     */

    private String url;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<News> loadInBackground() {
        if (this.url == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        List<News> INInewslines = QueryUtils.makeHttpRequest(this.url);
        Log.d("myTag", "This is my message");

        return INInewslines;
    }
}