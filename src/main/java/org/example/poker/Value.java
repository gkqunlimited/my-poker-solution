package org.example.poker;

import java.util.HashMap;
import java.util.Map;
public enum Value {
    TWO ('2'),
    THREE ('3'),
    FOUR ('4'),
    FIVE ('5'),
    SIX ('6'),
    SEVEN ('7'),
    EIGHT ('8'),
    NINE ('9'),
    TEN ('T'),
    JACK ('J'),
    QUEEN ('Q'),
    KING ('K'),
    ACE ('A'),
    NULL ('x');

    private final char valueChar;

    private static final Map<Character, Value> valueMap = new HashMap<>();

    static {
        for(Value c : Value.values()) {
            valueMap.put(c.getChar(), c);
        }
    }

    Value(char cardString) {
        this.valueChar = cardString;
    }

    public char getChar() {
        return valueChar;
    }

    public static Value of(char value) {
        Value v = valueMap.get(value);
        if(v == null) return NULL;
        return v;
    }
}
