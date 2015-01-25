package com.example.tust.tmdbmovieviewer.Activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.example.tust.tmdbmovieviewer.Adapter.MovieTMDbAdapter;
import com.example.tust.tmdbmovieviewer.R;
import com.example.tust.tmdbmovieviewer.api.MovieDBClient;
import com.example.tust.tmdbmovieviewer.api.WebServiceClient;
import com.example.tust.tmdbmovieviewer.model.Request.WebServiceRequest;
import com.example.tust.tmdbmovieviewer.model.Response.Movie;
import com.example.tust.tmdbmovieviewer.model.Response.MoviesResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpcomingActivity extends ListActivity {
    private MoviesResponse movies;
    private WebServiceClient client = MovieDBClient.getInstance();
    private ListView moviesListView;
    private MovieTMDbAdapter moviesAdapter;
    private String defaultRequestUrl = "/3/movie/upcoming";

    private ProgressDialog pDialog;

    private Fragment mFragment;

    private com.actionbarsherlock.widget.SearchView search;

    private ArrayList<Movie> filterMovies = new ArrayList<Movie>();

    private String textSearch;

    EditText editsearch;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list3);
        moviesListView = (ListView) findViewById(android.R.id.list);



        new LoadPopular().execute();


    }
    class LoadPopular extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UpcomingActivity.this);
            pDialog.setMessage("Loading data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {


            movies = getInitialMovies();
            moviesAdapter = new MovieTMDbAdapter(getApplicationContext(), movies, defaultRequestUrl, client);



            moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Bitmap bitmap = null;
                    String title = ((TextView) view.findViewById(R.id.listItemTitle)).getText()
                            .toString();
                    String descriprion = MessageFormat.format("Rate: {0}\nPopularity: {1}\nDate: {2}\nVote count: {3} " +
                                    "\n {4}",
                            moviesAdapter.getMovieslist().get(position).getVoteAverage(),
                            moviesAdapter.getMovieslist().get(position).getPopularity(),
                            moviesAdapter.getMovieslist().get(position).getDate(),
                            moviesAdapter.getMovieslist().get(position).getVoteCount(),
                            moviesAdapter.getMovieslist().get(position).getAdult() == true ? "\nJust for adult" : "\n");

                    try {
                        bitmap = moviesAdapter.getImages().get(position);
                    }catch (Exception e){}
                    Intent i = new Intent(getApplicationContext(), SingleMovieView.class);

                    i.putExtra("img", bitmap);
                    i.putExtra("title", title);
                    i.putExtra("description", descriprion);
                    startActivity(i);

                }
            });
            return null;
        }


        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            super.onPostExecute(file_url);
            moviesListView.setAdapter(moviesAdapter);
            runOnUiThread(new Runnable() {
                public void run() {


                    moviesListView.setOnScrollListener(new AbsListView.OnScrollListener() {

                        @Override
                        public void onScrollStateChanged(AbsListView view, int scrollState) {
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem,
                                             int visibleItemCount, int totalItemCount) {

                            if (totalItemCount > 0) {
                                int lastInScreen = firstVisibleItem + visibleItemCount;
                                if ((lastInScreen == totalItemCount) && (lastInScreen != movies.getTotalResults())) {
                                    Log.i("ListView", "End");
                                    moviesAdapter.nextPage();
                                    moviesAdapter.updateMovies();
                                    moviesAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });


                }
            });
            runOnUiThread(new Runnable(){
                public void run(){
                    editsearch = (EditText) findViewById(R.id.search);
                    editsearch.addTextChangedListener(new TextWatcher() {


                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            System.out.println("Text ["+s+"]");
                            moviesAdapter.getFilter().filter(s.toString());
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count,
                                                      int after) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });

                }
            });

        }




    }
    private MoviesResponse getInitialMovies() {
        Map<String, String> parameters =  new HashMap<String, String>();



        WebServiceRequest request = new WebServiceRequest();
        request.setHost(MovieDBClient.HOST);
        request.setParameters(parameters);
        request.setRequestUrl(defaultRequestUrl);

        MoviesResponse movies = client.sendRequest(request, MoviesResponse.class);
        Log.i("Movies", movies.toString());
        return movies;
    }
}