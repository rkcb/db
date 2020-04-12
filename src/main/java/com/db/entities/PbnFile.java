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
public class PbnFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull()
    private String pbn;

    @NotNull
    private String json;

    @ManyToOne(cascade = CascadeType.ALL)
    private Tournament tournament;

    protected PbnFile(){}

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;


    public static PbnFile create(String pbn, String json){

        PbnFile pbnFile = new PbnFile();
        pbnFile.setPbn(pbn);
        pbnFile.setPbn(json);

        return pbnFile;
    }


}
