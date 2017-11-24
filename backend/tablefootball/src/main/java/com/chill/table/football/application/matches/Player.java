package com.chill.table.football.application.matches;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    private Long id;

    @ManyToMany
    private Set<Team> teams = new HashSet<>();

    Player appendTeam(Team team) {
        teams.add(team);
        return this;
    }

}
