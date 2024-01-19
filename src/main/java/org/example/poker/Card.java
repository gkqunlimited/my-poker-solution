package org.example.poker;

public class Card implements Comparable<Card> {
    private final Value value;
    private final Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card(String s) {
        this(Value.of(s.charAt(0)), Suit.of(s.charAt(1)));
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return String.format("%s%s", value.getChar(), suit.getChar());
    }
}
