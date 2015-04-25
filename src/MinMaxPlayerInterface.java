
public interface MinMaxPlayerInterface {

	public int getMin(Graph g);
	
	public int getMax(Graph g);
	
	public int minMaxResult(Graph g);
	
	public boolean isLeaf(Graph g);
	
	public void minMaxTurn(Graph g);
	
	public int getDepth();
	
	public int getHeuristic();
}
