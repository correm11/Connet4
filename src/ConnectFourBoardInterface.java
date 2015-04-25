
public interface ConnectFourBoardInterface {
	
	//manuel
	public void copyBoard(ConnectFourBoardInterface source);
	//juan
	public boolean move(int column);
	//manuel
	public int nextPlayer();
	//juan
	public int winner();
	//manuel
	public int[][] getState();
	//juan
	public int[] getColumns();
	//manuel
	public int getColumn(int column);
	
}
