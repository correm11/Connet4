
public class Board implements ConnectFourBoardInterface{
	
	private int[][] board;
	private int[] column;
	private int player;
	private int amountOfRows;
	private int amountOfColums;
	
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
		
	}
		
	@Override
	public void copyBoard(ConnectFourBoardInterface source) {
		
		this.player = source.nextPlayer();
		
		this.column = source.getColumns();
		
		this.board = source.getState();
		
	}

	@Override
	public boolean move(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nextPlayer() {
		return this.player;
	}

	@Override
	public int winner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[][] getState() {
		int[][] result = new int[this.amountOfRows][this.amountOfColums];
		
		for(int i = 0; i < this.amountOfRows; i++){
			for(int j = 0; j < this.amountOfColums; j++){
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
		
		if(column < 0 || column > this.amountOfColums){
			throw new IndexOutOfBoundsException();
		}
		
		return this.column[column];
	}	

}
