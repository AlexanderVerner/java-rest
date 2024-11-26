package com.study.rest.service;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;

import java.util.List;

public interface CatService {
    int addCat(CatDto catDto);
    List<Cat> getAllCats();
    String getCatById(int id);
    String deleteCat(int id);
    String updateCat(CatIdDto catId);
}
