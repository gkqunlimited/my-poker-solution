package org.example.poker;

import java.util.List;

public class Round {
    public final Hand playerOne;
    public final Hand playerTwo;

    public Round(List<Card> cardList) {
        List<Card> firstCards = cardList.subList(0, 5);
        this.playerOne = new Hand(firstCards);
        List<Card> secondCards = cardList.subList(5, 10);
        this.playerTwo = new Hand(secondCards);
    }

    public boolean playerOneWins() {
        return playerOne.compareTo(playerTwo) > 0;
    }
}
