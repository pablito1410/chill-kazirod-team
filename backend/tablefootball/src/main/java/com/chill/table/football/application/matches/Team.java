package com.chill.table.football.application.matches;

import com.chill.table.football.application.matches.dto.in.CreateMatchRequestDTO;
import com.chill.table.football.application.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "TEAMS")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(nullable = false, name = "NAME")
    private String name;

    @ManyToMany(targetEntity = Team.class)
    @JoinTable(name = "TEAMS_USERS",
            joinColumns = {@JoinColumn(name = "TEAM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "MATCHES_TEAMS",
            joinColumns = {@JoinColumn(name = "TEAM_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MATCH_ID")})
    private Set<Match> matches;

    Team(String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public static Team fromDTO(String name, Set<User> users) {
        return null;
    }
}
