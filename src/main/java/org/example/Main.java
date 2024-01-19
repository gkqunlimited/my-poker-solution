package org.example;

import org.example.poker.Round;
import org.example.utilities.Parser;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String... args) throws IOException {
        InputStream is = Main.class.getResourceAsStream("/poker-hands.txt");
        Parser p = new Parser(is);

        int counter1 = 0;
        int counter2 = 0;
        while(p.hasNext()) {
            Round r = p.getNextRound();
            if(r.playerOneWins()){
                counter1++;
                System.out.println("Player 1 wins");
            }else{
                counter2++;
                System.out.println("Player 2 wins");
            }

        }

        System.out.println("Player 1: " + counter1 +" hands");
        System.out.println("Player 2: " + counter2 +" hands");

        p.close();
    }
}






