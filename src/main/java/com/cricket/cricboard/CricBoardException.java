package com.cricket.cricboard;

public class CricBoardException extends Exception {

  private static final long serialVersionUID = 1L;

  public CricBoardException(String message) {
    super(message);
  }

  public CricBoardException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public CricBoardException(Throwable throwable) {
    super(throwable);
  }
}
