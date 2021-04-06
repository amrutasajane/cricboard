package com.cricket.cricboard.model;

import com.cricket.cricboard.constants.BattingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BattingStats {

    private BattingStatus battingStatus;

    private int runs;

    private int ballFaced;

    private int fours;

    private int sixes;

    private double strikeRate;

    public BattingStats() {
        this.battingStatus = BattingStatus.YET_TO_BAT;
    }

    public void addRuns(int run) {
        this.runs += run;

        this.ballFaced += 1;

        if (run == 4) {
            this.fours += 1;
        } else if (run == 6) {
            this.sixes += 1;
        }

        this.strikeRate = (runs / ballFaced) * 100;
    }

    @Override
    public String toString() {

        return String.format("%10s%10s%10s%10s", runs, fours, sixes, ballFaced);

    }
}
