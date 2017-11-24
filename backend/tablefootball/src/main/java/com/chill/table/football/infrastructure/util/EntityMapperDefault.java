package com.chill.table.football.infrastructure.util;

import com.chill.table.football.application.util.EntityMapper;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

public class EntityMapperDefault implements EntityMapper {

    private final ModelMapper modelMapper;

    public EntityMapperDefault(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <DTO> DTO map(final Object source, final Class<DTO> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <DTO> Set<DTO> mapCollection(final Collection<?> source,
                                        final Class<DTO> destinationType,
                                        final Type collectionType) {
        return modelMapper.map(source, collectionType);
    }

}
