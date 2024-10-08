package org.burgas.employeeservice.model.standart;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Мужчина"),
    FEMALE("Женщина");

    private final String name;

    Gender(String name) {
        this.name = name;
    }
}
