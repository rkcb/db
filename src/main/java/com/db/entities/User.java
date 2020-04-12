package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    Club club;

    @ManyToMany(mappedBy = "registeredPlayers")
    List<Tournament> registeredInTournaments;

    @ManyToMany(mappedBy = "playedPlayers")
    List<Tournament> playedTournaments;

    protected User(){}

    public static User create(String username){

        User user = new User();
        user.setUsername(username);
        user.enabled = true;

        return user;
    }

}
