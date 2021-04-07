package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BowlingStats {

  private String overs;

  private int balls;

  private int maiden;

  private int runsConceded;

  private int wickets;

  private double economy;

  public void addRun(int runs) {
    this.runsConceded += runs;
    this.balls+=1;
    this.economy = runsConceded / 6;
  }
}
