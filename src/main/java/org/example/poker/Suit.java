package org.example.poker;

import java.util.HashMap;
import java.util.Map;

public enum Suit {
    HEART ('H'),
    DIAMOND ('D'),
    SPADE ('S'),
    CLUB ('C'),
    NULL ('x');

    private final char suitChar;

    private static final Map<Character, Suit> valueMap = new HashMap<>();

    static {
        for(Suit c : Suit.values()) {
            valueMap.put(c.getChar(), c);
        }
    }

    Suit(char cardString) {
        this.suitChar = cardString;
    }

    public char getChar() {
        return suitChar;
    }

    public static Suit of(char value) {
        Suit s = valueMap.get(value);
        if(s == null) return NULL;
        return s;
    }
}
