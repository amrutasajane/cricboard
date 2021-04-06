package com.cricket.cricboard.processor;

import com.cricket.cricboard.CricBoardException;
import com.cricket.cricboard.model.ScoreBoard;
import com.cricket.cricboard.model.TeamScoreBoard;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Scanner;

@Named
public class MatchManager {

  private InningsManager inningsManager;

  @Inject
  public MatchManager(InningsManager inningsManager) {
    this.inningsManager = inningsManager;
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

    inningsManager.manageInnings(scoreBoard, team1);

    inningsManager.manageInnings(scoreBoard, team2);

    printMatchResults(scoreBoard, team1, team2);
  }

  private void printMatchResults(ScoreBoard scoreBoard, String team1, String team2) {

    TeamScoreBoard team1Score = scoreBoard.getTeamScore(team1);

    TeamScoreBoard team2Score = scoreBoard.getTeamScore(team2);

    if (team1Score.getTotalScore() > team2Score.getTotalScore()) {
      int diff = team1Score.getTotalScore() - team2Score.getTotalScore();
      System.out.println("Team 1 won the match by " + diff + " runs");

    } else if (team1Score.getTotalScore() == team2Score.getTotalScore()) {
      System.out.println("Match Tied");

    } else {

      int wkt = scoreBoard.getNoOfPlayers() - team2Score.getWickets() - 1;
      System.out.println("Team 2 won the match by " + wkt + " wickets");
    }
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
