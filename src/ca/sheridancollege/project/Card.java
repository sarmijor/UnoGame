/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code 
 * should remember to add themselves as a modifier.
 * @author dancye, 2018
 * @modifier sarmijor, 2024-11-08
 */
public class Card 
{
    //default modifier for child classes
    
    /**
     * Students should implement this method for their specific children classes 
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    
    public enum Color{
        RED, BLUE, GREEN, YELLOW, WILD;
        
        //getter to access color values before object is created
        //cannot have a setter since enums are immutable/final
        private static Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }
    
    public enum Value{
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAWTWO, SKIP, REVERSE, WILD, WILDFOUR;
        
        //getter to access card number values before object is created
        private static Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }
    
    private Value value;
    private Color color;
    
    public Card(Color color, Value value){
        this.value = value;
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        return color + " " + value;
    }
    
}
