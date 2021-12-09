package edu.iu.c212.places.games.blackjack;

public class BlackjackDealer extends BlackjackParticipant {

    private String shownCard;
    private int dealerBest = -1;

    public BlackjackDealer() {
        super(new int[] { 0, 0 });

        shownCard = hit();
        hit();
    }

    @Override
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

    public String getPartialHand() {
        if (totals[0] > 21 && totals[1] > 21) return "Bust!";

        return shownCard + " + ???";
    }

    public void play() {
        while (totals[0] <= 16 || totals[1] <= 16) {
            hit();
            dealerBest = getBestTotal() > 21 ? -1 : getBestTotal();
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
