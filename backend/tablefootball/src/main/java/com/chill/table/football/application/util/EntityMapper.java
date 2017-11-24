package com.chill.table.football.application.util;

public interface EntityMapper {

    <T, DTO> DTO map(T user, Class<DTO> userDTOClass);
}
