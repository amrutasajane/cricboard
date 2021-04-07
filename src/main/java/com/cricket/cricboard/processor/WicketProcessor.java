package com.cricket.cricboard.processor;

import com.cricket.cricboard.constants.BattingStatus;
import com.cricket.cricboard.model.BattingStats;
import com.cricket.cricboard.model.TeamScoreBoard;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;

@Named
public class WicketProcessor extends BallProcessor {

  @Override
  public void process(String ball, TeamScoreBoard teamScoreBoard) {

    teamScoreBoard.addWicket();

    BattingStats onStriker = teamScoreBoard.getBattingScores().get(teamScoreBoard.getOnStriker());

    teamScoreBoard.addRunToBatsman(0);

    onStriker.setBattingStatus(BattingStatus.OUT);

    String nextPlayer = teamScoreBoard.getNextPlayer();

    if (StringUtils.isNotEmpty(nextPlayer)) {

      teamScoreBoard.setOnStriker(nextPlayer);
    }
  }
}
