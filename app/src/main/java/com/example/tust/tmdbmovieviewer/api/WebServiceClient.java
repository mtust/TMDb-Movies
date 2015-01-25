package com.example.tust.tmdbmovieviewer.api;

import com.example.tust.tmdbmovieviewer.model.Request.WebServiceRequest;

public interface WebServiceClient {

    <T> T sendRequest(WebServiceRequest request, Class<T> clz);

}
