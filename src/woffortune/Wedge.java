/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package woffortune;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class defines one wedge of a wheel for the wheel of fortune game
 * @author Nguyen, Minhquan
 */
public class Wedge {
    
    private Wheel.WedgeType type;
    private int amount = 0;
    private String randomPrize ; //string variable
    int index = 0; //set to 0
    String[] prizeArray = new String[15]; //string array with 15 index

    /**
     * Constructor
     * @param type Wheel.WedgeType  
     */
    public Wedge(Wheel.WedgeType type) {
        this.type = type;
        if (type == Wheel.WedgeType.MONEY) {
            amount = (int)(Math.random()*20 + 1)*25;
        }
        
        if (type == Wheel.WedgeType.PRIZE) { //on the prize wedge
            //string array of prizes
            prizeArray[0] = "Stimpak";
            prizeArray[1] = "a half eaten MnM";
            prizeArray[2] = "Shishkebab";
            prizeArray[3] = "old movie ticket";
            prizeArray[4] = "left sock";
            prizeArray[5] = "broken bottle";
            prizeArray[6] = "seashell";
            prizeArray[7] = "right sock";
            prizeArray[8] = "32 penny";
            prizeArray[9] = "chair with a missing leg";
            prizeArray[10] = "dead battery";
            prizeArray[11] = "broken mirror";
            prizeArray[12] = "25 bottle caps";
            prizeArray[13] = "Nuke Cola";
            prizeArray[14] = "Quantum";
        }
    }
    /**
     * Method to get random prize
     * @return random prize from string array
     */
    public String getRandomPrize() {
        //send a random string phrase description of the prize from the index of String array
        randomPrize = prizeArray[index = (int)(Math.random()*14 -0)+1];
        return randomPrize; //return that phrase
    }

    /**
     * Getter
     * @return Wheel.WedgeType 
     */
    public Wheel.WedgeType getType() {
        return type;
    }

    /**
     * Getter
     * @return int amount (only for wedges of Wheel.WedgeType.MONEY)
     */
    public int getAmount() {
        return amount;
    }
    

    
    
    
}
