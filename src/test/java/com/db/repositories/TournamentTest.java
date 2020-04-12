package com.db.repositories;

import com.db.entities.CalendarEvent;
import com.db.entities.Tournament;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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

        Tournament tournament = Tournament.create(CalendarEvent.createNow("Title"));

        tournamentRepository.save(tournament);

        Assert.isTrue(tournamentRepository.count() == 1, "tournament count == 1");
        Assert.isTrue(eventRepository.count() == 1, "event count == 1");

    }

    @Test
    public void cupTournament(){

        // main
        Tournament parent = Tournament.create(CalendarEvent.createNow("Main"));
        parent = tournamentRepository.save(parent);

        // sub tours
        Tournament tour1 = Tournament.create(CalendarEvent.createNow("Sub 1"));
        tour1.setMainTournament(parent);
        Tournament tour2 = Tournament.create(CalendarEvent.createNow("Sub 2"));
        tour2.setMainTournament(parent);
        Tournament tour3 = Tournament.create(CalendarEvent.createNow("Sub 3"));
        tour3.setMainTournament(parent);

        List<Tournament> tours = new ArrayList<>(List.of(tour1, tour2, tour3));

        parent.setSubTournaments(tours);

        parent = tournamentRepository.save(parent);

        Assert.isTrue(tournamentRepository.count() == 4, "tour count == 4");

        Assert.notNull(parent.getSubTournaments().get(1).getId(), "sub tour id not null");
        tour1 = tournamentRepository.getOne(parent.getSubTournaments().get(0).getId());
        Assert.notNull(tournamentRepository.getOne(tour1.getId()), "tour 1 has id");


    }
}
