package com.chill.table.football.application.matches;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Match {

    @Id
    private Long id;

    private LocalDateTime beginDateTime;

    @Enumerated(EnumType.STRING)
    private State state;



}
