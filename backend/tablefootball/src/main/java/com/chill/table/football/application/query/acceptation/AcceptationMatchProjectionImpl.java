package com.chill.table.football.application.query.acceptation;

import lombok.Value;

@Value
public class AcceptationMatchProjectionImpl implements AcceptationMatchProjection {
    private Long id;

    public AcceptationMatchProjectionImpl(AcceptationMatchProjection acceptationMatchProjection) {
        this.id = acceptationMatchProjection.getId();
    }
}
