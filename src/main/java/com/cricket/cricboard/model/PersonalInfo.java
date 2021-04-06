package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonalInfo {

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private LocalDate birthDate;

    private Integer height;

    private String country;

}
