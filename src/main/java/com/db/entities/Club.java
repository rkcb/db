package com.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Club {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<User> members;

}
