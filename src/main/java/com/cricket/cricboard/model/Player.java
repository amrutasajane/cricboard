package com.cricket.cricboard.model;

import com.cricket.cricboard.constants.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {

    private int playerId;

    private String displayName;

    private PersonalInfo personalInfo;

    private Role role;

}
