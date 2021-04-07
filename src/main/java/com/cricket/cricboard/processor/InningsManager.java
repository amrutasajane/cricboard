package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.ScoreBoard;
import com.cricket.cricboard.model.TeamScoreBoard;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Named
public class InningsManager {

  private DefaultOverProcessor defaultOverProcessor;

  @Inject
  public InningsManager(DefaultOverProcessor defaultOverProcessor) {
    this.defaultOverProcessor = defaultOverProcessor;
  }

  public void manageInnings(ScoreBoard scoreBoard) {

    Scanner sc = new Scanner(System.in);

    /*System.out.println("Add Players for team :" + scoreBoard.getBattingTeam());

    List<String> players = new ArrayList<>();
    for (int i = 0; i < scoreBoard.getNoOfPlayers(); i++) {
      players.add(sc.next());
    }*/

    TeamScoreBoard teamScoreBoard = initTeamScoreBoard(scoreBoard);

    for (int i = 0; i < scoreBoard.getTotalOvers(); i++) {

      System.out.println("Over " + (i + 1));

      defaultOverProcessor.process(teamScoreBoard, scoreBoard.getFieldingTeamBoard());

      System.out.println("ScoreCard:");

      System.out.println(teamScoreBoard.toString());

      if (teamScoreBoard.isEndInnings()) {
        break;
      }

      TeamScoreBoard fieldingTeamBoard = scoreBoard.getFieldingTeamBoard();
      fieldingTeamBoard.setCurrentBowler(fieldingTeamBoard.getNextBowler());
    }
  }

  private TeamScoreBoard initTeamScoreBoard(ScoreBoard scoreBoard) {

    TeamScoreBoard teamScoreBoard = scoreBoard.getBattingTeamBoard();

    List<String> players = teamScoreBoard.getPlayers();
    teamScoreBoard.setOnStrikerPlayer(players.get(0));
    teamScoreBoard.setNonStrikerPlayer(players.get(1));

    TeamScoreBoard fieldingTeamBoard = scoreBoard.getFieldingTeamBoard();
    fieldingTeamBoard.setCurrentBowler(fieldingTeamBoard.getPlayers().get(0));

    return teamScoreBoard;
  }
}
