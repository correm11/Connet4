
/**
 * This class are in charge of manage the board of the game. This class
 * has the task of perform the moves in the board. Also the class
 * display and verify one of the player has win.
 * 
 * @author all members
 *
 */
public class Board implements ConnectFourBoardInterface{

	private int[][] board;
	private int[] column;
	private int player;
	private int amountOfRows;
	private int amountOfColums;
	private int winner;
	private int pos_x, pos_y;

	/**
	 * Constructor
	 * @param amountOfColumns 
	 * @param amountOfRows
	 * @param player 
	 */
	public Board(int player, int amountOfColumns, int amountOfRows) {

		this.amountOfColums = amountOfColumns;
		this.amountOfRows = amountOfRows;
		this.board = new int[amountOfRows][amountOfColumns];
		this.column = new int[amountOfColumns];
		this.player = player;
		this.winner = -1;
		this.pos_y = 0;
		this.pos_x = 0;

	}

	@Override
	public void copyBoard(ConnectFourBoardInterface source) {

		this.player = source.nextPlayer();

		this.column = source.getColumns();

		this.board = source.getState();

	}

	/* 
	 * This method perform the moves in the board.
	 * 
	 */
	@Override
	public boolean move(int column) {
		if ( (column < 0) || (column > this.getAmountOfColums() - 1)){
			System.out.println("invalid input\n\n");
			return false;
		}
		else{
			if ((this.column[column] == this.getAmountOfColums() - 1)){
				System.out.println("Column " + column + " full");
				return false;
			}
			else{
				this.pos_y=column;
				this.pos_x= (this.getAmountOfColums() - 2) - this.column[column];
				this.column[column]++; 
				this.board[this.pos_x][this.pos_y] = this.player; //el player que juega este state.
				this.changePlayer();//this.player =  al player que le toca en el siguiente turno.(le toca decidir partiendo de este state que dejo el player anterior)
				//winner method verify if the game has a winner
				this.winner();

				return true;
			}
		}
	}
	
	public int getMoveRow(){
		return this.pos_x;
	}
	
	public int getMoveCol(){
		return this.pos_y;
	}

	/*
	 * Method to change the player.
	 */
	public void changePlayer() {
		if(this.player == 1){
			this.player = 2;
		}
		else{
			this.player = 1;
		}
	}

	private int getLastPlayer(){
		if(this.player == 1){
			return 2;
		}
		else{
			return 1;
		}
	}

	@Override
	public int nextPlayer() {
		return this.player;
	}

	/* 
	 * This method make winner to -1 if the board don't have a winner else
	 * return the winner player. 
	 * 
	 * This method should be call **after** every move.
	 * 
	 */
	private void winner() {
		int lp = this.getLastPlayer();
		int col = this.pos_y;
		int row = this.pos_x;
		int s[][] = this.board;
		/////Horizontal/////
		//Contando 4 fichas (winning turn)
		if(col>=3 && s[row][col-1] == lp && s[row][col-2] == lp && s[row][col-3] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=2 && col<=5 && s[row][col-1] == lp && s[row][col-2] == lp && s[row][col+1] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=1 && col<=4 && s[row][col-1] == lp && s[row][col+1] == lp && s[row][col+2] == lp ){
			this.winner = lp;
			return;
		}
		if(col<=3 && s[row][col+1] == lp && s[row][col+2] == lp && s[row][col+3] == lp ){
			this.winner = lp;
			return;
		}
		
		/////Vertical/////
		//Contando 4 fichas (winning turn)
		if(row>=3 && s[row-1][col] == lp && s[row-2][col] == lp && s[row-3][col] == lp ){
			this.winner = lp;
			return;
		}
		if(row>=1 && row<=3 && s[row-1][col] == lp && s[row+1][col] == lp && s[row+2][col] == lp ){
			this.winner = lp;
			return;
		}
		if(row>=2 && row<=4 && s[row-1][col] == lp && s[row-2][col] == lp && s[row+1][col] == lp ){
			this.winner = lp;
			return;
		}
		if(row<=2 && s[row+1][col] == lp && s[row+2][col] == lp && s[row+3][col] == lp ){
			this.winner = lp;
			return;
		}
		
		/////Diagonals//////
		
		//Contando 4 fichas (\)
		if(col>=3 && row>=3 && s[row-1][col-1] == lp && s[row-2][col-2] == lp && s[row-3][col-3] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=2 && col<=5 && row>=2 && row<=4 && s[row-1][col-1] == lp && s[row-2][col-2] == lp && s[row+1][col+1] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=1 && col<=4 && row>=1 && row<=3 && s[row-1][col-1] == lp && s[row+1][col+1] == lp && s[row+2][col+2] == lp ){
			this.winner = lp;
			return;
		}
		if(col<=3 && row<=2 && s[row+1][col+1] == lp && s[row+2][col+2] == lp && s[row+3][col+3] == lp ){
			this.winner = lp;
			return;
		}
		
		//Contando 4 fichas(/)
		if(col<=3 && row>=3 && s[row-1][col+1] == lp && s[row-2][col+2] == lp && s[row-3][col+3] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=1 && col<=4 && row>=2 && row<=4 && s[row-1][col+1] == lp && s[row-2][col+2] == lp && s[row+1][col-1] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=2 && col<=5 && row>=1 && row<=3 && s[row-1][col+1] == lp && s[row+1][col-1] == lp && s[row+2][col-2] == lp ){
			this.winner = lp;
			return;
		}
		if(col>=3 && row<=2 && s[row+1][col-1] == lp && s[row+2][col-2] == lp && s[row+3][col-3] == lp ){
			this.winner = lp;
			return;
		}
		

		this.winner = -1;
	}

	public boolean hasWinner(){
		return this.winner != -1;
	}

	public int getWinner(){
		return this.winner;
	}
	
	public int[][] getBoardState(){
		return this.board;
	}
	
	public boolean hasFinished(){
		boolean result = true;
		
		for(int i = 0; i < this.column.length; i++){
			if(this.getColumn(i) < this.getAmountOfRows()){ //amountOfRows = 6
				result = false;
			}
		}
//		if(hasWinner()){
//			System.out.println("Winner = " + this.getWinner() + " with state:\n" + this.toString());
//		}
		
		return hasWinner() || result;
	}

	@Override
	public int[][] getState() {//makes a copy
		int[][] result = new int[this.getAmountOfRows()][this.getAmountOfColums()];

		for(int i = 0; i < this.getAmountOfRows(); i++){
			for(int j = 0; j < this.getAmountOfColums(); j++){
				result[i][j] = this.board[i][j];
			}
		}

		return result;
	}

	@Override
	public int[] getColumns() {
		int[] result = new int[this.column.length];

		for(int i = 0; i < this.column.length; i++){
			result[i] = this.getColumn(i);
		}

		return result;
	}

	@Override
	public int getColumn(int column) {

		if(column < 0 || column > (this.getAmountOfColums() - 1)){
			throw new IndexOutOfBoundsException();
		}

		return this.column[column];
	}

	public int getAmountOfRows() {
		return this.amountOfRows;
	}

	public int getAmountOfColums() {
		return this.amountOfColums;
	}

	@Override
	public String toString() {
		String result = "    ";
		
		for(int i = 0; i < this.getAmountOfColums(); i++){						
			if(i == (this.getAmountOfColums() - 1)){
				result += i + "\n";
			}
			else{
				result += i + " , ";
			}
		}

		for(int i = 0; i < this.getAmountOfRows(); i++){
			result += (this.getAmountOfRows() - i) + " [ "; 
			for(int j = 0; j < this.getAmountOfColums(); j++){
				if(j == (this.getAmountOfColums() - 1)){
					result += this.board[i][j] + " ]\n";
				}
				else{
					result += this.board[i][j] + " , ";
				}
			}
		}
		
		return result;
	}

}
