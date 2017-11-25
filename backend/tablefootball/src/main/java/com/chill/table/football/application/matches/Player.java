package com.chill.table.football.application.matches;

import com.chill.table.football.application.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    private Player() {
        // dla hibernate
    }

    Player(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

}
