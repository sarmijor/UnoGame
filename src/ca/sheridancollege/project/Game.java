/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific
 * child of this class and instantiate the methods given.
 * @author dancye, 2018
 * @modifier sarmijor, 2024-11-08
 */

public class Game{
 
    private final String gameName;
    private UnoDeck deck;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private boolean isClockwise;
    private Card topCard;
    private Scanner input;

    public Game(String gameName) {
        this.gameName = gameName;
        this.players = new ArrayList<>();
        this.deck = new UnoDeck();
        this.currentPlayerIndex = 0;
        this.isClockwise = true;
        this.input = new Scanner(System.in);
    }

    public void addPlayer(Player player) {
        if (players.size() < 4) {
            players.add(player);
        } else {
            System.out.println("Maximum number of players reached.");
        }
    }

    public void startGame() {
        deck.restart();
        deck.shuffle();   
        // Deal 7 cards to each player 
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            } 
        } 
        // Takes a card from deck instead of a player placing a card down first to match a card
        topCard = deck.drawCard();
        System.out.println("Game started with initial card: " + topCard);
        // Loop to check if any player has no cards left in hand
        while (!isGameOver()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.play(this); 
            nextPlayer(); 
        } 
        
        System.out.println("Game over! Winner: " + declareWinner());
    }
    
    public Card getTopCard() {
        return topCard;
    }

    public Card drawCard() {
        return deck.drawCard();
    }

    public boolean isPlayable(Card card, Card topCard) {
        return card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue() || card.getColor() == Card.Color.WILD;
    }

    public void playCard(Player player, Card card) {
        topCard = card;
        player.removeCard(card);
        handleSpecialCard(card);
    }

    private void nextPlayer() {
        if (isClockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    private void handleSpecialCard(Card card) {
        switch (card.getValue()) {
            case REVERSE:
                isClockwise = !isClockwise;
                break;
            case SKIP:
                nextPlayer();
                break;
            case DRAWTWO:
                nextPlayer();
                players.get(currentPlayerIndex).addCard(deck.drawCard());
                players.get(currentPlayerIndex).addCard(deck.drawCard());
                break;
            case WILDFOUR:
                System.out.println("Choose a color (RED, BLUE, GREEN, YELLOW): ");
                String color = input.nextLine().toUpperCase();
                topCard = new Card(Card.Color.valueOf(color), Card.Value.WILDFOUR);
                nextPlayer();
                for (int i = 0; i < 4; i++) {
                    players.get(currentPlayerIndex).addCard(deck.drawCard());
                }
                break;
            case WILD:
                System.out.println("Choose a color (RED, BLUE, GREEN, YELLOW): ");
                color = input.nextLine().toUpperCase();
                topCard = new Card(Card.Color.valueOf(color), Card.Value.WILD);
                break;
        }
    }

    private boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public String declareWinner() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player.getPlayerID();
            }
        }
        return "Uno Game has ended";
    }

    public void endGame() {
        System.exit(0); 
    }
}//end class
