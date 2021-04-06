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

  public void manageInnings(ScoreBoard scoreBoard, String teamName) {

    Scanner sc = new Scanner(System.in);

    TeamScoreBoard teamScoreBoard = scoreBoard.getBattingTeamBoard();
    TeamScoreBoard fieldScoreBoard = scoreBoard.getFieldingTeamBoard();

    System.out.println("Add Players for team :" + teamName);

    List<String> players = new ArrayList<>();
    for (int i = 0; i < scoreBoard.getNoOfPlayers(); i++) {
      players.add(sc.next());
    }

    teamScoreBoard.addPlayers(players);
    teamScoreBoard.setOnStrikerPlayer(players.get(0));
    teamScoreBoard.setNonStrikerPlayer(players.get(1));

    for (int i = 0; i < scoreBoard.getTotalOvers(); i++) {

      System.out.println("Over " + (i + 1));

      overProcessor.process(teamScoreBoard, fieldScoreBoard);

      System.out.println("ScoreCard:");

      System.out.println(teamScoreBoard.toString());

      if (teamScoreBoard.isEndInnings()) {
        break;
      }
    }
  }
}
