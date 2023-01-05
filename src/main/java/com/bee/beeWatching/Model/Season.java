package com.bee.beeWatching.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Season")
public class Season {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_start")
    private Date dateStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_end")
    private Date dateEnd;

    public Season() {
    }

    public Season(int id,String name, Date dateStart, Date dateEnd) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getSeason() {
        return id;
    }

    public void setSeason(int id) {
        this.id = id;
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
