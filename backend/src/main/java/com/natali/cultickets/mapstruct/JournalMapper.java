package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.JournalDto;
import com.natali.cultickets.model.Journal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalMapper {
    JournalDto journalToJournalDto(Journal journal);
}