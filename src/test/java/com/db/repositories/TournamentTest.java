package com.db.repositories;

import com.db.entities.CalendarEvent;
import com.db.entities.Tournament;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

@DataJpaTest
public class TournamentTest {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    CalendarEventRepository eventRepository;

    @Test
    public void tournamentWithoutEventSaveAndCreation(){

        CalendarEvent event = null;
        Tournament tournament = Tournament.create(event);

        tournamentRepository.save(tournament);

        Assert.isTrue(tournamentRepository.count() == 1, "tournament count == 1");
        Assert.isTrue( eventRepository.count() == 0,"event count == 0");
    }

    @Test
    public void tournamenWithEventSaved(){

        CalendarEvent event = CalendarEvent.createNow("Title");

        Tournament tournament = Tournament.create(event);

        tournamentRepository.save(tournament);

        Assert.isTrue(tournamentRepository.count() == 1, "tournament count == 1");
        Assert.isTrue(eventRepository.count() == 1, "event count == 1");

    }
}
