package com.chill.table.football.application.query.acceptation;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Value
class AcceptationProjectionImpl implements AcceptationProjection {
    private List<Long> matchIds = new ArrayList<>();

    AcceptationProjectionImpl(List<Long> ids) {
        this.matchIds.addAll(ids);
    }
}
