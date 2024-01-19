package org.example.utilities;

import org.example.poker.Card;
import org.example.poker.Round;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Parser implements Closeable {
    private final BufferedReader reader;

    public Parser(InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    public Round getNextRound() throws IOException {
        String nextLine = reader.readLine();
        String[] cardStrings = nextLine.split(" ");

        List<Card> cardList = new LinkedList<>();

        for(String cardString : cardStrings) {
            cardList.add(new Card(cardString));
        }

       // System.out.print(cardList.toString());

        return new Round(cardList);
    }

    public void close() throws IOException {
        reader.close();
    }

    public boolean hasNext() throws IOException {
        return reader.ready();
    }
}

