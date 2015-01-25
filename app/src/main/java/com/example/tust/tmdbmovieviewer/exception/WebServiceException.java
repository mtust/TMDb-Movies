package com.example.tust.tmdbmovieviewer.exception;

public class WebServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public WebServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

}
