package com.chill.table.football.application.matches;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

//    @Column(nullable = false, name = "NAME")
//    private String name;

    @ManyToMany(mappedBy = "teams")
    private Set<Player> players = new HashSet<>();

//    Team(String name) {
//        this.name = name;
//    }

    Long getId() {
        return id;
    }

    Team appendPlayer(Player player) {
        players.add(player);
        return this;
    }

}
