package com.study.rest.service;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;
import com.study.rest.exception.CatNotFoundException;
import com.study.rest.mapper.CatMapper;
import com.study.rest.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepo catRepo;
    private final CatMapper catMapper;

    @Override
    public int addCat(CatDto catDto) {
        Cat cat = catMapper.toEntity(catDto);
        return catRepo.save(cat).getId();
    }

    @Override
    public List<CatIdDto> getAllCats() {
        List<Cat> cats = catRepo.findAll();
        return catMapper.toIdDtos(cats);
    }

    @Override
    public CatIdDto getCatById(int id) {
        Cat cat = catRepo.findById(id)
                .orElseThrow(() -> new CatNotFoundException(String.format("Cat with id = %s", id)));
        return catMapper.toIdDto(cat);
    }

    @Override
    public void deleteCat(int id) {
        catRepo.deleteById(id);
    }

    @Override
    public int updateCat(int id, CatDto cat) {
        if (!catRepo.existsById(id)) {
            throw new CatNotFoundException(String.format("Cat with id = %s is not exist", id));
        }
        Cat catToUpdate = catMapper.toEntity(id, cat);
        return catRepo.save(catToUpdate).getId();
    }
}
