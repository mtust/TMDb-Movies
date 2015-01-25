package com.example.tust.tmdbmovieviewer.api;

import android.util.Log;

import com.example.tust.tmdbmovieviewer.exception.WebServiceException;
import com.example.tust.tmdbmovieviewer.model.Request.WebServiceRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.text.MessageFormat;
import java.util.Map;


public class MovieDBClient implements WebServiceClient {

    public static final String HOST = "http://api.themoviedb.org";

    private static final MovieDBClient instance = new MovieDBClient();
    private static final String apiKey = "2c385ab46c449e566f1a22020d03f404";

    private HttpClient httpClient;
    private Gson gson;

    private MovieDBClient() {
        super();
        this.httpClient = new DefaultHttpClient();
        this.gson = new GsonBuilder().create();
    }

    public static MovieDBClient getInstance() {
        return instance;
    }

    @Override
    public <T> T sendRequest(WebServiceRequest request, Class<T> clz) {
        T result = null;
        try {
            String queryParams = getQueryParameters(request);
            String url = MessageFormat.format("{0}{1}{2}", request.getHost(), request.getRequestUrl(), queryParams);
            Log.i("url:",url);
            HttpGet httpRequest = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            String response = IOUtils.toString(httpResponse.getEntity().getContent());
            result = gson.fromJson(response, clz);
        } catch (Exception e) {
            Log.e("Exception", "Exception occurs while sending request to theMovieDB");
            throw new WebServiceException("Exception with web service", e);
        }

        return result;
    }

    private String getQueryParameters(WebServiceRequest request) {
        StringBuilder queryParam = new StringBuilder();
        // Needed in all requests
        queryParam.append(MessageFormat.format("?api_key={0}", apiKey));

        for (Map.Entry<String, String> entry : request.getParameters().entrySet()) {
            queryParam.append(MessageFormat.format("&{0}={1}", entry.getKey(), entry.getValue()));
        }

        return queryParam.toString();
    }

}
