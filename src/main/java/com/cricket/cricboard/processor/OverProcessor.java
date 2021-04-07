package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.TeamScoreBoard;

public interface OverProcessor {

  void process(TeamScoreBoard battingTeamScore, TeamScoreBoard fieldingTeamScore);
}
