package com.example.tust.tmdbmovieviewer.model.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("page")
    private Integer page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("total_results")
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        result = prime * result + ((results == null) ? 0 : results.hashCode());
        result = prime * result
                + ((totalPages == null) ? 0 : totalPages.hashCode());
        result = prime * result
                + ((totalResults == null) ? 0 : totalResults.hashCode());
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
        MoviesResponse other = (MoviesResponse) obj;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        if (results == null) {
            if (other.results != null)
                return false;
        } else if (!results.equals(other.results))
            return false;
        if (totalPages == null) {
            if (other.totalPages != null)
                return false;
        } else if (!totalPages.equals(other.totalPages))
            return false;
        if (totalResults == null) {
            if (other.totalResults != null)
                return false;
        } else if (!totalResults.equals(other.totalResults))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MoviesRequest [page=" + page + ", results=" + results
                + ", totalPages=" + totalPages + ", totalResults="
                + totalResults + "]";
    }


}
