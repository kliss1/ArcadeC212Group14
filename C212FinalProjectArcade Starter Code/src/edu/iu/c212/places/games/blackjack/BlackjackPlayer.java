package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackjackPlayer extends BlackjackParticipant {

    private static final ArrayList<String> deck = new ArrayList<>(Arrays.asList(
            "A", "A", "A", "A",
            "1", "1", "1", "1",
            "2", "2", "2", "2",
            "3", "3", "3", "3",
            "4", "4", "4", "4",
            "5", "5", "5", "5",
            "6", "6", "6", "6",
            "7", "7", "7", "7",
            "8", "8", "8", "8",
            "9", "9", "9", "9",
            "10", "10", "10", "10",
            "J", "J", "J", "J",
            "Q", "Q", "Q", "Q",
            "K", "K", "K", "K"
    ));

    public BlackjackPlayer() {
        super(deck, new int[] { 0, 0 });

        hit();
        hit();
    }

    public String getCurrentTotalsString() {
        int total1 = this.totals[0];
        int total2 = this.totals[1];

        if (total1 > 21 && total2 > 21) {
            return "Bust!";
        }

        if (total1 != total2) {
            if (total1 <= 21 && total2 <= 21) {
                return total1 + " | " + total2;
            } else {
                return String.valueOf(total1 <= 21 ? total1 : total2);
            }
        } else {
            return String.valueOf(total1);
        }
    }

    public int getBestTotal() {
        if (totals[0] > 21 && totals[1] > 21) {
            return Math.min(totals[0], totals[1]);
        } else {
            if (totals[0] > totals[1]) {
                if (totals[0] <= 21) return totals[0];
                else return totals[1];
            } else {
                if (totals[1] <= 21) return totals[1];
                else return totals[0];
            }
        }
    }
}
