package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;

import static com.cricket.cricboard.constants.BallType.WIDE;

@Named
public class WideProcessor extends BallProcessor{

    @Override
    public void process(String ball, TeamScoreBoard teamScoreBoard) {

        String runs = ball.replace(WIDE.getShortName(), "");

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
