import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	Text.clear();
	for(int i = 1; i<= WIDTH; i++){
		Text.go(1,i);
		System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
	}
	for(int i = 2; i<HEIGHT; i++){
		Text.go(i, 1);
		System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
		for(int j = 2; j<WIDTH;j++){
			if(i==6||i==23){
				Text.go(i,j);
				System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
			}
		}
		if(i>6&&i<23){
			Text.go(i,40);
			System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
		}
		Text.go(i,WIDTH);
		System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
	}
	for(int i = 1; i<=WIDTH;i++){
		Text.go(HEIGHT,i);
		System.out.print(Text.colorize(" ", BORDER_COLOR, BORDER_BACKGROUND));
	}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	if(startRow>0 && startCol>0){
		Text.go(startRow,startCol);
		System.out.println(s);
	}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	String[] lineS = text.split(" ");
	String[] otherL = new String[height];
	int currentL = 0;
	String currentText = "";
	for(String word:lineS){
		if(currentText.length()+word.length() + 1<=width){
			if(!currentText.isEmpty()){
				currentText+=" ";
			}
			currentText+=word;
		}else{
			if(currentL < height){
			otherL[currentL] = currentText;
			currentL++;
		}
		currentText = word;
	}
	}
	if(currentL<height){
		otherL[currentL] = currentText;
	}
	for(int i = 0;i<height;i++){
		if(i<otherL.length&&otherL[i] != null){
			String line = otherL[i];
			while(line.length()<width){
				line+=" ";
			}
			drawText(line.substring(0,width),row+i,col);
		}else{
			String blankL = "";
			while(blankL.length()<width){
				blankL += " ";
			}
			drawText(blankL,row+i,col);
		}
	}
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      Random rand = new Random();
        int choice = rand.nextInt(3); // Adjust for the number of subclasses
        Adventurer[] adventurers = {
            new Rogue(),
            new Guardian(),
            new Cleric(),
            new Boss()
        };
        return adventurers[rand.nextInt(adventurers.length)];
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      if(startRow > 0){
		  String[] rowData = new String[3];
		  for(int i=0;i<party.size();i++){
			  Adventurer currentA = party.get(i);
			  int col = (80/party.size())*(i+1)-(80/party.size()-2);
			  rowData[0] = currentA.getName();
			  rowData[1] = "HP: " + colorByPercent(currentA.getHP(),currentA.getmaxHP());
			  rowData[2]=currentA.getSpecialName() +": " + currentA.getSpecial();
			  for(int j = 0;j<3;j++){
				  drawText(rowData[j], startRow+j,col);
			  }
		  }
    }
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    if ((double) hp/maxHP < 0.25){
      return Text.colorize(output, Text.RED);
    }else if((double) hp/maxHP<0.75){
      return Text.colorize(output, Text.YELLOW);
    }else{
      return Text.colorize(output, Text.WHITE);
    }
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method. add instance for this one
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){

    drawBackground();

    //draw player party
	drawParty(party, 25);

    //draw enemy party
	drawParty(enemies, 2);
	Text.go(HEIGHT,1);
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(32,1);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();

      //clear the text that was written
      Text.clear();

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();

    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    enemies.add(new Boss("Frost Golem"));
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer Rogue = new Rogue();
    party.add(Rogue);
    Adventurer Guardian = new Guardian();
    party.add(Guardian);
    Adventurer Cleric = new Cleric();
    party.add(Cleric);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.
    

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    TextBox(7,2,15,5,preprompt);
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);
      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        Adventurer current = party.get(whichPlayer);
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          Adventurer target = enemies.get(whichOpponent);
          String result = current.attack(target);
          TextBox(1,1,WIDTH,1,result);
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          Adventurer target = enemies.get(whichOpponent);
          String result = current.specialAttack(target);
          TextBox(28,1,WIDTH,1,result);
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          Adventurer target = enemies.get((whichPlayer+1)%party.size());
          String result = current.specialAttack(target);
          TextBox(28,1,WIDTH,1,result);
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          TextBox(2,1,81,1,prompt);

        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";
          TextBox(31,1,81,1,prompt);
          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        Adventurer currentEnemy = enemies.get(whichOpponent);
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        Adventurer target = party.get(new Random().nextInt(party.size()));
        String result = "";
        if(new Random().nextBoolean()){
          result = currentEnemy.attack(target);
        }else{
          result = currentEnemy.specialAttack(target);
        }
        TextBox(28,1,WIDTH,1,result);
        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        TextBox(31,1,81,1,prompt);
        whichOpponent = (whichOpponent+1)%enemies.size();

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
        TextBox(31,1,81,1,prompt);
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
