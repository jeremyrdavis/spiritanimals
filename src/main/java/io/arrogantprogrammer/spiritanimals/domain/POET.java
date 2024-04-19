package io.arrogantprogrammer.spiritanimals.domain;

import java.util.Random;

public enum POET {

    ROBERT_FROST("Robert Frost"),
    EMILY_DICKINSON("Emily Dickinson"),
    WALT_WHITMAN("Walt Whitman"),
    LANGSTON_HUGHES("Langston Hughes"),
    MAYA_ANGELLOU("Maya Angelou"),
    RUMI("Rumi"),
    PABLO_NERUDA("Pablo Neruda"),
    WILLIAM_SHAKESPEARE("William Shakespeare"),
    SYLVIA_PLATH("Sylvia Plath"),
    EDGAR_ALLAN_POE("Edgar Allan Poe");

    public final String name;

    POET(String name) {
        this.name = name;
    }

    public static String randomPoet() {
        return POET.values()[new Random().nextInt(POET.values().length - 1) + 1].name;
    }
}
