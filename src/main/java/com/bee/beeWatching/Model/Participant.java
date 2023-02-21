package com.bee.beeWatching.Model;

import com.bee.beeWatching.Model.Base.NamedBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "participant")
public class Participant extends NamedBaseEntity {

    private String discordName;

    private String avatarFileUpload;


    @JsonIgnore
    @JsonManagedReference
    @ManyToMany(targetEntity = Movie.class, mappedBy = "participants")
    public List<Movie> movies;

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public String getAvatarFileUpload() {
        return avatarFileUpload;
    }

    public void setAvatarFileUpload(String avatarFileUpload) {
        this.avatarFileUpload = avatarFileUpload;
    }
}
