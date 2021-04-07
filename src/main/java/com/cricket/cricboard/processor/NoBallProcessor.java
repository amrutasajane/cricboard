package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;

import static com.cricket.cricboard.constants.BallType.NO_BALL;

@Named
public class NoBallProcessor extends DefaultBallProcessor {

    @Override
    public void updateBattingScore(String ball, TeamScoreBoard teamScoreBoard) {

        String runs = ball.replace(NO_BALL.getShortName(), "");

        if (StringUtils.isNotEmpty(runs)) {

            teamScoreBoard.addScore(Integer.parseInt(runs));

            int runByBatsman = Integer.parseInt(runs) - 1;
            teamScoreBoard.addRunToBatsman(runByBatsman);

            if (runByBatsman == 1 || runByBatsman == 3 || runByBatsman == 5) {
                teamScoreBoard.changeStrike();
            }

        } else {
            teamScoreBoard.addScore(1);
        }
    }
}
