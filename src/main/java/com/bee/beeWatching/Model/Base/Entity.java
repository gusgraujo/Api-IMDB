package com.bee.beeWatching.Model.Base;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int id;
    @NotNull
    @Column(name = "createdAt")
    public Date createdAt;
    @NotNull
    @Column(name = "updatedAt")
    public Date updatedAt;

    @NotNull
    @Column(name = "guuid")
    public UUID guuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getGuuid() {
        return guuid;
    }

    public void setGuuid(UUID guuid) {
        this.guuid = guuid;
    }
}
