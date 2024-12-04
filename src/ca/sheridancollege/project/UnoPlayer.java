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
    private boolean saidUno;

    public UnoPlayer(String name) {
        super(name);
        input = new Scanner(System.in);
        saidUno = false; // False until player says "uno" and becomes true
    }

    @Override
    public void play(Game game) {
        Card topCard = game.getTopCard();
        System.out.println(getPlayerID() + "'s turn. \nTop card: " + topCard);
        System.out.println("Your hand:");
        
        ArrayList<Card> hand = getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }

        boolean cardPlayed = false;
        while (!cardPlayed) {
            // Checking if the player says "UNO"
            if (hand.size() == 2 && !saidUno) {
                System.out.println("You have 2 cards left. Type 'uno' before playing your card.");
                String unoInput = input.nextLine();
                if (unoInput.equalsIgnoreCase("uno")) {
                    saidUno = true;
                } else {
                    System.out.println("You forgot to say 'UNO'! You must now draw 2 cards.");
                    hand.add(game.drawCard());
                    hand.add(game.drawCard());
                    saidUno = false; 
                    break; 
                } 
            }
            System.out.println("Choose a card to play by entering a number from hand (1-x) or type 'draw' to draw a card:");
            String choice = input.nextLine();

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
            System.out.println(getPlayerID() + " says UNO!");
        } else if (getHand().isEmpty()) {
            System.out.println(getPlayerID() + " wins the game!");
            game.endGame();
        }
    }
}
