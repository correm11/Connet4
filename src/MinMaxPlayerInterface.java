
public interface MinMaxPlayerInterface {

	public int getMin(Tree g);
	
	public int getMax(Tree g);
	
	public int minMaxResult(Tree g);
	
	public boolean isLeaf(Tree g);
	
	public void minMaxTurn(Tree g);
	
	public int getDepth();
	
	public int getHeuristic();
}
