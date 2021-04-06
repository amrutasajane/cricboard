package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;

import javax.inject.Named;

@Named
public class BallProcessor {

  public void process(String ball, TeamScoreBoard teamScoreBoard) {

    int runs = Integer.parseInt(ball);

    teamScoreBoard.addScore(runs);

    teamScoreBoard.addRunToBatsman(runs);

    if (runs == 1 || runs == 3 || runs == 5) {
      teamScoreBoard.changeStrike();
    }
  }

  public void updateBowlingScore(String ball, TeamScoreBoard fieldingTeamScore) {

  }
}
