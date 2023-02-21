package com.bee.beeWatching.Model;


import com.bee.beeWatching.Model.Base.NamedBaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "movie")
@DynamicUpdate
public class Movie extends NamedBaseEntity {

    private String idMovie;
    private Integer year;
    private String background;
    private String imageURL;
    private String category;
    private Double duration;
    private Double rateIMDB;
    private Double rateMeta;
    @Lob
    private String trailerUrl;

    private Boolean status;


    @JsonIgnore
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "season_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id"))
    private List<Season> seasons;

    @JsonIgnore
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "participant_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<Participant> participants;

    public Movie() {
    }

    public Movie(String idMovie, String name, Integer year, String background ,String imageURL, String trailerUrl, String category, Double duration, Double rateIMDB, Double rateMeta,List<Participant> participants,List<Season> seasons, Boolean status) {
        this.idMovie = idMovie;
        this.name = name;
        this.year = year;
        this.background = background;
        this.imageURL = imageURL;
        this.trailerUrl = trailerUrl;
        this.category = category;
        this.duration = duration;
        this.rateIMDB = rateIMDB;
        this.rateMeta = rateMeta;
        this.seasons = seasons;
        this.status = status;
        this.participants = participants;
    }
    public List<Participant> getParticipants(){return this.participants;}

    public void setParticipants(List<Participant> participants){ this.participants = participants; }
    public void setTrailerUrl(String trailerUrl){
        this.trailerUrl = trailerUrl;
    }
    public String getTrailerUrl(){
        return this.trailerUrl;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }
    public Boolean getStatus(){
        return this.status;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getRateIMDB() {
        return rateIMDB;
    }

    public void setRateIMDB(Double rateIMDB) {
        this.rateIMDB = rateIMDB;
    }

    public Double getRateMeta() {
        return rateMeta;
    }

    public void setRateMeta(Double rateMeta) {
        this.rateMeta = rateMeta;
    }

    public List<Season> getSeason() {
        return this.seasons;
    }

    public void setSeason(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                "idMovie='" + idMovie + '\'' +
                ", year=" + year +
                ", background='" + background + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", category='" + category + '\'' +
                ", duration=" + duration +
                ", rateIMDB=" + rateIMDB +
                ", rateMeta=" + rateMeta +
                ", trailerUrl='" + trailerUrl + '\'' +
                ", status=" + status +
                ", seasons=" + seasons +
                '}';
    }
}
