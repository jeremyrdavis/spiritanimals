package io.arrogantprogrammer.spiritanimals.domain;

import java.util.Random;

public enum POETICADDITION {
    KITTENS("kittens"),
    LAWNGNOMES("lawn gnomes"),
    PUPPIES("puppies"),
    UNICORNS("unicorns"),
    KLINGONS("Klingons"),
    TAYLORSWIFT("Taylor Swift"),
    JAVASCRIPT("JavaScript"),
    SINGLEMALTTSCOTCH("single malt Scotch"),
    EDDIEMURPHY("Eddie Murphy"),
    HARRYSTYLES("Harry Styles"),
    HARRYKANE("Harry Kane"),
    BEEFJERKY("beef jerky"),
    ROLLERCOASTERS("roller coasters"),
    VEGANBLUEBERRYIMUFFINS("vegan blueberry muffins");

    public final String addition;

    POETICADDITION(String addition) {
        this.addition = addition;
    }

    public static POETICADDITION randomAddition() {
        return POETICADDITION.values()[new Random().nextInt(POETICADDITION.values().length)];
    }

    public String addition() {
        return this.addition;
    }
}
