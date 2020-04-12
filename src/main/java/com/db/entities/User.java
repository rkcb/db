package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Entity is only for checking existence
 */

@Data
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @NotNull
    private String username;

    @ReadOnlyProperty
    private String password;

    @NotNull
    private Boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    private Club club;

    @ManyToMany(mappedBy = "registeredPlayers", cascade = CascadeType.ALL)
    private List<Tournament> registeredInTournaments;

    @ManyToMany(mappedBy = "playedPlayers", cascade = CascadeType.ALL)
    private List<Tournament> playedTournaments;

    protected User(){}

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Masterpoint> masterpoints;

    public static User create(String username){

        User user = new User();
        user.setUsername(username);
        user.enabled = true;

        return user;
    }

}
