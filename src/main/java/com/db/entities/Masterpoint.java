package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Masterpoint {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    @OneToOne(mappedBy = "masterpoint", cascade = CascadeType.ALL)
    private Tournament tournament;

    @ManyToMany(mappedBy = "masterpoints", cascade = CascadeType.ALL)
    private List<User> receivers;

    @ElementCollection
    private Map<Integer, Float> points;
}
