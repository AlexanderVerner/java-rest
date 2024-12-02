package com.study.rest.mapper;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {
    CatDto toDto(Cat cat);
    CatIdDto toIdDto(Cat cat);
    List<CatIdDto> toIdDtos(List<Cat> cats);
    Cat toEntity(CatDto catDto);
    Cat toEntity(int id, CatDto catDto);
}
