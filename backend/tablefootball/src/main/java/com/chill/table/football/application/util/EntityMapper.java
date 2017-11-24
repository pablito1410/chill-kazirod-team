package com.chill.table.football.application.util;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

public interface EntityMapper {

    <DTO> DTO map(Object source, Class<DTO> destinationType);

    <DTO> Set<DTO> mapCollection(Collection<? extends Object> source, Class<DTO> destinationType, Type collectionType);
}
