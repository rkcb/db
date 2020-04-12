package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Club {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotNull
    private String name;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    private List<User> members;

    protected Club(){}

    static public Club create(String name){

        Club club = new Club();
        club.setName(name);
        return club;
    }

}
