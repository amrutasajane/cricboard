package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ScoreBoard {

  // key:teamName
  private Map<String, TeamScoreBoard> teamScoreBoards;

  private String battingTeam;

  private String fieldingTeam;

  private int noOfPlayers;

  private int totalOvers;

  public ScoreBoard() {
    this.teamScoreBoards = new HashMap<>();
  }

  public TeamScoreBoard getTeamScore(String teamName) {

    return teamScoreBoards.get(teamName);
  }

  public void addTeam(String teamName) {

    teamScoreBoards.put(teamName, new TeamScoreBoard());
  }

  public TeamScoreBoard getBattingTeamBoard() {

    return teamScoreBoards.get(battingTeam);
  }

  public TeamScoreBoard getFieldingTeamBoard() {

    return teamScoreBoards.get(fieldingTeam);
  }

}
