package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Team {

  private String name;

  private Map<String, Player> players;
}
