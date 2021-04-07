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

  private OverProcessor overProcessor;

  @Inject
  public InningsManager(OverProcessor overProcessor) {
    this.overProcessor = overProcessor;
  }

  public void manageInnings(ScoreBoard scoreBoard) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Add Players for team :" + scoreBoard.getBattingTeam());

    List<String> players = new ArrayList<>();
    for (int i = 0; i < scoreBoard.getNoOfPlayers(); i++) {
      players.add(sc.next());
    }

    TeamScoreBoard teamScoreBoard = initTeamScoreBoard(scoreBoard, players);

    for (int i = 0; i < scoreBoard.getTotalOvers(); i++) {

      System.out.println("Over " + (i + 1));

      overProcessor.process(teamScoreBoard, scoreBoard.getFieldingTeamBoard());

      System.out.println("ScoreCard:");

      System.out.println(teamScoreBoard.toString());

      if (teamScoreBoard.isEndInnings()) {
        break;
      }
    }
  }

  private TeamScoreBoard initTeamScoreBoard(ScoreBoard scoreBoard, List<String> players) {

    TeamScoreBoard teamScoreBoard = scoreBoard.getBattingTeamBoard();

    teamScoreBoard.addPlayers(players);
    teamScoreBoard.setOnStrikerPlayer(players.get(0));
    teamScoreBoard.setNonStrikerPlayer(players.get(1));

    return teamScoreBoard;
  }
}
