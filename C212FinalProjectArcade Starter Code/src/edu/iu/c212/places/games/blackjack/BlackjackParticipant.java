package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class BlackjackParticipant {

    protected static ArrayList<String> cards = new ArrayList<>();
    protected int[] totals;

    public BlackjackParticipant(ArrayList<String> deck, int[] totals) {
        cards = deck;
        this.totals = totals;
    }

    public BlackjackParticipant(int[] totals) {
        this.totals = totals;
    }

    public String hit() {
        String card = cards.get((int)(Math.random() * cards.size()));

        if (card.equals("A")) {
            totals[0] += 1;
            totals[1] += 11;
        } else {
            int value = getCardValue(card);
            totals[0] += value;
            totals[1] += value;
        }

        cards.remove(card);

        return card;
    }

    public abstract int getBestTotal();

    public int getCardValue(String card) {
        return switch (card) {
            case "J", "Q", "K", "10" -> 10;
            default -> Integer.parseInt(card);
        };
    }
}
