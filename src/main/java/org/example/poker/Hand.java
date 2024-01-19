package org.example.poker;

import java.util.*;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;
    private final ValueRanking ranking;

    public Hand(List<Card> cards) {
        List<Card> temp = new ArrayList<>(cards);
       // System.out.print(temp.toString());
        temp.add(new Card(Value.NULL, Suit.NULL));
        Collections.sort(temp);
        this.cards = Collections.unmodifiableList(temp);

        ValueRanking straightRanking = straightRanking();

        ranking = Objects.requireNonNullElseGet(straightRanking, this::pairRanking);
    }

    private ValueRanking straightRanking() {
        if(isStraight() && isFlush()) {
            System.out.print(cards.toString() + " Ranking.STRAIGHT_FLUSH \t\t");
            return new ValueRanking(Ranking.STRAIGHT_FLUSH, cards.get(4).getValue(), null, Collections.emptyList());
        } else if(isFlush()) {
            List<Value> kickers = new LinkedList<>();
            kickers.add(cards.get(2).getValue());
            kickers.add(cards.get(1).getValue());
            kickers.add(cards.get(0).getValue());
            System.out.print(cards + " Ranking.FLUSH \t\t\t");
            return new ValueRanking(Ranking.FLUSH, cards.get(4).getValue(), cards.get(3).getValue(), kickers);
        } else if(isStraight()) {
            System.out.print(cards.toString() + " Ranking.STRAIGHT_FLUSH \t\t");
            return new ValueRanking(Ranking.STRAIGHT_FLUSH, cards.get(4).getValue(), null, Collections.emptyList());
        } else {
            return null;
        }
    }

    private ValueRanking pairRanking() {
        Value v = null;
        int counter = 0;
        int pair = 0, trips = 0, quads = 0;

        Value primary = null;
        Value secondary = null;
        List<Value> kicker = new LinkedList<>();

        for(Card c : cards) {
            Value current = c.getValue();
            if(v != current) {
                switch (counter) {
                    case 2 -> {
                        pair++;
                        if (primary == null) {
                            primary = v;
                        } else if (trips > 0 || primary.compareTo(current) > 0) {
                            secondary = current;
                        } else {
                            secondary = primary;
                            primary = v;
                        }
                    }
                    case 3 -> {
                        trips++;
                        secondary = primary;
                        primary = v;
                    }
                    case 4 -> {
                        quads++;
                        primary = v;
                    }
                    default -> {
                        if (v != null) kicker.add(v);
                    }
                }
                v = current;
                counter = 1;
            } else {
                counter++;
            }
        }

        if(quads > 0) {
            System.out.print(cards + " Ranking.QUADS \t\t\t");
            return new ValueRanking(Ranking.QUADS, primary, secondary, kicker);
        } else if(trips > 0) {
            if(pair > 0) {
                System.out.print(cards + " Ranking.FULL_HOUSE \t");
                return new ValueRanking(Ranking.FULL_HOUSE, primary, secondary, kicker);
            } else {
                System.out.print(cards + " Ranking.TRIPS \t\t\t");
                return new ValueRanking(Ranking.TRIPS, primary, secondary, kicker);
            }
        } else if(pair == 2) {
            System.out.print(cards + " Ranking.TWO_PAIR \t\t");
            return new ValueRanking(Ranking.TWO_PAIR, primary, secondary, kicker);
        } else if(pair == 1) {
            System.out.print(cards + " Ranking.PAIR \t\t\t");
            return new ValueRanking(Ranking.PAIR, primary, secondary, kicker);
        } else {
            System.out.print(cards + " Ranking.HIGH_CARD \t\t");
            return new ValueRanking(Ranking.HIGH_CARD, primary, secondary, kicker);
        }
    }

    private boolean isStraight() {
        for(int i = 0; i < 4; i++) {
            if(cards.get(i).getValue().ordinal() + 1 != cards.get(i+1).getValue().ordinal()) return false;
        }
        return true;
    }

    private boolean isFlush() {
        for(int i = 0; i < 4; i++) {
            if(cards.get(i).getSuit() != cards.get(i+1).getSuit()) return false;
        }
        return true;
    }

    @Override
    public int compareTo(Hand o) {
        return ranking.compareTo(o.ranking);
    }
}
