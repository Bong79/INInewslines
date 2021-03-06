package com.example.ubom.newslines.newsfeed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ubom.newslines.R;

import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>>, SwipeRefreshLayout.OnRefreshListener {

    private NewsAdapter adapter;
    private static int LOADER_ID = 0;
    SwipeRefreshLayout swipe;
    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //sets view for resource swiperefresh
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

       //swipe actions
         swipe.setOnRefreshListener(this);

         //sets color for swipe
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        //finds view by id named list view
        ListView listView = (ListView) findViewById(android.R.id.list);
        TextView emptyText = (TextView)findViewById(android.R.id.empty);
        listView.setEmptyView(emptyText);

        if (isNetworkConnected()) {
            //onRefresh();
            emptyText.setVisibility(View.GONE);
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            emptyText.setVisibility(View.VISIBLE);

        }

        //a new NewsAdapter called adapter
        adapter = new NewsAdapter(this);

        //sets behavior of list view adapter
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = adapter.getItem(i);
                String url = news.url;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    public boolean isNetworkConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity == null) {
            return false;
        }

        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }


    //override methods
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        swipe.setRefreshing(false);
        if (data != null) {
            adapter.setNotifyOnChange(false);
            adapter.clear();
            adapter.setNotifyOnChange(true);
            adapter.addAll(data);
        }
    }



    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
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
