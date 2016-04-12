import java.util.LinkedHashMap;

public class Player {
	
	// private member variables
	public String name;
	public int currPos;
	private static String[] flag = {"No snake or ladder!", "Bitten by a snake!", "Climbed a ladder!", "Another chance given!" };	

	// Constructor
	public Player (String name, int currPos) {
		  this.name = name;
	      this.currPos = currPos;
	 }

  	 static LinkedHashMap<Integer, Integer> snake = Snakes_Ladders.getSnakesMap(Snakes_Ladders.initializeBoard(Snakes_Ladders.squarePieces));
  	 static LinkedHashMap<Integer, Integer> ladder = Snakes_Ladders.getLaddersMap(Snakes_Ladders.initializeBoard(Snakes_Ladders.squarePieces));



 	 // member function; all rules of game implemented here
 	public int playTurn (int diceOutcome) {	
		   System.out.println("\tPrevious Position at: "+currPos);
		   System.out.println("\tDice Roll Outcome: "+diceOutcome);
		   
  	  	   currPos = currPos + diceOutcome;
   		   if (currPos > 100) {										// out of Board bounds
		       currPos = currPos - diceOutcome;					// no change in current position then
		       System.out.println("\tCurrent Position at: "+currPos+"\n");
 		   }
 		   else if(currPos == 100) {								// no further play
			   System.out.println("\tCurrent Position at: "+currPos+"\n");
 			   return currPos;
 		   } 
  		   else if (snake.containsKey(currPos)) {					// maps to the current position if its a snake head
 			    currPos = snake.get(currPos);						// get value and set it as current position
			    System.out.println("\tEvent Occurred: "+flag[1]);		// bitten by a snake
 			    System.out.println("\tCurrent Position at: "+currPos+"\n");
 		   }
 		   else if (ladder.containsKey(currPos)) {					// maps to the current position if its a ladder bottom
 			    currPos = ladder.get(currPos);						// get value and set it as current position
			    System.out.println("\tEvent Occurred: "+flag[2]);		// climbed up a ladder
 			    System.out.println("\tCurrent Position at: "+currPos+"\n");
				System.out.println("\t"+name+" climbed a ladder. "+flag[3]);	// given another chance to roll the die
 	 			diceOutcome = Snakes_Ladders.rollDice();
 	 			playTurn(diceOutcome);
 		   } 	
 		   else {
 			   //System.out.println("Event Occurred: "+flag[0]);				// no snake or ladder
 			   System.out.print("\tCurrent Position at: "+currPos+"\n");
	       }
  		  
 		   while (diceOutcome == 6)  {
				System.out.println("\t"+name+" scored a 6. "+flag[3]+"\n");		// given another chance to roll the die
		   		diceOutcome = Snakes_Ladders.rollDice();
	 			playTurn(diceOutcome);
		 	} 
 		   return currPos;
 	   }
 }
 	
