package edu.iu.c212.places.games.blackjack;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BlackjackGame extends Game {

    private Arcade arcade;

    User user;
    BlackjackPlayer player;
    BlackjackDealer dealer;

    // GUI
    static JFrame frame;
    static JButton hit;
    static JButton stay;
    static JLabel totalsLabel;
    static JLabel dealerLabel;

    public BlackjackGame(Arcade a) {
        arcade = a;
    }

    private class HitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            player.hit();
            totalsLabel.setText("You have " + player.getCurrentTotalsString());

            if (player.totals[0] > 21) {
                hit.setEnabled(false);
                stay.setEnabled(false);

                dealerLabel.setText("Dealer has " + dealer.getBestTotal());

                JOptionPane.showMessageDialog(frame, "You lost!");
            }
        }
    }

    private class StayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            hit.setEnabled(false);
            stay.setEnabled(false);

            dealer.play();

            int playerTotal = player.getBestTotal();
            int dealerTotal = dealer.getBestTotal();

            dealerLabel.setText("Dealer has " + (dealerTotal > 21 ? "Bust!" : dealerTotal));
            totalsLabel.setText("You have " + playerTotal);

            if (dealerTotal > 21) {
                JOptionPane.showMessageDialog(frame, "You won $50.00!");
                user.setBalance(user.getBalance() + 50);
            } else {
                JOptionPane.showMessageDialog(frame, playerTotal > dealerTotal ? "You won $50.00!" : "You Lost!");

                if (playerTotal > dealerTotal) user.setBalance(user.getBalance() + 50);
            }
        }
    }

    @Override
    public Arcade getArcade() {
        return null;
    }

    @Override
    public double getEntryFee() {
        return 20;
    }

    @Override
    public String getPlaceName() {
        return "Blackjack";
    }

    public void onEnter(User user) {
        this.user = user;
        player = new BlackjackPlayer();
        dealer = new BlackjackDealer();

        frame = new JFrame("Blackjack");
        JPanel mainPanel = new JPanel();

        JPanel statusPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        mainPanel.add(statusPanel);
        mainPanel.add(buttonsPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        String dealerValue = dealer.getPartialHand();
        dealerLabel = new JLabel("Dealer has " + dealerValue);
        statusPanel.add(dealerLabel);

        String totalsValue = player.getCurrentTotalsString();
        totalsLabel = new JLabel("You have " + totalsValue);
        statusPanel.add(totalsLabel);

        hit = new JButton("Hit");
        stay = new JButton("Stay");
        buttonsPanel.add(hit);
        buttonsPanel.add(stay);

        hit.addActionListener(new HitButtonListener());
        stay.addActionListener(new StayButtonListener());

        frame.add(mainPanel);

        frame.setSize(250, 125);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                frame.dispose();
                arcade.transitionArcadeState("Lobby");
            }
        });

        frame.setVisible(true);
    }

    @Override
    public String toString() {
        return "Name: " + getPlaceName() + " | Entry Fee: " + getEntryFee() + " | Game: True";
    }
}
