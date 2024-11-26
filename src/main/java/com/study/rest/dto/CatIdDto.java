package com.study.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CatIdDto {
    private int id;
    private String name;
    private int weight;
    private int age;
}
