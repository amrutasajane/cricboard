package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;

import javax.inject.Named;

@Named
public class DefaultBallProcessor implements BallProcessor {


  @Override
  public void updateBattingScore(String ball, TeamScoreBoard battingTeamBoard) {

    int runs = Integer.parseInt(ball);

    battingTeamBoard.addScore(runs);

    battingTeamBoard.addRunToBatsman(runs);

    if (runs == 1 || runs == 3 || runs == 5) {
      battingTeamBoard.changeStrike();
    }
  }

  @Override
  public void updateBowlingScore(String ball, TeamScoreBoard fieldingTeamScore) {}
}
