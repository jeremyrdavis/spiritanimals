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
    VEGANZUCCINIMUFFINS("vegan blueberry muffins");

    public final String addition;

    POETICADDITION(String addition) {
        this.addition = addition;
    }

    public static String addition() {
        return POETICADDITION.values()[new Random().nextInt(POETICADDITION.values().length - 1) + 1].addition;
    }
}
