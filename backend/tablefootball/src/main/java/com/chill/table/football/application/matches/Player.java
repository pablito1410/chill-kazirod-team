package com.chill.table.football.application.matches;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private Player() {
        // dla hibernate
    }

    Player(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

}
