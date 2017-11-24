package com.chill.table.football.infrastructure.util;

import com.chill.table.football.application.util.EntityMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;
import java.util.HashSet;
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
    public <DTO> Set<DTO> mapCollection(final Collection<?> source, final Class<DTO> destinationType) {
        final TypeToken dtoType = TypeToken.of(hashSetType(destinationType).getClass());
//        final Type dtoType = new TypeToken<HashSet<DTO>>() {}.getType();
        return modelMapper.map(source, dtoType.getType());
    }

    private <DTO> HashSet<DTO> hashSetType(Class<DTO> type) {
        return new HashSet<>();
    }

}
