package com.natali.cultickets.mapstruct;

import com.natali.cultickets.dto.PriceDto;
import com.natali.cultickets.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDto PriceToPriceDto(Price price);
}
