package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;

public interface BallProcessor {

  void updateBattingScore(String ball, TeamScoreBoard battingTeamBoard);

  void updateBowlingScore(String ball, TeamScoreBoard fieldingTeamBoard);
}
