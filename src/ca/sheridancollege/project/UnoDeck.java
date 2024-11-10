/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;
//import ca.sheridancollege.project.Card.Color;
//import ca.sheridancollege.project.Card.Value;

import java.util.ArrayList;
import java.util.Random;


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
}
