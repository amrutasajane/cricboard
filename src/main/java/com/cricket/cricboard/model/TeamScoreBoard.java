package com.cricket.cricboard.model;

import com.cricket.cricboard.constants.BattingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class TeamScoreBoard {

  // key:playerName
  private Map<String, BattingStats> battingScores;

  private Map<String, BowlingStats> bowlingScores;

  private String onStriker;

  private String nonStriker;

  private String currentBowler;

  private int totalScore;

  private int wickets;

  private int balls;

  private String overs;

  private boolean endInnings;

  public TeamScoreBoard() {
    this.battingScores = new LinkedHashMap<>();
    this.bowlingScores = new LinkedHashMap<>();
  }

  public List<String> getPlayers() {

    return new ArrayList<>(battingScores.keySet());
  }

  public void addPlayers(List<String> players) {

    players.forEach(
        player -> {
          battingScores.put(player, new BattingStats());
          bowlingScores.put(player, new BowlingStats());
        });
  }

  public void setOnStrikerPlayer(String player) {

    onStriker = player;
    battingScores.get(onStriker).setBattingStatus(BattingStatus.NOT_OUT);
  }

  public void setNonStrikerPlayer(String player) {

    nonStriker = player;
    battingScores.get(nonStriker).setBattingStatus(BattingStatus.NOT_OUT);
  }

  public void addScore(int score) {
    this.totalScore += score;
  }

  public void addWicket() {
    this.wickets += 1;
  }

  public void addOverBall() {

    this.balls += 1;
    int over = balls / 6;
    int mod = balls % 6;
    overs = over + "." + mod;
  }

  public void changeStrike() {

    String temp = onStriker;
    onStriker = nonStriker;
    nonStriker = temp;
  }

  public void addRunToBatsman(int runs) {

    BattingStats battingStats = battingScores.get(onStriker);
    battingStats.addRuns(runs);
  }

  public void addRunToBowler(int runs) {

    BowlingStats bowlingStats = bowlingScores.get(currentBowler);
    bowlingStats.addRun(runs);
  }

  public String getNextBatsman() {

    Optional<Map.Entry<String, BattingStats>> entry =
        battingScores.entrySet().stream()
            .filter(player -> player.getValue().getBattingStatus().equals(BattingStatus.YET_TO_BAT))
            .findFirst();

    if (entry.isPresent()) {
      return entry.get().getKey();
    }

    return null;
  }

  public String getNextBowler() {

    List<String> players = new ArrayList<>(bowlingScores.keySet());

    int i = players.indexOf(currentBowler);

    return players.get(i + 1);
  }

  public int getTotalPlayers() {
    return battingScores.size();
  }

  @Override
  public String toString() {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(
        String.format("%10s%10s%10s%10s%10s", "Player Name", "Score", "4s", "6s", "Balls"));
    stringBuilder.append("\n");

    for (String player : battingScores.keySet()) {

      String playerDisplayName = player;
      if (player.equals(onStriker) || player.equals(nonStriker)) {
        playerDisplayName = player + "*";
      }
      stringBuilder.append(
          String.format("%10s%10s", playerDisplayName, battingScores.get(player).toString()));
      stringBuilder.append("\n");
    }
    stringBuilder.append("Total: ").append(totalScore).append("/").append(wickets);
    stringBuilder.append("\n");
    stringBuilder.append("Overs: ").append(overs);
    stringBuilder.append("On Strike Player: ").append(onStriker);
    stringBuilder.append("Current Bowler: ").append(currentBowler);

    return stringBuilder.toString();
  }
}
