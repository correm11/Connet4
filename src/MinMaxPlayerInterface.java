
public interface MinMaxPlayerInterface {

	public int getMin(Node n);
	
	public int getMax(Node n);
	
	public int minMaxResult(Node n);
	
	public void minMaxTurn(Node n);
	
	public int getDepth();
	
	public int getUtilityValue(Node n);
}
