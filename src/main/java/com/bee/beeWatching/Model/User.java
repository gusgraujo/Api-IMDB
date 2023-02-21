package com.bee.beeWatching.Model;


import com.bee.beeWatching.Model.Base.BaseEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
