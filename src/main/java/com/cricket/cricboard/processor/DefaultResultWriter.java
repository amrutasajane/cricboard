package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.ScoreBoard;
import com.cricket.cricboard.model.TeamScoreBoard;

import javax.inject.Named;

@Named
public class DefaultResultWriter implements ResultWriter {

  @Override
  public void write(ScoreBoard scoreBoard) {

    TeamScoreBoard team1Score = scoreBoard.getFieldingTeamBoard();

    TeamScoreBoard team2Score = scoreBoard.getBattingTeamBoard();

    if (team1Score.getTotalScore() > team2Score.getTotalScore()) {

      int diff = team1Score.getTotalScore() - team2Score.getTotalScore();
      System.out.println(scoreBoard.getFieldingTeam() + " won the match by " + diff + " runs");

    } else if (team1Score.getTotalScore() == team2Score.getTotalScore()) {
      System.out.println("Match Tied");

    } else {

      int wkt = scoreBoard.getNoOfPlayers() - team2Score.getWickets() - 1;
      System.out.println(scoreBoard.getBattingTeam() + " won the match by " + wkt + " wickets");
    }
  }
}
