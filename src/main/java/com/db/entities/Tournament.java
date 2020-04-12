package com.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

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

    protected Tournament(){}

    protected Tournament(CalendarEvent calendarEvent){
        this.calendarEvent = calendarEvent;
    }

    public static Tournament create(CalendarEvent calendarEvent){
        return new Tournament(calendarEvent);
    }

//
//    protected List<Player> played;








}
