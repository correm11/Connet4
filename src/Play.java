import java.util.Scanner;


public class Play {

	public static void main(String[] args) {
		Board gameBoard = new Board(1, 7, 6);
		MinMaxPlayer machine = new MinMaxPlayer(gameBoard);
		
		while(!gameBoard.hasFinished()){
			System.out.println(gameBoard.toString());
			
			if(gameBoard.nextPlayer() == 0){
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

	}

}
