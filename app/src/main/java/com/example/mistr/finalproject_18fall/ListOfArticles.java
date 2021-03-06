/**
 *
 *this activity is  used to display saved articles by the user
 * @author harsh mistry
 */



package com.example.mistr.finalproject_18fall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListOfArticles extends Activity{
    ListView view;
    ArrayList<NewsInformation> titles = new ArrayList<>();
    TextView headLines;
    NewsAdapter ns;

    /**
     * method that add headlines, description and link
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_articles);
        Intent in = getIntent();
        view = findViewById(R.id.listsaved);
        NewsInformation n = new NewsInformation();
        n.setHeadline(in.getStringExtra("head"));
        n.setDescription(in.getStringExtra("description"));
        n.setLink(in.getStringExtra("link"));
        titles.add(n);
        view.setAdapter(ns);


    }

    /**
     *to shows news from the list
     */
    private class NewsAdapter extends ArrayAdapter<NewsInformation> {

        public NewsAdapter(Context ctx) {
            super(ctx, 0);
        }

        /**
         * to read titles from arraylist
         * @return
         */
        public int getCount(){
            return titles.size();
        }

        /**
         * to display the news titles saved into the arraylist
         * @param position
         * @return
         */
        public NewsInformation getItem(int position){
            return titles.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ListOfArticles.this.getLayoutInflater();
            View result = null;
            result = inflater.inflate(R.layout.display_saved_info, null);

            headLines = (TextView)result.findViewById(R.id.listOfSaved);
            headLines.setText(getItem(position).getHeadline()  ); // get the string at position
            return result;
        }

    }
}
