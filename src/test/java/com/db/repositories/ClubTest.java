package com.db.repositories;

import com.db.entities.Club;
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

    @Test
    public void createAndSave(){
        Club club = Club.create("my Club");
        clubRepository.save(club);

        Assert.isTrue(clubRepository.count() == 1, "club count == 1");
    }

    @Test
    public void addMember(){

        Club club = Club.create("My club");
        club = clubRepository.save(club);

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





}
