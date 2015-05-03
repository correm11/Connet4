import java.util.Scanner;


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
		if(gameBoard.getWinner()==HUMAN){
			System.out.println("YOU WON!");
		}
		else if(gameBoard.getWinner()==MACHINE){
			System.out.println("MACHINE WON!");
		}
		else{
			System.out.println("GAME ENDED WITH TIE!");
		}
		System.out.println(gameBoard.toString());

	}

}
