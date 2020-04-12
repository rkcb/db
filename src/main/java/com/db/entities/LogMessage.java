package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String message;

    @NotNull
    private String playerCode; // user who caused the message

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;


}
