package com.chill.table.football.application.matches;

import com.chill.table.football.application.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
class Team {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private Set<User> users;

    Team(String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
}
