package com.study.rest.controller;

import com.study.rest.DTO.CatDTO;
import com.study.rest.entity.Cat;
import com.study.rest.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @Operation(
            summary = "Create new cat in DB",
            description = "Get DTO cats and build and save entity in database"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row: " + catRepo.save(
                        Cat.builder()
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())
                        .name(catDTO.getName())
                        .build())
        );
    }

    @Operation(
            summary = "Show all cats in DB",
            description = "Get list, with all cats in database"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAllCats() {
        return catRepo.findAll();
    }

    @Operation(
            summary = "Get by id cat in DB"
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Delete cat from DB",
            description = "Find cat in database by id and delete from it"
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @Operation(
            summary = "Put methods for update cat-parameters",
            description = "Method will check if the cat exists and update it or created a new one"
    )
    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
}
