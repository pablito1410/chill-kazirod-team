package com.chill.table.football.application.matches.dto;

import lombok.Value;

import java.util.Set;

@Value
public class CreateMatchDTO {
    private Set<Long> firstTeamUserIds;
    private Set<Long> secondTeamUserIds;
}
