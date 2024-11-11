/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
//import ca.sheridancollege.project.Card.Color;
//import ca.sheridancollege.project.Card.Value;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import javax.swing.ImageIcon;


/**
 *
 * @author jordansarmiento
 */
public class UnoDeck {
    private Card[] cards;
    private int deckCards;
    
    public UnoDeck(){
        cards = new Card[108];
    }
    
    public void restart(){
        Card.Color[] colors = Card.Color.values();
        deckCards = 0;
        
        for(int i = 0; i < colors.length - 1; i++){
            
            Card.Color color = colors[i];
            
            //Each color has only one 0 
            cards[deckCards++] = new Card(color, Card.Value.getValue(0));
            
            for(int j = 1; j < 10; j++){
                //Each color has two of the same number from 1-9
                cards[deckCards++] = new Card(color, Card.Value.getValue(j));
                cards[deckCards++] = new Card(color, Card.Value.getValue(j));
            }
            
            Card.Value[] values = new Card.Value[]{
                Card.Value.DRAWTWO,
                Card.Value.REVERSE,
                Card.Value.SKIP
            };
            //Creates array of for the non-number cards associated to the colors
            for(Card.Value value : values){
                cards[deckCards++] = new Card(color, value);
                cards[deckCards++] = new Card(color, value);
            }
        }
        
        //WILD cards are not connected to a specific color like the others
        Card.Value[] values = new Card.Value[]{
            Card.Value.WILD,
            Card.Value.WILDFOUR
        };

        for (Card.Value value : values) {
            for (int i = 0; i < 4; i++) {
                cards[deckCards++] = new Card(Card.Color.WILD, value);
            }
        }
    }
    
    //replaces the cards within the deck into an arraylist of stockpile cards
    public void replaceDeckWith(ArrayList<Card> cards) {
        this.cards = cards.toArray(new Card[cards.size()]);
        this.deckCards = this.cards.length;
    }

    //checks if there are no more cards to pick from deck
    public boolean isEmpty() {
        return deckCards == 0;
    }

    public void shuffle() {
        int num = cards.length;
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            //Randomly get a value index from the 108 cards
            int randomValue = i + random.nextInt(num - i);
            //draw a random card value into each index
            Card randomCard = cards[randomValue];
            //place the random card into the deck from indexes 0-107
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
    
    //Declares an error exception when there is no card is said elements within the array
    public Card drawCard() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("There are no more cards to draw");
        }
        return cards[--deckCards]; //draws a card and decrements deck by 1
    }
    
    public ImageIcon drawCardImage() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("No cards left in deck to draw");
        }
        return new ImageIcon(cards[--deckCards].toString() + ",png");
    }
    
    //Declares an error exception when an illegal argument is passed to the method
    //where the UNO game rules does not allow cards to be drawn
    public Card[] drawCard(int num){
        if (num < 0){
            throw new IllegalArgumentException("Can't draw less than 1 card");
        }
        if (num > deckCards){
            throw new IllegalArgumentException("Can't draw a card if there is only " + deckCards + "cards left");
        }
        
        //returning cards to deck if there are none for player to draw from
        Card[] returningCards = new Card[num];
        
        for(int i = 0; i < num; i++){
            returningCards[i] = cards[--deckCards];
        }
        return returningCards;
    }
}
