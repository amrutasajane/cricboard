package com.cricket.cricboard;

import com.cricket.cricboard.processor.MatchManager;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cricket.cricboard")
public class CricBoardManager {

  public static void main(String[] args) throws Exception {

    MatchManager matchManager = SpringContext.getMatchManager();

    matchManager.manage();
  }
}
