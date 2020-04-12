package com.db.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@Entity
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private String location;

    private String organizer;

    private boolean masterpoints;

    private String price;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    @OneToOne
    private Tournament tournament;

    private Timestamp start;

    protected CalendarEvent(){}

    public static CalendarEvent create(String title, Timestamp start){

        CalendarEvent event = new CalendarEvent();
        event.title = title;
        event.start = start;
        return event;

    }

    public static CalendarEvent createNow(String title){

        Calendar calendar = Calendar.getInstance();

        CalendarEvent event = new CalendarEvent();
        event.title = title;
        event.start = new Timestamp(calendar.getTimeInMillis());
        return event;

    }

}
