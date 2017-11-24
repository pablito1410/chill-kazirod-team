package com.chill.table.football.application.user;


import com.chill.table.football.application.matches.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany()
    @JoinTable(name = "TEAMS_USERS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TEAM_ID")})
    private Set<Team> teams;

    public User(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

}
