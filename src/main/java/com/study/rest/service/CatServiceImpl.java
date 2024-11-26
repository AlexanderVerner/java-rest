package com.study.rest.service;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;
import com.study.rest.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepo catRepo;

//    public static <nameDto> Object buildCat(nameDto){
//        Cat cat = Cat.builder()
//                .age(nameDto.getAge())
//                .weight(nameDto.getWeight())
//                .name(nameDto.getName())
//                .build();
//        return cat;
//    }

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
    public List<Cat> getAllCats() {
        return catRepo.findAll();
    }

    @SneakyThrows
    @Override
    public String getCatById(int id) {
        return catRepo.findById(id).orElseThrow().toString();
    }

    @SneakyThrows
    @Override
    public String deleteCat(int id) {
        catRepo.deleteById(id);
        return "Deleted " + id;
    }

    @Override
    public String updateCat(CatIdDto cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        Cat catWithId = Cat.builder()
                .age(cat.getAge())
                .weight(cat.getWeight())
                .name(cat.getName())
                .build();
        return catRepo.save(catWithId).toString();
    }
}
