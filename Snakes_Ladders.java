import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


//A Thread Class
class MyRunnableThread implements Runnable {
	 
	public MyRunnableThread(){
      
 }
 public void run() 		
 {
	 
	 // Instantiate a Random object "r"
	    Random r = new Random();
	    int number;
	    int[] round = new int[100];

    	for(int i=0; i<100; i++) {
       	    // Random.nextInt(int) returns a value from 0 (inclusive)
    	    // up to but excluding the int passed into the method.
    	    // (3 is excluded), +1 for inclusion
    		number = r.nextInt(3) + 1;	// b/w 1-3
    		if (number == 1) 
	    		round[i] = Snakes_Ladders.twoPlayers();

    		if (number == 2) 
	    		round[i] = Snakes_Ladders.threePlayers();

    		if (number == 3) 
	    		round[i] = Snakes_Ladders.maxPlayers();

    	}

    	int maxValue = round[0];
    	for(int i=1; i<round.length; i++) {	
	        if(round[i] > maxValue) { 		
	            maxValue = round[i]; 
	         }
    	}
	    System.out.println("Max rounds: " + maxValue);	    
	    
    	int minValue = round[0];
    	for(int i=1; i<round.length; i++) {	
	        if(round[i] < minValue) { 
	        	minValue = round[i]; 
	         }
    	}
	    System.out.println("Min rounds: " + minValue);
	    	    
    	int sum = 0; int avg = 0;
    	for(int i=0; i<round.length; i++) {	
    		sum += round[i];
    	}
	    System.out.println("Sum of rounds: " + sum);

    	avg = sum/round.length;
    	
	    System.out.println("Avg rounds: " + avg);

	    for(int i=0; i<round.length; i++) 
    		System.out.print("\t" + round[i]); 
	   	}
 }

////////////////////////////////////

public class Snakes_Ladders {
	
	public static final int squarePieces = 100;	// constant no. of square pieces in Board
	public static LinkedHashMap<Integer, Integer> map_snakes = new LinkedHashMap<Integer, Integer>();
	public static LinkedHashMap<Integer, Integer> map_ladders = new LinkedHashMap<Integer, Integer>();
	public static int[] Board;

	public static void main(String[] args) {
		

		int playerCount = 0;								
		System.out.println("Enter the total no. of players:");
		Scanner input = new Scanner(System.in);
		playerCount = input.nextInt(); 		
		
		if ((playerCount > 1) && (playerCount < 3))				// 2 Players
			twoPlayers();		
		else if ((playerCount > 1) && (playerCount < 4))		// 3 Players
			threePlayers();

		else if ((playerCount > 1) && (playerCount < 5))		// 4 Players
			maxPlayers();
		else
			System.out.println("Please select players b/w 1-4.");
		
		
		  //Creating an object of the first thread
	      /* MyRunnableThread   firstThread = new MyRunnableThread();

		   //Starting the first thread
		   Thread thread1 = new Thread(firstThread);
		   thread1.start();		
		           
		    try {
		   	 thread1.join();
		    } catch (InterruptedException e) {
		   	 e.printStackTrace();
		    } */
	}

	/* Initializing a Board at beginning of game */
	public static int[] initializeBoard (final int squarePieces) {
		Board = new int[squarePieces];
		// initializing board
		for (int i = 0; i < Board.length; i++) 
			Board[i] = i+1;	
		//getSnakesMap(Board);
		//getLaddersMap(Board);
		//printMaps();
		return Board;
	}
	
	/* Locating snakes' heads and tails onto Board */
    public static LinkedHashMap<Integer, Integer> getSnakesMap(int[] Board) {
    	map_snakes.put(Board[16], Board[6]);
		map_snakes.put(Board[61], Board[18]);
		map_snakes.put(Board[63], Board[59]);
		map_snakes.put(Board[53], Board[33]);
		map_snakes.put(Board[86], Board[35]);
		map_snakes.put(Board[92], Board[72]);
		map_snakes.put(Board[94], Board[74]);
		map_snakes.put(Board[97], Board[78]);
        return map_snakes;
   }
	
	/* Locating ladders' bottom and top onto Board */
    public static LinkedHashMap<Integer, Integer> getLaddersMap(int[] Board) {
    	map_ladders.put(Board[0], Board[37]);
		map_ladders.put(Board[3], Board[13]);
		map_ladders.put(Board[8], Board[30]);
		map_ladders.put(Board[20], Board[41]);
		map_ladders.put(Board[27], Board[83]);
		map_ladders.put(Board[50], Board[66]);
		map_ladders.put(Board[71], Board[90]);
		map_ladders.put(Board[79], Board[98]);
        return map_ladders;
   }
    
   /* Prints both snakes and ladders map by iterating over the map entries */ 
   public static void printMaps () {

	     // Get a set of the entries
	     Set<?> set_snakes = map_snakes.entrySet();
	     // Get an iterator
	     Iterator<?> i = set_snakes.iterator();
	     // Display elements
	     System.out.println("Snake Head" + " -> "+ "Snake Tail");
	     while(i.hasNext()) {
		         Map.Entry me = (Map.Entry)i.next();
		         System.out.print(me.getKey() + " -> ");
		         System.out.println(me.getValue());
	     }
	     
	     Set<?> set_ladders = map_ladders.entrySet();
	     // Get an iterator
	     Iterator<?> i1 = set_ladders.iterator();
	     // Display elements
	     System.out.println("Ladder Bottom" + " -> "+ "Ladder Top");
	     while(i1.hasNext()) {
	         Map.Entry me = (Map.Entry)i1.next();
	         System.out.print(me.getKey() + " -> ");
	         System.out.println(me.getValue());
	      }
    }
    
   public static int rollDice() {
	   int randomInt = 0;
	   int START = 1;
	   int END = 6;
	   Random random = new Random();
	   randomInt = showRandomInteger(START, END, random);
	   return randomInt;
   }
   
   private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);
	    return randomNumber;
	  }
   
   /* Game Play b/w 2 players */
   public static int twoPlayers() {							// climbed a ladder and 6 wala wrong, one extra chance neing given which is wrong
	    Player player_1 = new Player("RED", 0);	
		Player player_2 = new Player("GREEN", 0);
		int rounds = 0;
				
		while ((player_1.currPos < 100) || (player_2.currPos < 100)) {
			System.out.println("\n"+player_1.name+"'s Turn"); 
			player_1.currPos = player_1.playTurn(rollDice());
			//System.out.println("RED at: "+player_1.currPos); 		
			if (player_1.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_1.name); 
				rounds ++;
				break;
			}
			System.out.println("\n"+player_2.name+"'s Turn"); 
			player_2.currPos = player_2.playTurn(rollDice());		
			//System.out.println("GREEN at: "+player_2.currPos); 
			if (player_2.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_2.name); 
				rounds ++;
				break;
			}
			System.out.println("---------------------------------------------------------"); 
			rounds ++;
			System.out.println("\t\tRound # "+rounds+" Completed."); 
			System.out.println("---------------------------------------------------------"); 

		}
		
		System.out.println("---------------------------------------------------------"); 
		System.out.println("\t\tTotal no. of rounds: "+rounds);
		System.out.println("---------------------------------------------------------");
		return rounds;
  }
   
   /* Game Play b/w 3 players */
   public static int threePlayers() {
	    Player player_1 = new Player("RED", 0);	
		Player player_2 = new Player("GREEN", 0);
		Player player_3 = new Player("BLUE", 0);
		int rounds = 0;
		
		while ((player_1.currPos < 100) || (player_2.currPos < 100) || (player_3.currPos < 100)) {
			System.out.println("\n"+player_1.name+"'s Turn"); 
			player_1.currPos = player_1.playTurn(rollDice());
			//System.out.println("RED at: "+player_1.currPos); 		
			if (player_1.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_1.name); 
				rounds++;
				break;
			}
			System.out.println("\n"+player_2.name+"'s Turn"); 
			player_2.currPos = player_2.playTurn(rollDice());		
			//System.out.println("GREEN at: "+player_2.currPos); 
			if (player_2.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_2.name); 
				rounds++;
				break;
			}
			System.out.println("\n"+player_3.name+"'s Turn"); 
			player_3.currPos = player_3.playTurn(rollDice());		
			//System.out.println("BLUE at: "+player_3.currPos); 
			if (player_3.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_3.name); 
				rounds++;
				break;
			}
			System.out.println("---------------------------------------------------------"); 
			rounds ++;
			System.out.println("\t\tRound # "+rounds+" Completed."); 
			System.out.println("---------------------------------------------------------"); 
		}
		System.out.println("---------------------------------------------------------"); 
		System.out.println("\t\tTotal no. of rounds: "+rounds);
		System.out.println("---------------------------------------------------------");		
		return rounds;
  }
   
   /* Game Play b/w 4 players */
   public static int maxPlayers() {
	    Player player_1 = new Player("RED", 0);				
		Player player_2 = new Player("GREEN", 0);
		Player player_3 = new Player("BLUE", 0);
		Player player_4 = new Player("YELLOW", 0);
		int rounds = 0;
		
		while ((player_1.currPos < 100) || (player_2.currPos < 100) || (player_3.currPos < 100)|| (player_4.currPos < 100)) {
			System.out.println("\n"+player_1.name+"'s Turn"); 
			player_1.currPos = player_1.playTurn(rollDice());
			//System.out.println("RED at: "+player_1.currPos); 		
			if (player_1.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_1.name); 
				rounds++;
				break;
			}
			System.out.println("\n"+player_2.name+"'s Turn"); 
			player_2.currPos = player_2.playTurn(rollDice());		
			//System.out.println("GREEN at: "+player_2.currPos); 
			if (player_2.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_2.name); 
				rounds++;
				break;
			}
			System.out.println("\n"+player_3.name+"'s Turn"); 
			player_3.currPos = player_3.playTurn(rollDice());		
			//System.out.println("BLUE at: "+player_3.currPos); 
			if (player_3.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_3.name); 					
				rounds++;
				break;
			}
			System.out.println("\n"+player_4.name+"'s Turn"); 
			player_4.currPos = player_4.playTurn(rollDice());		
			//System.out.println("YELLOW at: "+player_4.currPos); 
			if (player_4.currPos == 100) {
				System.out.println("\nWe have a WINNER: "+player_4.name);
				rounds++;
				break;
			}
			System.out.println("---------------------------------------------------------"); 
			rounds++;
			System.out.println("\t\tRound # "+rounds+" Completed."); 
			System.out.println("---------------------------------------------------------"); 
		}
		System.out.println("---------------------------------------------------------"); 
		System.out.println("\t\tTotal no. of rounds: "+rounds);
		System.out.println("---------------------------------------------------------"); 
		return rounds;
   }
}
