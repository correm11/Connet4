import java.util.ArrayList;

public class Node {
	private int identifier;//in which column the token is thrown
	private int depth;
	private int minMaxValue;//propagated min max value or utility value (if leaf node)
	private Board stateBoard;
	private ArrayList<Node> children;
	
	public Node(int identifier, int depth, Board stateBoard){
		this.identifier = identifier;
		this.depth = depth;
		this.stateBoard = stateBoard;
		this.children = new ArrayList<Node>();
		this.minMaxValue = -2;
	}
	
	public void addChild(Node childNode){
		this.children.add(childNode);
	}
	
	public Board getBoard(){
		return this.stateBoard;
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public ArrayList<Node> getChildren(){
		return this.children;
	}
	
	public boolean isLeaf(){
		return this.depth == Tree.MAX_DEPTH;
	}
	
	public int getMinMaxValue(){
		return this.minMaxValue;
	}
	
	public int getIdentifier(){
		return this.identifier;
	}
	
	public void setMinMaxValue(int minMaxValue){
		this.minMaxValue = minMaxValue;
	}
}
