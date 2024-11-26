package com.study.rest.service;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;

import java.util.List;

public interface CatService {
    int addCat(CatDto catDto);
    List<CatIdDto> getAllCats();
    CatIdDto getCatById(int id);
    void deleteCat(int id);
    int updateCat(CatIdDto catId);
}
