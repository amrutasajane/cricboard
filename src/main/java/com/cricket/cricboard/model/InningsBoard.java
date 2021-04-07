package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InningsBoard {

  private Map<Integer, ScoreBoard> inningsBoard;
}
