package com.example.ubom.newslines.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ubom.newslines.R;

import java.util.ArrayList;

/**
 * Created by ubom on 7/24/2018.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    //a class that returns context from arraylist
    public NewsAdapter(Context context) {
        super(context, -1, new ArrayList<News>());
    }

    //a class that gets 3 parameters, then inflates list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
       //finds textview by resource named title then converts view
        TextView title = (TextView) convertView.findViewById(R.id.title);
        //finds textview by resource named author then converts view
        TextView author = (TextView) convertView.findViewById(R.id.author);
        //finds textview by resource named date then converts view
        TextView date = (TextView) convertView.findViewById(R.id.date);
        //finds textview by resource named section then converts view
        TextView section = (TextView) convertView.findViewById(R.id.section);

        //gets array "news" sets textviews
        News currentNews = getItem(position);
        title.setText(currentNews.getTitle());
        author.setText(currentNews.getAuthor());
        date.setText(currentNews.getDate());
        section.setText(currentNews.getSection());

        return convertView;
    }
}
