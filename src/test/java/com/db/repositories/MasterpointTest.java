package com.db.repositories;

import com.db.entities.CalendarEvent;
import com.db.entities.Masterpoint;
import com.db.entities.Tournament;
import com.db.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
public class MasterpointTest {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    MasterpointRepository masterpointRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void addMasterpointToPlayers(){

        // create users
        User user1 = User.create("Touko");
        User user2 = User.create("Taavi");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        // create masterpoint values per player
        Masterpoint masterpoint = new Masterpoint();

        Map<Integer, Float> points = new HashMap<>(4);
        points.put(user1.getId(), 1.4f);
        points.put(user2.getId(), 0.5f);

        masterpoint.setPoints(points);

        masterpoint = masterpointRepository.save(masterpoint);

        Assert.notNull(masterpoint.getId(), "masterpoint was saved correctly");

        Assert.isTrue(masterpoint.getPoints().get(user1.getId()) > 0,
                "user1 has positive points");
    }

    @Test
    public void createRelations(){

        // create users
        User user1 = User.create("Touko");
        User user2 = User.create("Taavi");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        // create masterpoint and add receivers
        Masterpoint masterpoint = new Masterpoint();
        masterpoint.setReceivers(new ArrayList<>(List.of(user1, user2)));

        // set points
        Map<Integer, Float> points = new HashMap<>(2);
        points.put(user1.getId(), 1.3f);
        points.put(user2.getId(), 0.5f);

//        create tournament
        Tournament tournament = Tournament.create(CalendarEvent.createNow("Title"));
        tournament = tournamentRepository.save(tournament);

        masterpoint.setTournament(tournament);

        // save all of masterpoint
        masterpoint = masterpointRepository.save(masterpoint);

        Assert.notNull(masterpoint.getId(), "masterpoint id not null");

        // define 1-1 of tournament and masterpoint
        tournament.setMasterpoint(masterpoint);
        tournament = tournamentRepository.save(tournament);

        tournament = tournamentRepository.getOne(tournament.getId());

        Assert.isTrue(tournament.getMasterpoint().getId() > 0,
                "masterpoint id of a tour is not null");

        // set user side masterpoint
        user1.setMasterpoints(new ArrayList<>(List.of(masterpoint)));
        user1 = userRepository.save(user1);

        Assert.isTrue(user1.getMasterpoints().get(0).getId() > 0,
                "masterpoint id of user is not null");


    }
}
