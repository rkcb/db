package com.db.entities;

import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity is only for checking existence
 */

@Data
@Entity
public class User {

    @Id
    @ReadOnlyProperty
    private String username;

    @ReadOnlyProperty
    private String password;

    private boolean enabled;

    @ManyToOne
    Club club;


}
