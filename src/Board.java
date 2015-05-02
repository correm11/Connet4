
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
				this.changePlayer();//this.player =  al player que le toca en el siguiente turno.
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
	 * This method return -1 if the board don't have a winner else
	 * return the winner player. 
	 * 
	 * This method should be call **after** every move.
	 * 
	 */
	private void winner() {
		int lastPlayer = this.getLastPlayer();

		//check vertical
		for(int i = 0; i <  this.getAmountOfColums(); i ++){
			int colCount = 0;

			for(int j = 0; j < this.getAmountOfRows(); j++){
				if(this.board[j][i] == lastPlayer){
					colCount++;
				}
				else{
					colCount = 0;
				}

				if(colCount >= 4){
					this.winner = lastPlayer;
					return;
				}
			}						
		}

		//check horizontal
		for(int i = 0; i <  this.getAmountOfRows(); i ++){
			int rowCount = 0;

			for(int j = 0; j < this.getAmountOfColums(); j++){
				if(this.board[i][j] == lastPlayer){
					rowCount++;
				}
				else{
					rowCount = 0;
				}

				if(rowCount >= 4){
					this.winner = lastPlayer;
					return;
				}
			}						
		}

		//check diagonal left
		for(int i = 3; i < this.getAmountOfColums(); i ++){
			int diagonalLeft = 0;

			int temp = i;
			for(int j = 0; j < this.getAmountOfRows(); j++){
				if(temp < 0){
					break;
				}

				if(this.board[j][temp--] == lastPlayer){
					diagonalLeft++;
				}
				else{
					diagonalLeft = 0;
				}

				if(diagonalLeft >= 4){
					this.winner = lastPlayer;
					return;
				}
			}

			if(i == (this.getAmountOfColums() - 1)){

				for(int z = 1; z < (this.getAmountOfRows() - 3); z++){
					int temp2 = i;
					for(int j = z; j < this.getAmountOfRows(); j++){
						if(temp2 < 0){
							break;
						}

						if(this.board[j][temp2--] == lastPlayer){
							diagonalLeft++;
						}
						else{
							diagonalLeft = 0;
						}

						if(diagonalLeft >= 4){
							this.winner = lastPlayer;
							return;
						}
					}
				}
			}
		}

		//check diagonal right
		for(int i = 0; i < (this.getAmountOfColums() - 3); i ++){
			int diagonalRight = 0;

			int temp = i;
			for(int j = 0; j < this.getAmountOfRows(); j++){
				if(temp > (this.getAmountOfColums() - 1)){
					break;
				}

				if(this.board[j][temp++] == lastPlayer){
					diagonalRight++;
				}
				else{
					diagonalRight = 0;
				}

				if(diagonalRight >= 4){
					this.winner = lastPlayer;
					return;
				}
			}

			if(i == 0){

				for(int z = 1; z < (this.getAmountOfRows() - 3); z++){
					int temp2 = i;
					for(int j = z; j < this.getAmountOfRows(); j++){
						if(temp2 > (this.getAmountOfColums() - 1)){
							break;
						}

						if(this.board[j][temp2++] == lastPlayer){
							diagonalRight++;
						}
						else{
							diagonalRight = 0;
						}

						if(diagonalRight >= 4){
							this.winner = lastPlayer;
							return;
						}
					}
				}
			}
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
//		if(result){
//			System.out.println("ALL COLUMNS FULL");
//		}
//		else{
//			System.out.println("COLUMNS ARE NOT FULL");
//		}
		
		if(hasWinner())
			System.out.println("hasWinner state: \n" + this.toString());
		
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

	/**
	 * @return the amountOfRows
	 */
	public int getAmountOfRows() {
		return this.amountOfRows;
	}

	/**
	 * @return the amountOfColums
	 */
	public int getAmountOfColums() {
		return this.amountOfColums;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
