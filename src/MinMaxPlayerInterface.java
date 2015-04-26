
public interface MinMaxPlayerInterface {

	public int getMin(Node n);
	
	public int getMax(Node n);
	
	public int minMaxResult(Node n);
	
	public void minMaxTurn(Board gameBoard);
	
	public int getUtilityValue(Node n);
}
