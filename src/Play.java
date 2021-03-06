import java.util.Scanner;

/**
 * This class represents the game, its main method starts the game and is responsible of taking
 * user inputs.  It also displays the current board to the user throughout the terminal.
 */

public class Play {
	
	public static final int HUMAN = 1;
	public static final int MACHINE = 2;
	
	public static void main(String[] args) {
		//Board gameBoard = new Board(MACHINE, 7, 6);
		Board gameBoard = new Board(HUMAN, 7, 6);//Un board donde el human va a hacer la movida.
		MinMaxPlayer machine = new MinMaxPlayer();
		
		while(!gameBoard.hasFinished()){
			System.out.println(gameBoard.toString());
			
			if(gameBoard.nextPlayer() == MACHINE){
				machine.minMaxTurn(gameBoard);
			}
			else{
				//User inputs
				System.out.println("Please enter a column number (0-6):");
				Scanner in = new Scanner(System.in);
				int input = in.nextInt();
				if(input>=0 && input <=6){
					System.out.println("Human throwing token into column " + input);
					gameBoard.move(input);//moves and changes next player to MACHINE
				}
				else{
					System.out.println("Input must be a value between 0 and 6");
				}
			}
		}
		//Game ended:
		System.out.println("\n");
		if(gameBoard.getWinner()==HUMAN){
			System.out.println(":::::::::::::::::::YOU WON!:::::::::::::::::::");
		}
		else if(gameBoard.getWinner()==MACHINE){
			System.out.println(":::::::::::::::::MACHINE WON!:::::::::::::::::");
		}
		else{
			System.out.println("::::::::::::GAME ENDED IN A DRAW!:::::::::::::");
		}
		System.out.println(gameBoard.toString());

	}

}
