package com.cricket.cricboard.processor;

import com.cricket.cricboard.model.ScoreBoard;

public interface ResultWriter {

  void write(ScoreBoard scoreBoard);
}
