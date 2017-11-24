package com.chill.table.football.application.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface EntityMapper {

    <DTO> DTO map(Object source, Class<DTO> destinationType);

    <DTO> Set<DTO> mapCollection(Collection<? extends Object> source, Class<HashSet<DTO>> destinationType);
}
