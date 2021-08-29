/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package woffortune;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * WofFortuneGame class
 * Contains all logistics to run the game
 * @author Nguyen, Minhquan
 */
public class WofFortuneGame {

    private boolean puzzleSolved = false;

    private Wheel wheel;
    private Player player1;
    private String phrase = "Once upon a time";
    //private Letter[] letter_array = new Letter[16];
   
    private ArrayList<Letter> letterList = new ArrayList<Letter>();  //store each letter in phrase into arrayList one a time
    private ArrayList<String> storedPhrase = new ArrayList<String>(); //arrayList storing all premade phrase
    private ArrayList<Player> playerName = new ArrayList<Player>(); //arrayList store all player name

    /**
     * Constructor
     * @param wheel Wheel 
     * @throws InterruptedException 
     */
    public WofFortuneGame(Wheel wheel) throws InterruptedException {
        // get the wheel
        this.wheel = wheel;
        
        // do all the initialization for the game
        setUpGame();
        

    }
    
    /**
     * Plays the game
     * @throws InterruptedException 
     */
    public void playGame() throws InterruptedException {
        // while the puzzle isn't solved, keep going
        
        while (!puzzleSolved){
            // let the current player play
            for(Player a : playerName)
            { 
                playTurn(a); //for each Player object, there is a name in array to it
            }

        }
    }
    /**
     * Method to store array of phrases into ArrayList
     */
    private void premadePhrase(){
        String[] phrases = new String[10]; //string array to store 10 phrases
        phrases[0] = "you can do this";
        phrases[1] = "this is easy";
        phrases[2] = "no man land";
        phrases[3] = "aint no thang but a chicken wang";
        phrases[4] = "i like fish";
        phrases[5] = "i want an A";
        phrases[6] = "drink some water";
        phrases[7] = "wake up earlier";
        phrases[8] = "what does water taste like";
        phrases[9] = "pass the class";
        
        for (String s : phrases){ //for each phrase, store it into string variable S
            storedPhrase.add(s); //add each string stored in S into arrayList
        }
    }
    
    /**
     * Sets up all necessary information to run the game
     */
    private void setUpGame() {
        // create a single player 
        premadePhrase();  //call method that stored all premade phrases into arrayList
        int num = 0; //initialize to 0
        //player1 = new Player("Player1");  
        Scanner player = new Scanner(System.in); //scanner take user input    
        try{ //try this
            System.out.println("How many will be player?");   //output duh!    
            num = player.nextInt(); //take input from user in INT
        }catch(InputMismatchException error){ //now catch any error
            System.out.println("Error! Enter an integer"); //if there's error
            setUpGame(); //start setUpGame all over again
        }
        for(int j = 0; j<num; j++){  //for loop of amount of player, get their name
            Scanner sc = new Scanner(System.in); //duh!
            String name = null; //initialize to null
            try{ //try this
                System.out.println("What is player "+j+" name?"); //ask each player's name in ascending order
                name = sc.nextLine(); //string input
            }catch(Exception e){} //catch 
            player1 = new Player(name); //new object variable to the Player class/object
            playerName.add(player1); //each time a name is entered, store it into the arrayList of player's name
            
            //System.out.println(name); //check if it's right
        }       
        
 
        // print out the rules
        System.out.println("RULES!");
        System.out.println("Each player gets to spin the wheel, to get a number value");
        System.out.println("Each player then gets to guess a letter. If that letter is in the phrase, ");
        System.out.println(" the player will get the amount from the wheel for each occurence of the letter");
        System.out.println("If you have found a letter, you will also get a chance to guess at the phrase");
        System.out.println("Each player only has three guesses, once you have used up your three guesses, ");
        System.out.println("you can still guess letters, but no longer solve the puzzle.");
        System.out.println();
        
        Scanner sc = new Scanner(System.in); //do i have to tell you what this do ?
        System.out.println("Would you like to enter your own Phrase?");
        char answer = sc.next().charAt(0); //user input in character
            if ((answer == 'y') || (answer == 'Y')){  //check condition
                Scanner newPhrase = new Scanner(System.in); //...
                System.out.println("Enter a new phrase"); //...
                phrase = newPhrase.nextLine(); //store new phrase into the String variable phrase
            }
            else{ //if no
                Random rand = new Random(); //variable declaration to get random number
                int index = rand.nextInt(9-0)+1; //declare an integer variable that will hold the random number generated between 0 and 10              
                ////////System.out.println("random index: "+index);    //checking my work          
                phrase = storedPhrase.get(index); //set the String variable phrase to store the premade phrase inside of a random index of arrayList         
                /////////System.out.println("stored string: "+phrase);                                  
            }
       
        /* //for each character in the phrase, create a letter and add to letters array
        for (int i = 0; i < phrase.length(); i++) {
            letter_array[i] = new Letter(phrase.charAt(i));
        }  */
        
        for(int i=0;i<phrase.length();i++) //for loop to bring take out letter from a phrase, one letter at at time
        {
            Letter a = new Letter(phrase.charAt(i)); //variable object reference of Letter, store 1 letter of the phrase into this variable
            letterList.add(a); //add the letter inside the Letter variable into arrayList that store 1 letter of the phrase into each index
            ///////System.out.println(i+"  "+phrase.charAt(i));

        } 
        // setup done
    }
    
    /**
     * One player's turn in the game
     * Spin wheel, pick a letter, choose to solve puzzle if letter found
     * @param player
     * @throws InterruptedException 
     */
    private void playTurn(Player player) throws InterruptedException {
        try{ //try this
            int money = 0; //initialize money to 0
            String prize = null; //initialize to null
            Scanner sc = new Scanner(System.in); //...uhhhh it's obvious
            boolean spinPrize = false; //set a boolean to check if player had land on a prize to false

            System.out.println(player.getName() + ", you have $" + player.getWinnings()); //print out player name and their current winning sum
            System.out.println("Spin the wheel! <press enter>");
            sc.nextLine();
            System.out.println("<SPINNING>");
            Thread.sleep(200);
            Wheel.WedgeType type = wheel.spin();
            System.out.print("The wheel landed on: ");
            switch (type) {
                case MONEY:
                    money = wheel.getAmount();
                    System.out.println("$" + money);
                    break;

                case LOSE_TURN:
                    System.out.println("LOSE A TURN");
                    System.out.println("So sorry, you lose a turn.");
                    return; // doesn't get to guess letter


                case BANKRUPT:
                    System.out.println("BANKRUPT");
                    player.bankrupt();
                    return; // doesn't get to guess letter
   
                case PRIZE: //new case, new PRIZE wedge
                    System.out.println("PRIZE"); //let player know they land on PRIZE wedge
                    prize = wheel.getPrize(); //send the String phrase of a randomly selected prize from string prize pool in Wedge class
                    System.out.println("The prize is: " + prize); //print out what that prize is
                    spinPrize = true; //if they land on prize wedge then bool is set to true
                    break;
               
                default:

            }
            System.out.println("");
            System.out.println("Here is the puzzle:");
            showPuzzle();
            System.out.println();
            System.out.println(player.getName() + ", please guess a letter.");
            //String guess = sc.next();
            char letter = sc.next().charAt(0);
            if (!Character.isAlphabetic(letter)) {
                System.out.println("Sorry, but only alphabetic characters are allowed. You lose your turn.");
            } else {
                // search for letter to see if it is in
                int numFound = 0;
                for (Letter l : letterList) {
                    if ((l.getLetter() == letter) || (l.getLetter() == Character.toUpperCase(letter))) {
                        l.setFound();
                        numFound += 1;
                    }
                }
                if (numFound == 0) {
                    System.out.println("Sorry, but there are no " + letter + "'s.");
                } else {
                    if (numFound == 1) {
                        System.out.println("Congrats! There is 1 letter " + letter + ":");
                    } else {
                        System.out.println("Congrats! There are " + numFound + " letter " + letter + "'s:");
                    }
                    System.out.println();
                    showPuzzle();
                    System.out.println();
                    player.incrementScore(numFound*money);
             
                    
                    if(spinPrize == true && numFound > 0){ //condition check if a player had spun a PRIZE and if they guessed more than 1 letter correct
                        System.out.println("You won the prize: " + prize); //print out the prize they won
                        player.prizeWon(prize); //send the winning prize into a method inside the Player class that stored it into an arrayList that hold all player's prize
                        spinPrize = false; // set prize won to false again to reset prize wedge
                    }
                    System.out.println("You earned $" + (numFound*money) + ", and you now have: $" + player.getWinnings());
                    

                    System.out.println("Would you like to try to solve the puzzle? (Y/N)");
                    letter = sc.next().charAt(0);
                    System.out.println();
                    if ((letter == 'Y') || (letter == 'y')) {
                        solvePuzzleAttempt(player);
                    }
                }


            }
        }catch(InterruptedException error){ //catch any error
            System.out.println("Error!");
        }
        
    }
    
    /**
     * Logic for when user tries to solve the puzzle
     * @param player 
     */
    private void solvePuzzleAttempt(Player player) {
        
        if (player.getNumGuesses() >= 3) {
            System.out.println("Sorry, but you have used up all your guesses.");
            return;
        }
        
        
        player.incrementNumGuesses();
        System.out.println("What is your solution?");
        System.out.println("Please enter it in all CAP");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String guess = sc.next();
        if (guess.compareToIgnoreCase(phrase) == 0) {
            System.out.println("Congratulations! You guessed it!");
            puzzleSolved = true;
            // Round is over. Write message with final stats
            // TODO
            
            System.out.println(player.getName() +" you win this game"); //print out the winner's name
            for(Player eachPlayer : playerName){ //for each player's name, there's a player
                //print out each player's name, their winning, and any prize they won during the game
                System.out.println(eachPlayer.getName() +" you won $: " + eachPlayer.getWinnings()+" your prizes"+ eachPlayer.getPrize());
            }         

        } else {
            System.out.println("Sorry, but that is not correct.");
        }
    }
    
    /**
     * Display the puzzle on the console
     */
    private void showPuzzle() {
        System.out.print("\t\t");
        for (Letter l : letterList) {
            if (l.isSpace()) {
                System.out.print("   ");
            } else {
                if (l.isFound()) {
                    System.out.print(Character.toUpperCase(l.getLetter()) + " ");
                } else {
                    System.out.print(" _ ");
                }
            }
        }
        System.out.println();
        
    }
    
    /**
     * For a new game reset player's number of guesses to 0
     */
    public void reset() {
        player1.reset();
    }
  
}
