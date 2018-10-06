//package com.example.ubom.newslines.newsfeed;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Bundle;
//import android.preference.Preference;
//import android.preference.PreferenceManager;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.ubom.newslines.R;
//
//import java.net.URI;
//import java.net.URL;
//import java.util.List;
//
//
//public class MainActivity
//        extends AppCompatActivity
//        implements LoaderManager.LoaderCallbacks<List<News>>,
//        SwipeRefreshLayout.OnRefreshListener {
////        SharedPreferences.OnSharedPreferenceChangeListener {
//
//    private NewsAdapter adapter;
//    private static int LOADER_ID = 0;
//    SwipeRefreshLayout swipe;
//    /** TextView that is displayed when the list is empty */
//    private TextView mEmptyStateTextView;
//    public static final String LOG_TAG = MainActivity.class.getSimpleName();
//
//
//    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
////        prefs.registerOnSharedPreferenceChangeListener(this);
//
//
//
//       //sets view for resource swiperefresh
//        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
//
//       //swipe actions
//         swipe.setOnRefreshListener(this);
//
//         //sets color for swipe
//        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
//
//        //finds view by id named list view
//        ListView listView = (ListView) findViewById(android.R.id.list);
//        TextView emptyText = (TextView)findViewById(android.R.id.empty);
//        listView.setEmptyView(emptyText);
//
//        if (isNetworkConnected()) {
//            emptyText.setVisibility(View.GONE);
//            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
//        } else {
//            emptyText.setVisibility(View.VISIBLE);
//
//        }
//
//        //a new NewsAdapter called adapter
//        adapter = new NewsAdapter(this);
//
//        //sets behavior of list view adapter
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()) {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                News news = adapter.getItem(i);
//                String url = news.url;
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
//            }
//        });
//        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
//    }
//
////    @Override
////    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
////        if (key.equals(getString(R.string.settings_min_amount_news_key)) ||
////                key.equals(getString(R.string.settings_order_by_key))){
////            // Clear the ListView as a new query will be kicked off
////            adapter.clear();
////
////            // Hide the empty state text view as the loading indicator will be displayed
////            mEmptyStateTextView.setVisibility(View.GONE);
////
////            // Show the loading indicator while new data is being fetched
//////            View loadingIndicator = findViewById(R.id.loading_indicator);
//////            loadingIndicator.setVisibility(View.VISIBLE);
////
////            // Restart the loader to requery the USGS as the query settings have been updated
////            getLoaderManager().restartLoader(LOADER_ID, null, this);
////        }
////    }
//
////checks network connection
//    public boolean isNetworkConnected() {
//        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (connectivity == null) {
//            return false;
//        }
//
//        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
//        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//
//    }
//
//
//    @Override
//    // onCreateLoader instantiates and returns a new Loader for the given ID
//    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
//
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//
//            String stringUrl = QueryUtils.createStringUrl();
//
//        String minAmountNews = sharedPrefs.getString(
//                getString(R.string.settings_min_amount_news_key),
//                getString(R.string.settings_min_amount_news_default));
//
//        String orderBy = sharedPrefs.getString(
//                getString(R.string.settings_order_by_key),
//                getString(R.string.settings_order_by_default)
//        );
//
//        // parse breaks apart the URI string that's passed into its parameter
//        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
//
//        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
//        Uri.Builder uriBuilder = baseUri.buildUpon();
//
//        // Append query parameter and its value. For example, the `format=geojson`
//        Log.d(LOG_TAG, "onCreateLoader() returned: " + uriBuilder);
//
//        uriBuilder.appendQueryParameter("format", "json");
//        uriBuilder.appendQueryParameter("limit", "10");
//        uriBuilder.appendQueryParameter("page-size", minAmountNews);
//        uriBuilder.appendQueryParameter("orderby", orderBy);
//
//        Log.d(LOG_TAG, "NewsLoader stringUrl: " + uriBuilder);
//        return new NewsLoader(this, stringUrl);
//
////        return new EarthquakeLoader(this, uriBuilder.toString());
//
////        the reason that's makes your list didn't affect by Settings Activity preferences changed values that
//// you're passing stringUrl which is calledQueryUtils.createStringUrl() and this method returns static Uri without
//// consider of updated values and to solve this issue you can make pass Settings Activity values as a parameter to
//// QueryUtils.createStringUrl() to return dynamic URI or simply adjust this uriBuilder and passing uriBuilder.toString()
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
//        swipe.setRefreshing(false);
//        if (data != null) {
//            adapter.setNotifyOnChange(false);
//            adapter.clear();
//            adapter.setNotifyOnChange(true);
//            adapter.addAll(data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<News>> loader) {
//
//    }
//
//    @Override
//    public void onRefresh() {
//        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
//    }
//
//
//    @Override
//    // This method initialize the contents of the Activity's options menu.
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the Options Menu we specified in XML
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//@Override
//public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//        Intent settingsIntent = new Intent(this, SettingsActivity.class);
//        startActivity(settingsIntent);
//        return true;
//        }
//        return super.onOptionsItemSelected(item);
//        }
//}


package com.example.ubom.newslines.newsfeed;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.support.v4.content.AsyncTaskLoader;


import com.example.ubom.newslines.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderCallbacks<List<News>>,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String LOG_TAG = MainActivity.class.getName();

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search";
//                    "?q=nollywood&api-key=896df9d7-548f-41eb-b4a0-475707743f15";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static int LOADER_ID = 1;

    /**
     * Adapter for the list of earthquakes
     */
    private NewsAdapter adapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds view by id named list view
        ListView listView = (ListView) findViewById(android.R.id.list);
        mEmptyStateTextView = (TextView) findViewById(android.R.id.empty);
        listView.setEmptyView(mEmptyStateTextView);

        // a new NewsAdapter called adapter: takes an empty list news (as input)
        adapter = new NewsAdapter(this);

        //sets behavior of list view adapter
        listView.setAdapter(adapter);

        // Obtain a reference to the SharedPreferences file for this app
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // And register to be notified of preference changes
        // So we know when the user has adjusted the query settings
        prefs.registerOnSharedPreferenceChangeListener(this);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Find the current earthquake that was clicked on
//                News news = adapter.getItem(i);

                News currentNews = adapter.getItem(i);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());
                // Create a new intent to view the earthquake URI
                Intent intent = new Intent(Intent.ACTION_VIEW);
                // Send the intent to launch a new activity
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(LOADER_ID, null, this);
        Log.d(LOG_TAG, "onCreate: loader33 INITIALIZED");


        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivity =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            // Show the loading indicator while new data is being fetched
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if (key.equals(getString(R.string.settings_min_amount_news_key)) ||
                        key.equals(getString(R.string.settings_order_by_key))) {
                    // Clear the ListView as a new query will be kicked off
                    adapter.clear();

                    // Hide the empty state text view as the loading indicator will be displayed
                    mEmptyStateTextView.setVisibility(View.GONE);

                    // Show the loading indicator while new data is being fetched
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.VISIBLE);

                    // Restart the loader to requery the USGS as the query settings have been updated
                    getLoaderManager().restartLoader(LOADER_ID, null, this);
                }
            }

            @Override
            // onCreateLoader instantiates and returns a new Loader for the given ID
            public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

//            String stringUrl = QueryUtils.createStringUrl();

                String minAmountNews = sharedPrefs.getString(
                        getString(R.string.settings_min_amount_news_key),
                        getString(R.string.settings_min_amount_news_default));

                String orderBy = sharedPrefs.getString(
                        getString(R.string.settings_order_by_key),
                        getString(R.string.settings_order_by_default)
                );

                // parse breaks apart the URI string that's passed into its parameter
                Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

                // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
                Uri.Builder uriBuilder = baseUri.buildUpon();

                // Append query parameter and its value. For example, the `format=geojson`
//                Log.d(LOG_TAG, "onCreateLoader() returned: " + uriBuilder);

                uriBuilder.appendQueryParameter("api-key", "896df9d7-548f-41eb-b4a0-475707743f15");
                uriBuilder.appendQueryParameter("format", "json");
                uriBuilder.appendQueryParameter("limit", "10");
                uriBuilder.appendQueryParameter("page-size", minAmountNews);
                uriBuilder.appendQueryParameter("orderby", orderBy);

                Log.d(LOG_TAG, "NewsLoader stringUrl: " + uriBuilder);
                return new NewsLoader(this, uriBuilder.toString());

            }

            @Override
            public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
                // Hide loading indicator because the data has been loaded
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);

                // Set empty state text to display "No earthquakes found."
                mEmptyStateTextView.setText(R.string.empty_list);

                // Clear the adapter of previous earthquake data
//                adapter.clear();

                // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
                // data set. This will trigger the ListView to update.
                if (data != null && !data.isEmpty()) {
                    adapter.addAll(data);
//                                updateUi(data);
                }

            }

            @Override
            public void onLoaderReset(Loader<List<News>> loader) {
                // Loader reset, so we can clear out our existing data.
                adapter.clear();
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    Intent settingsIntent = new Intent(this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
        }



