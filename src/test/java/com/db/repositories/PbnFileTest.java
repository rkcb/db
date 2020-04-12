package com.db.repositories;

import com.db.entities.CalendarEvent;
import com.db.entities.PbnFile;
import com.db.entities.Tournament;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class PbnFileTest {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    PbnFileRepository pbnFileRepository;

    @Test
    public void createPbnFile(){

        // create and save tournament
        Tournament tournament = Tournament.create(CalendarEvent.createNow("Title"));
        tournament = tournamentRepository.save(tournament);

        // create and save pbn files
        PbnFile pbnFile1 = PbnFile.create("", "");
        pbnFile1.setTournament(tournament);
        PbnFile pbnFile2 = PbnFile.create("", "");
        pbnFile2.setTournament(tournament);

        pbnFile1 = pbnFileRepository.save(pbnFile1);
        pbnFile2 = pbnFileRepository.save(pbnFile2);

        // set pbn files of tournament and save
        tournament.setPbnFiles(new ArrayList<>(List.of(pbnFile1, pbnFile2)));
        tournament = tournamentRepository.save(tournament);

        Assert.isTrue(tournament.getPbnFiles().size() == 2, "pbn count matches");
        Assert.isTrue(tournament.getPbnFiles().get(0).getId() > 0,
                "pbn id of a tour is not null");

        Assert.isTrue(pbnFile1.getTournament().getPbnFiles().size() == 2,
                "count from pbn file matches");

        Assert.isTrue(pbnFile1.getTournament().getPbnFiles().get(1).getId().equals(pbnFile2.getId()),
                "id of pbn file 2 matches");

    }

}
