package com.chill.table.football.application.query.acceptation;

import com.chill.table.football.application.query.matches.MatchProjection;

import java.util.List;

public interface AcceptationProjection {
    List<MatchProjection> getMatches();
}
