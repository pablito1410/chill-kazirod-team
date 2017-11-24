package com.chill.table.football.infrastructure.util;

import com.chill.table.football.application.util.EntityMapper;
import org.modelmapper.ModelMapper;

public class EntityMapperDefault implements EntityMapper {

    private final ModelMapper modelMapper;

    public EntityMapperDefault(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public <T, DTO> DTO map(final T user, final Class<DTO> userDTOClass) {
        return null;
    }
}
