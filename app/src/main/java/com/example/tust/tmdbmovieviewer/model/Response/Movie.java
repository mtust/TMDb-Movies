package com.example.tust.tmdbmovieviewer.model.Response;


import com.google.gson.annotations.SerializedName;


public class Movie {

    @SerializedName("id")
    private Long id;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("backdrop_path")
    private String backDropPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String date;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getAdult() {
        return adult;
    }
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    public String getBackDropPath() {
        return backDropPath;
    }
    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public Double getPopularity() {
        return popularity;
    }
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
    public Integer getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adult == null) ? 0 : adult.hashCode());
        result = prime * result
                + ((backDropPath == null) ? 0 : backDropPath.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((originalTitle == null) ? 0 : originalTitle.hashCode());
        result = prime * result
                + ((popularity == null) ? 0 : popularity.hashCode());
        result = prime * result
                + ((posterPath == null) ? 0 : posterPath.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result
                + ((voteAverage == null) ? 0 : voteAverage.hashCode());
        result = prime * result
                + ((voteCount == null) ? 0 : voteCount.hashCode());
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
        Movie other = (Movie) obj;
        if (adult == null) {
            if (other.adult != null)
                return false;
        } else if (!adult.equals(other.adult))
            return false;
        if (backDropPath == null) {
            if (other.backDropPath != null)
                return false;
        } else if (!backDropPath.equals(other.backDropPath))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (originalTitle == null) {
            if (other.originalTitle != null)
                return false;
        } else if (!originalTitle.equals(other.originalTitle))
            return false;
        if (popularity == null) {
            if (other.popularity != null)
                return false;
        } else if (!popularity.equals(other.popularity))
            return false;
        if (posterPath == null) {
            if (other.posterPath != null)
                return false;
        } else if (!posterPath.equals(other.posterPath))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (voteAverage == null) {
            if (other.voteAverage != null)
                return false;
        } else if (!voteAverage.equals(other.voteAverage))
            return false;
        if (voteCount == null) {
            if (other.voteCount != null)
                return false;
        } else if (!voteCount.equals(other.voteCount))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", adult=" + adult + ", backDropPath="
                + backDropPath + ", originalTitle=" + originalTitle
                + ", posterPath=" + posterPath + ", popularity=" + popularity
                + ", title=" + title + ", voteAverage=" + voteAverage
                + ", voteCount=" + voteCount + "]";
    }

}
