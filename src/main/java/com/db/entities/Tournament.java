package com.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Tournament keeps record of players who registered in a tournament and
 * played it
 */

@Data
@Entity
public class Tournament  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tournament")
    private CalendarEvent calendarEvent;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> playedPlayers;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> registeredPlayers;

    // cup type tournament
    @OneToMany(mappedBy = "mainTournament", cascade = CascadeType.ALL)
    private List<Tournament> subTournaments;

    // cup master tournament
    @ManyToOne(cascade = CascadeType.ALL)
    private Tournament mainTournament;

    @OneToOne(cascade = CascadeType.ALL)
    private Masterpoint masterpoint;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<PbnFile> pbnFiles;

    protected Tournament(){}

    protected Tournament(CalendarEvent calendarEvent){
        this.calendarEvent = calendarEvent;
    }

    public static Tournament create(CalendarEvent calendarEvent){
        return new Tournament(calendarEvent);
    }









}
