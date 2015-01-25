package com.example.tust.tmdbmovieviewer.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class MovieTMDbAdapter extends BaseAdapter implements Filterable {


    private MoviesResponse movies;



    private List<Movie> movieslist;
    private LayoutInflater inflater;
    private Context context;
    private String requestUrl;
    private WebServiceClient client;
    private Bitmap bitmap;


    private ItemFilter filter;

    private List<Bitmap> images = new ArrayList<Bitmap>();
    private List<Bitmap> imagesworks = new ArrayList<Bitmap>();

    ImageLoadThread imageLoadThread = new ImageLoadThread();

    private int i = 0;

    private MoviesResponse moviesAll  = null;
    private List<Movie> movieslistAll = new ArrayList<Movie>();

    private boolean isSearch = false;


    public MovieTMDbAdapter(Context context, final MoviesResponse movies, String requestUrl, WebServiceClient client) {
        super();
        this.movies = movies;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.requestUrl = requestUrl;
        this.client = client;
        movieslist = movies.getResults();

        imageLoadThread.start();
        images = new ArrayList<Bitmap>();
        images = imagesworks;
        //getFilter();
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                moviesAll = movies;
                setAll();
            }
        }).start();*/
    }

    @Override
    public int getCount() {
        return movies.getResults().size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.getResults().get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.getResults().get(position).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
       // final int p = position;
        final ViewHolder viewHolder;
        final String description;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_list_item, null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

           try {
               description = MessageFormat.format("Rate: {0}\nPopularity: {1}\nDate: {2}",
                       movieslist.get(position).getVoteAverage(),
                       movieslist.get(position).getPopularity(),
                       movieslist.get(position).getDate());


               viewHolder.title = detail(view, R.id.listItemTitle, movieslist.get(position).getTitle());
               viewHolder.description = detail(view, R.id.listitemDescription, description);

               viewHolder.icon = (ImageView) view.findViewById(R.id.listItemIcon);


               new Thread(new Runnable() {
                   @Override
                   public void run() {


                       try {


                           if (imageLoadThread.isAlive()) {
                               try {
                                   imageLoadThread.join();
                               } catch (InterruptedException e) {
                                   System.out.println("InterruptedExcetion");
                               }

                           }
                           else
                            viewHolder.icon.setImageBitmap(images.get(position));


                       } catch (Exception e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                           System.out.println("Exceprion");
                       }
                   }
               }).start();

           }catch (Exception e){
               view = inflater.inflate(R.layout.layout_list_item, null);
               view.setTag(new ViewHolder());
           }

        return view;
    }

    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }


    private class ViewHolder {
        TextView title;
        TextView description;
        ImageView icon;
    }


    public void nextPage() {
        Integer page = movies.getPage() + 1;
        movies.setPage(page);
    }


    public void updateMovies() {
        Map<String, String> parameters =  new HashMap<String, String>();
        parameters.put("page", String.valueOf(movies.getPage()));

        WebServiceRequest request = new WebServiceRequest();
        request.setHost(MovieDBClient.HOST);
        request.setParameters(parameters);
        request.setRequestUrl(requestUrl);

        MoviesResponse movies = client.sendRequest(request, MoviesResponse.class);
        Log.i("Movies", movies.toString());

        movieslist.addAll(movies.getResults());

        this.movies = movies;
        this.movies.setResults(movieslist);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    if (imageLoadThread.isAlive()) {
                        try {
                            imageLoadThread.join();
                        } catch (InterruptedException e) {
                        }

                    } else {
                        imageLoadThread = new ImageLoadThread();
                        imageLoadThread.start();
                    }

                }
            }).start();
        images = new ArrayList<Bitmap>();
        images = imagesworks;
       // imagesworks.addAll(images);

    }


    public void setAll(){
        while(moviesAll.getPage()!=moviesAll.getTotalPages())
        {
            Map<String, String> parameters =  new HashMap<String, String>();
            parameters.put("page", String.valueOf(moviesAll.getPage()));

            WebServiceRequest request = new WebServiceRequest();
            request.setHost(MovieDBClient.HOST);
            request.setParameters(parameters);
            request.setRequestUrl(requestUrl);

            MoviesResponse movies = client.sendRequest(request, MoviesResponse.class);
            Log.i("Movies", movies.toString());
            movieslistAll.addAll(moviesAll.getResults());
            Integer page = moviesAll.getPage() + 1;
            moviesAll.setPage(page);
        }
    }


    public Filter getFilter() {
        if (filter == null){
            filter  = new ItemFilter();
        }
        return filter;
    }
    private class ItemFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {



            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();

            images = new ArrayList<Bitmap>();
            if(constraint != null && constraint.toString().length() > 0){
                isSearch = true;
                ArrayList<Movie> filteredItems = new ArrayList<Movie>();
                ArrayList<Bitmap> filteredImages = new ArrayList<Bitmap>();
                for(int j = 0  ; j < movies.getResults().size(); j++)
                {
                    Movie m = movies.getResults().get(j);


                    if(m.getTitle().toLowerCase().contains(constraint)) {
                        filteredItems.add(m);
                        synchronized (this) {
                            try {
                                images.add(imagesworks.get(j));
                            } catch (Exception e) {
                                System.out.println("fuck!!!");
                            }
                        }
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = movies.getResults();
                    result.count = movies.getResults().size();
                    images = new ArrayList<Bitmap>();
                    try {
                        images = imagesworks;
                    }catch (Exception e){ e.printStackTrace();}
                    isSearch = false;
                }
            }
            return result;
        }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            movieslist = (List<Movie>)results.values;
            //notifyDataSetChanged();
        }
    }


        class ImageLoadThread extends Thread {



            public void run() {
                for (; i < movies.getResults().size(); i++) {
                try {
                    Bitmap bm;
                    URL url = new URL("http://image.tmdb.org/t/p/w300" + movies.getResults().get(i).getBackDropPath());
                    HttpGet httpRequest = null;

                    httpRequest = new HttpGet(url.toURI());

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);

                    HttpEntity entity = response.getEntity();
                    BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
                    InputStream input = b_entity.getContent();

                    bm = BitmapFactory.decodeStream(input);
                    imagesworks.add(bm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public boolean isSearcActive(){
        return isSearch;
    }
    public List<Movie> getMovieslist() {
        return movieslist;
    }

    public void setMovieslist(List<Movie> movieslist) {
        this.movieslist = movieslist;
    }

    public List<Bitmap> getImages() {
        if(imageLoadThread.isAlive())
        {
            try{
                imageLoadThread.join();
            }catch(InterruptedException e){}

        }
        return images;
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

}
