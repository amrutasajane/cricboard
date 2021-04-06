package com.cricket.cricboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStat {

    private String playerName;

    private BattingStats battingStats;

    private BowlingStats bowlingStats;
}
