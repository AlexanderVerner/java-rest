package com.study.rest.service;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;
import com.study.rest.exception.CatNotFoundException;
import com.study.rest.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepo catRepo;

    @Override
    public int addCat(CatDto catDto) {
        Cat cat = Cat.builder()
                    .age(catDto.getAge())
                    .weight(catDto.getWeight())
                    .name(catDto.getName())
                    .build();
        return catRepo.save(cat).getId();
    }

    @Override
    public List<CatIdDto> getAllCats() {
        List<Cat> allCat = catRepo.findAll();
        List<CatIdDto> catsForResponse = new ArrayList<>(allCat.size());
        for (Cat cat : allCat) {
            CatIdDto catDto = new CatIdDto(
                    cat.getId(),
                    cat.getName(),
                    cat.getWeight(),
                    cat.getAge()
            );
            catsForResponse.add(catDto);
        }
        return catsForResponse;
    }

    @Override
    public CatIdDto getCatById(int id) {
        Cat cat = catRepo.findById(id)
                .orElseThrow(() -> new CatNotFoundException(String.format("Cat with id = %s", id)));
        return new CatIdDto(
                cat.getId(),
                cat.getName(),
                cat.getWeight(),
                cat.getAge()
        );
    }

    @Override
    public void deleteCat(int id) {
        catRepo.deleteById(id);
    }

    @Override
    public int updateCat(CatIdDto cat) {
        if (!catRepo.existsById(cat.getId())) {
            throw new CatNotFoundException(String.format("Cat with id = %s is not exist", cat.getId()));
        }
        Cat catWithId = Cat.builder()
                .age(cat.getAge())
                .weight(cat.getWeight())
                .name(cat.getName())
                .build();
        return catRepo.save(catWithId).getId();
    }
}
