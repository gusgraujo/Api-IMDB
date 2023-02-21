package com.bee.beeWatching.Model.Base;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends Entity{

    @NotNull
    @Column(name = "name")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
