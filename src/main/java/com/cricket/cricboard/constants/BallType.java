package com.cricket.cricboard.constants;

import lombok.Getter;

@Getter
public enum BallType {
    WICKET("W"),
    WIDE("Wd"),
    NO_BALL("Nb"),
    NORMAL("");

    private String shortName;

    BallType(String shortName) {
        this.shortName = shortName;
    }

    public static BallType getBallType(String shortName) {

        for (BallType ballType : BallType.values()) {

            if (ballType.getShortName().equalsIgnoreCase(shortName)) {
                return ballType;
            }
        }

        return NORMAL;
    }
}


