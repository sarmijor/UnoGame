/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * @author dancye, 2018
 * @modifier sarmijor, 2024
 */
public abstract class Player 
{
    public String playerID; //the unique ID for this player
    //private static final int maxPlayers = 4; //must create object instance if wanted to access
    //private ArrayList<String> activePlayers;
    private ArrayList<Card> hand;
    
    /**
     * A constructor that allows you to set the player's unique ID
     * @param name the unique ID to assign to this player.
     */
    public Player(String name)
    {
        this.playerID = name;
        this.hand = new ArrayList<>();
    }
    
    /**
     * @return the playerID
     */
    public String getPlayerID() 
    {
        return playerID;
    }

    /**
     * Ensure that the playerID is unique
     * @param givenID the playerID to set
     */
//    public void setPlayerID(String givenID) 
//    {
//        if(activePlayers.contains(givenID) || activePlayers.size() > maxPlayers){
//            throw new IllegalArgumentException(givenID + " is already used ");
//        }else{
//            playerID = givenID;
//            activePlayers.add(givenID);
//        }
//    }
     
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }
    
    /**
     * The method to be instantiated when you subclass the Player class
     * with your specific type of Player and filled in with logic to play your game.
     */
    public abstract void play(Game game);
    
}
