/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author jordansarmiento
 */
public class UnoGame {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of players (2-4): ");
        int numOfPlayers = input.nextInt();
        input.nextLine(); 
        
        Game unoGame = new Game("UNO Game");
        for (int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Enter name for Player " + i + ": ");
            String playerName = input.nextLine();
            unoGame.addPlayer(new UnoPlayer(playerName)); 
        } 
        unoGame.startGame();
    }
}
