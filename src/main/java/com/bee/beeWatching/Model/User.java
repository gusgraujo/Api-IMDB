package com.bee.beeWatching.Model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String discordName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private Avatar avatarFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }


    public int getId() {
        return id;
    }

    public Avatar getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(Avatar avatarFile) {
        this.avatarFile = avatarFile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discordName='" + discordName + '\'' +
                ", fileName='" + avatarFile + '\'' +
                '}';
    }
}
