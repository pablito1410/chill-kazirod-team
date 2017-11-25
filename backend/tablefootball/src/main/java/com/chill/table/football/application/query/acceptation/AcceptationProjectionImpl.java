package com.chill.table.football.application.query.acceptation;

import com.chill.table.football.application.query.matches.MatchProjection;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Value
class AcceptationProjectionImpl implements AcceptationProjection {
    private List<MatchProjection> matches = new ArrayList<>();

    AcceptationProjectionImpl(List<MatchProjection> matches) {
        this.matches.addAll(matches);
    }
}
