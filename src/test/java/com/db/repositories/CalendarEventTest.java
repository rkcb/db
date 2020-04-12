package com.db.repositories;

import com.db.entities.CalendarEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.Calendar;

@DataJpaTest
public class CalendarEventTest {

    @Autowired
    CalendarEventRepository eventRepository;

    @Test
    public void createAndSaveTest(){

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        Timestamp timestamp = new Timestamp(time);
        CalendarEvent event = CalendarEvent.create("title", timestamp);
        Assert.notNull(eventRepository, "repository is not null");
        eventRepository.save(event);

        Assert.isTrue(eventRepository.count() == 1, "count matches");
    }

}
