
public class Board implements ConnectFourBoardInterface{
	
	private int[][] board;
	private int[] column;
	private int player;
	
	/**
	 * Constructor
	 */
	public Board(int player) {
		
		this.board = new int[6][7];
		this.column = new int[7];
		this.player = player;
		
	}

	@Override
	public void copyBoard(ConnectFourBoardInterface source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean move(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nextPlayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int winner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[][] getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumn(int column) {
		// TODO Auto-generated method stub
		return 0;
	}	

}
