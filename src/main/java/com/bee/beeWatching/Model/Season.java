package com.bee.beeWatching.Model;

import com.bee.beeWatching.Model.Base.NamedBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "season")
public class Season extends NamedBaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_start")
    private Date dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_end")
    private Date dateEnd;

    @JsonIgnore
    @JsonManagedReference
    @ManyToMany(targetEntity = Movie.class, mappedBy = "seasons")
    public List<Movie> movies;


    public Season() {
    }

    public Season(String name, Date dateStart, Date dateEnd) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                "name=" + name +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
