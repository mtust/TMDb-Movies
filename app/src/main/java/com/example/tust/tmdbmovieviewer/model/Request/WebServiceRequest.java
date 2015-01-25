package com.example.tust.tmdbmovieviewer.model.Request;

import java.util.Map;

public class WebServiceRequest {

    private String host;
    private Map<String, String> parameters;
    private String requestUrl;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public Map<String, String> getParameters() {
        return parameters;
    }
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    public String getRequestUrl() {
        return requestUrl;
    }
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result
                + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result
                + ((requestUrl == null) ? 0 : requestUrl.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WebServiceRequest other = (WebServiceRequest) obj;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        if (requestUrl == null) {
            if (other.requestUrl != null)
                return false;
        } else if (!requestUrl.equals(other.requestUrl))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "MovieDbRequest [host=" + host + ", parameters=" + parameters
                + ", requestUrl=" + requestUrl + "]";
    }

}
