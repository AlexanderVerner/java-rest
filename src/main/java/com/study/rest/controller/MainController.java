package com.study.rest.controller;

import com.study.rest.dto.CatDto;
import com.study.rest.dto.CatIdDto;
import com.study.rest.entity.Cat;
import com.study.rest.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatService catService;

    @Operation(
            summary = "Create new cat in DB",
            description = "Get DTO cats and build and save entity in database"
    )
    @PostMapping("/api/add")
    public int addCat(@RequestBody CatDto catDTO) {
        return catService.addCat(catDTO);
    }

    @Operation(
            summary = "Show all cats in DB",
            description = "Get list, with all cats in database"
    )

    @GetMapping("/api/all")
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @Operation(
            summary = "Get by id cat in DB"
    )
    @GetMapping("/api")
    public String getCat(@RequestParam int id) {
        return catService.getCatById(id);
    }

    @Operation(
            summary = "Delete cat from DB",
            description = "Find cat in database by id and delete from it"
    )
    @DeleteMapping("/api")
    public String deleteCat(@RequestParam int id) {
        return catService.deleteCat(id);
    }

    @Operation(
            summary = "Put methods for update cat-parameters",
            description = "Method will check if the cat exists and update it or created a new one"
    )

    @PutMapping("/api/add")
    public String changeCat(@RequestBody CatIdDto catId) {
        return catService.updateCat(catId);
    }
}
