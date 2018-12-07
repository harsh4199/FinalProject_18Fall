package com.example.mistr.finalproject_18fall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsArticle extends Activity {

    private final String Activity_Name = "CBCActivity";
    private final String Url = "https://www.cbc.ca/cmlink/rss-world";
    protected ListView newsList;
    protected ProgressBar progressBar;
    TextView headLines;
    ArrayList<NewsInformation> newsInformationArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
      //  progressBar.setVisibility(View.VISIBLE);
        newsAdapter = new NewsAdapter(this);
        new NewsQuaery().execute();
        newsList = findViewById(R.id.news);
        newsList.setAdapter(newsAdapter);
//        newsList.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            { Intent intent = new Intent(NewsArticle.class, NewsDescriptionAndLink.class);
//               intent.putExtra("description", newsList.getItemAtPosition())
//            }});






        }


    private class NewsAdapter extends ArrayAdapter<NewsInformation> {
        public NewsAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount(){
            return newsInformationArrayList.size();
        }
        public NewsInformation getItem(int position){
            return newsInformationArrayList.get(position);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = NewsArticle.this.getLayoutInflater();
            View result = null;
                result = inflater.inflate(R.layout.activity_news_details, null);

             headLines = (TextView)result.findViewById(R.id.newaheadline);
            headLines.setText(   getItem(position).getHeadline()  ); // get the string at position
            return result;
        }

    }


    private class NewsQuaery extends AsyncTask <String, Integer, String> {
     private String news;
     private String link;
     private ArrayList<String> description = new ArrayList<>();
        private ArrayList<String> title = new ArrayList<>();
        private ArrayList<String> links = new ArrayList<>();

        @Override
        protected String doInBackground(String... strings) {
           // InputStream inputStream = null;
                try {
                  URL  url = new URL("https://www.cbc.ca/cmlink/rss-world");
                HttpURLConnection    conn = (HttpURLConnection) url.openConnection();
                InputStream iStream = conn.getInputStream();
// conn.setReadTimeout(10000);
//                    conn.setConnectTimeout(15000);
//                    conn.setRequestMethod("GET");
//                    conn.setDoInput(true);
//                    conn.connect();
             //       inputStream = conn.getInputStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(iStream,"UTF-8");

                  //String value;
                    while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                        switch (xpp.getEventType()) {

                            case XmlPullParser.START_TAG:
                                String name = xpp.getName();
                                if (name.equals("title")) {
                                    xpp.next();
                                    news = xpp.getText();
                                    if (!news.contains("CBC | World News") && !news.contains("CBC.ca")) {
                                        title.add(news);
                                    }
                                    publishProgress(30);
                                    Log.d("News is:", news);

                                }
                                else if (name.equals("link")) {
                                    xpp.next();
                                    link = xpp.getText();
                                    if(!link.toString().equals("http://www.cbc.ca/world/?cmp=rss")&&  !link.toString().equals("https://www.cbc.ca/news/?cmp=rss"))
                                        links.add(link);
                                    publishProgress((30));

                                }
                                else if (name.equals("description")) {
                                    xpp.next();
                                    String newsdescription = xpp.getText();
                                    if (newsdescription.contains("<p>")){
                                        description.add(newsdescription);
                                    }
                                    publishProgress(30);

                                }
                                Log.i("read XML tag:", name);
                                break;
                            case XmlPullParser.TEXT:
                                break;
                        }

                        xpp.next();//look at next XML tag
                    }
                } catch (Exception e) {
                    Log.i("Exception", e.getMessage());
                }
//                finally {
//                            if(conn != null)
//                                try{
//                                    inputStream.close();
//                                }catch (IOException e){
//
//                                }
//                }

                return "";
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            newsInformationArrayList.clear();
            for(int i=0; i<title.size(); i++) {
                NewsInformation n = new NewsInformation();
                n.setHeadline(title.get(i));
                n.setDescription(description.get(i));
                n.setLink(links.get(i));
                newsInformationArrayList.add(i, n);
                newsAdapter.notifyDataSetChanged();
            }
        }

//        protected void onPostExecute(ArrayList<String> title) {
//             super.onPostExecute();
//            for(int i=0; i<title.size(); i++) {
//                NewsInformation n = new NewsInformation();
//                n.setHeadline(title.get(i));
//                n.setDescription(title.get(i));
//                n.setLink(title.get(i));
//                title.add(news n);
              //  List.add(n.getHeadline().toString());




            }

        }















