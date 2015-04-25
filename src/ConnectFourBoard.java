
public interface ConnectFourBoard {

	public void copyBoard(ConnectFourBoard source);
	
	public boolean move(int column);
	
	public int nextPlayer();
	
	public int winner();
	
	public int[][] getState();
	
	public int[] getColumns();
	
	public int getColumn(int column);
	
}