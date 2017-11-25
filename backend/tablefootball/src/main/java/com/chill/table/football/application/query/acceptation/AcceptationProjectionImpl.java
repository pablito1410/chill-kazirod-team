package com.chill.table.football.application.query.acceptation;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
class AcceptationProjectionImpl implements AcceptationProjection {
    private List<Long> acceptationIds = new ArrayList<>();

    AcceptationProjectionImpl(AcceptationProjection acceptationProjection) {
        this.acceptationIds.addAll(acceptationProjection.getAcceptationIds());
    }
}
