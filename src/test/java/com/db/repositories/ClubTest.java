package com.db.repositories;

import com.db.Tool;
import com.db.entities.CalendarEvent;
import com.db.entities.Club;
import com.db.entities.Tournament;
import com.db.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class ClubTest {
//    13.56	Plugin Error: Plugin "Lombok" is incompatible (until build 193.SNAPSHOT < IU-201.6668.121).
    @Autowired
    UserRepository userRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    CalendarEventRepository eventRepository;

    @Test
    public void createAndSave(){
        Club club = Club.create("my Club");
        clubRepository.save(club);

        Assert.isTrue(clubRepository.count() == 1, "club count == 1");
    }

    @Test
    public void addMember(){

        // create a club
        Club club = Club.create("My club");
        club = clubRepository.save(club);

        // create a user and and to the club
        User user = User.create("esa");
        user.setClub(club);
        club.setMembers(new ArrayList<>(List.of(user)));
        user = userRepository.save(user);

        Assert.isTrue(clubRepository.count() == 1, "club count == 1");
        Assert.isTrue(userRepository.count() == 1, "user count == 1");
        Assert.notNull(club.getCreated(), "has creation time");
        Assert.notNull(club.getUpdated(), "has update time");
        Assert.isTrue(user.getClub().getMembers().size() == 1, "club has one member");
        Assert.notNull(user.getClub(), "user has a club");

    }

    @Test
    public void tournamentRegistration(){

        // create tournaments
        Tournament tournament1 = Tournament.create(CalendarEvent.createNow("Tournament title"));
        tournament1 = tournamentRepository.save(tournament1);

        Tournament tournament2 = Tournament.create(CalendarEvent.createNow("Tournament title2"));
        tournament2 = tournamentRepository.save(tournament2);

        // check that events show in eventRepository
        Assert.isTrue(eventRepository.count() == 2, "both events are saved");

        // check that tournament has an event
        Assert.notNull(tournament1.getCalendarEvent(), "tournament1 has a calendar event" );

        // create a player and register him to two tournaments
        User user = User.create("esa");
        user.setRegisteredInTournaments(new ArrayList<>(List.of(tournament1, tournament2)));
        user = userRepository.save(user);

        tournament1 = user.getRegisteredInTournaments().get(0);

        Assert.notNull(tournament1.getId(), "tour1 id is not null");

        // add user to played players
        tournament1.setRegisteredPlayers(new ArrayList<>(List.of(user)));
        tournament1 = tournamentRepository.save(tournament1);

        Assert.notNull(tournament1.getRegisteredPlayers().get(0).getId(),
                "user id not null");

    }

    @Test
    public void playedTournaments(){

        // create tournaments
        Tournament tournament1 = Tournament.create(CalendarEvent.createNow("Tournament title"));
        tournament1 = tournamentRepository.save(tournament1);

        Tournament tournament2 = Tournament.create(CalendarEvent.createNow("Tournament title2"));
        tournament2 = tournamentRepository.save(tournament2);

        // check that events show in eventRepository
        Assert.isTrue(eventRepository.count() == 2, "both events are saved");

        // check that tournament has an event
        Assert.notNull(tournament1.getCalendarEvent(), "tournament1 has a calendar event" );

        // create a player and add him to two played tournaments
        User user = User.create("esa");
        user.setPlayedTournaments(new ArrayList<>(List.of(tournament1, tournament2)));
        user = userRepository.save(user);

        tournament1 = user.getPlayedTournaments().get(0);

        Assert.notNull(tournament1.getId(), "tour1 id is not null");


        tournament1.setPlayedPlayers(new ArrayList<>(List.of(user)));
        tournament1 = tournamentRepository.save(tournament1);

        Assert.notNull(tournament1.getPlayedPlayers().get(0).getId(),
                "user id not null");


    }




}
