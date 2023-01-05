package com.bee.beeWatching.Model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    private String idMovie;
    private String name;
    private int year;
    private String background;
    private String image;
    private String category;

    private double duration;
    private double rateIMDB;
    private double rateMeta;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_selected")
    private Date selectDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;



    public Movie() {
    }

    public Movie(String idMovie, String name, int year, String background ,String image, String category, double duration, double rateIMDB, double rateMeta,Date selectDate,User user,Season season) {
        this.idMovie = idMovie;
        this.name = name;
        this.year = year;
        this.background = background;
        this.image = image;
        this.category = category;
        this.duration = duration;
        this.rateIMDB = rateIMDB;
        this.rateMeta = rateMeta;
        this.user = user;
        this.selectDate = selectDate;
        this.season = season;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getRateIMDB() {
        return rateIMDB;
    }

    public void setRateIMDB(double rateIMDB) {
        this.rateIMDB = rateIMDB;
    }

    public double getRateMeta() {
        return rateMeta;
    }

    public void setRateMeta(double rateMeta) {
        this.rateMeta = rateMeta;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie='" + idMovie + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", background='" + background + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", duration=" + duration +
                ", rateIMDB=" + rateIMDB +
                ", rateMeta=" + rateMeta +
                ", user=" + user +
                ", date_selected=" + selectDate +
                ", Season=" + season +
                '}';
    }
}
