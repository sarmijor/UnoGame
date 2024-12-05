/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jordansarmiento
 */
public class UnoPlayer extends Player {

    private Scanner input;

    public UnoPlayer(String name) {
        super(name);
        input = new Scanner(System.in);
    }

    @Override
    public void play(Game game) {
        // Tells player what card is in discard pile
        Card topCard = game.getTopCard();
        System.out.println(getPlayerID() + "'s turn. \nTop card: " + topCard);
        System.out.println("Your hand:");
        
        // Displays the cards in hand of player
        ArrayList<Card> hand = getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }

        boolean cardPlayed = false;
        while (!cardPlayed) {
            // Beginning of players turn
            System.out.println("Choose a card to play by entering a number from hand (1-x) or type 'draw' to draw a card:");
            String choice = input.nextLine();
            
            // Draw card if not playable or plays card if draws a playable card
            if (choice.equalsIgnoreCase("draw")) {
                Card drawnCard = game.drawCard();
                addCard(drawnCard);
                System.out.println("You drew: " + drawnCard + "\n");
                if (game.isPlayable(drawnCard, topCard)) {
                    game.playCard(this, drawnCard);
                    cardPlayed = true;
                } else {
                    System.out.println("You cannot play this card, you turn is passed onto next player");
                    cardPlayed = true; 
                }
            } else {
                try {
                    int cardIndex = Integer.parseInt(choice) - 1;
                    Card chosenCard = getHand().get(cardIndex);
                    if (game.isPlayable(chosenCard, topCard)) {
                        game.playCard(this, chosenCard);
                        cardPlayed = true;
                    } else {
                        System.out.println("Invalid move! You can't play this card.");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Please choose a valid card number or input 'draw'.");
                }
            }
        }

        if (getHand().size() == 1) {
            System.out.println(getPlayerID() + " said UNO!");
        } else if (getHand().isEmpty()) {
            System.out.println(getPlayerID() + " won the game!");
            game.endGame();
        }
    }
}
