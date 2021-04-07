package com.cricket.cricboard.processor;

import com.cricket.cricboard.CricBoardException;
import com.cricket.cricboard.model.ScoreBoard;
import com.cricket.cricboard.model.TeamScoreBoard;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Named
public class MatchManager {

  private InningsManager inningsManager;

  private ResultWriter resultWriter;

  @Inject
  public MatchManager(InningsManager inningsManager, ResultWriter resultWriter) {
    this.inningsManager = inningsManager;
    this.resultWriter = resultWriter;
  }

  public void manage() throws CricBoardException {

    Scanner sc = new Scanner(System.in);
    System.out.println("No. of players for each team: ");
    int noOfPlayers = sc.nextInt();

    System.out.println("No. of overs: ");
    int noOfOvers = sc.nextInt();

    System.out.println("Enter Team Names: ");
    String team1 = sc.next();
    String team2 = sc.next();

    ScoreBoard scoreBoard = initScoreBoard(noOfPlayers, noOfOvers, team1, team2);

    startGame(scoreBoard, team1, team2);

    resultWriter.write(scoreBoard);
  }

  private void startGame(ScoreBoard scoreBoard, String team1, String team2) {

    scoreBoard.setBattingTeam(team1);
    scoreBoard.setFieldingTeam(team2);

    registerPlayers(scoreBoard, team1, team2);

    inningsManager.manageInnings(scoreBoard);

    scoreBoard.setBattingTeam(team2);
    scoreBoard.setFieldingTeam(team1);
    inningsManager.manageInnings(scoreBoard);
  }

  private void registerPlayers(ScoreBoard scoreBoard, String team1, String team2) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Add Players for team :" + team1);

    List<String> team1PLayers = new ArrayList<>();
    for (int i = 0; i < scoreBoard.getNoOfPlayers(); i++) {
      team1PLayers.add(sc.next());
    }

    TeamScoreBoard battingTeamBoard = scoreBoard.getBattingTeamBoard();
    battingTeamBoard.addPlayers(team1PLayers);

    System.out.println("Add Players for team :" + team2);

    List<String> team2Players = new ArrayList<>();
    for (int i = 0; i < scoreBoard.getNoOfPlayers(); i++) {
      team2Players.add(sc.next());
    }

    TeamScoreBoard fieldingTeamBoard = scoreBoard.getFieldingTeamBoard();
    fieldingTeamBoard.addPlayers(team2Players);
  }

  private ScoreBoard initScoreBoard(int noOfPlayers, int noOfOvers, String team1, String team2)
      throws CricBoardException {

    ScoreBoard scoreBoard = new ScoreBoard();

    scoreBoard.setNoOfPlayers(noOfPlayers);

    scoreBoard.setTotalOvers(noOfOvers);

    if (StringUtils.isNotEmpty(team1) && StringUtils.isNotEmpty(team2)) {
      scoreBoard.addTeam(team1);
      scoreBoard.addTeam(team2);
    } else {
      throw new CricBoardException("Enter Valid Team Name");
    }

    return scoreBoard;
  }
}
