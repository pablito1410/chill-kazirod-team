package com.chill.table.football.application.user;


import com.chill.table.football.application.matches.Team;
import lombok.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Value
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(targetEntity = Team.class)
    @JoinTable(name = "\"TEAMS_USERS\"",
            joinColumns = {@JoinColumn(name = "\"USER_ID\"")},
            inverseJoinColumns = {@JoinColumn(name = "\"TEAM_ID\"")})
    private Set<Team> teams;

    User(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    Long getId() {
        return id;
    }
}
