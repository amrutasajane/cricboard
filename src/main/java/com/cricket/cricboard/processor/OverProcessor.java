package com.cricket.cricboard.processor;

import com.cricket.cricboard.constants.BallType;
import com.cricket.cricboard.model.TeamScoreBoard;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Scanner;

@Named
public class OverProcessor {

  private BallProcessorFactory ballProcessorFactory;

  @Inject
  public OverProcessor(BallProcessorFactory ballProcessorFactory) {
    this.ballProcessorFactory = ballProcessorFactory;
  }

  public void process(TeamScoreBoard battingTeamScore, TeamScoreBoard fieldingTeamScore) {

    Scanner sc = new Scanner(System.in);

    int i = 0;
    while (i < 6) {

      String ball = sc.next();

      BallType ballType = BallType.getBallType(ball);

      BallProcessor ballProcessor = ballProcessorFactory.getBallProcessor(ballType);

      ballProcessor.process(ball, battingTeamScore);

      if (!(BallType.WIDE == ballType || BallType.NO_BALL == ballType)) {

        battingTeamScore.addOverBall();
        i++;
      }
      if (checkWinCondition(battingTeamScore, fieldingTeamScore)) {
        battingTeamScore.setEndInnings(true);
        return;
      }
    }

    battingTeamScore.changeStrike();
  }

  private boolean checkWinCondition(
      TeamScoreBoard battingTeamScore, TeamScoreBoard fieldingTeamScore) {

    return (fieldingTeamScore.getTotalScore() != 0
            && battingTeamScore.getTotalScore() > fieldingTeamScore.getTotalScore())
        || (battingTeamScore.getWickets() == battingTeamScore.getTotalPlayers() - 1);
  }
}
