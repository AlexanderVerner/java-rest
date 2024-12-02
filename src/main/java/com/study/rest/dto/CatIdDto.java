package com.study.rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatIdDto {
    private int id;
    private String name;
    private int weight;
    private int age;
}
