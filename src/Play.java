import java.util.Scanner;


public class Play {
	
	public static final int HUMAN = 1;
	public static final int MACHINE = 2;
	
	public static void main(String[] args) {
		//Board gameBoard = new Board(MACHINE, 7, 6);
		Board gameBoard = new Board(HUMAN, 7, 6);//Un board donde el human va a hacer la movida.
		MinMaxPlayer machine = new MinMaxPlayer(gameBoard);
		
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
					System.out.println("Throwing token into column " + input);
					gameBoard.move(input);
				}
				else{
					System.out.println("Input must be a value between 0 and 6");
				}
			}
		}
		System.out.println("GAME ENDED!");
		System.out.println(gameBoard.toString());

	}

}
