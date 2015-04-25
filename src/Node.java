import java.util.ArrayList;

public class Node {
	private int identifier;//in which column the token is thrown
	private int depth;
	//private int value;
	private Board stateBoard;
	private ArrayList<Node> children;
	
	public Node(int identifier, int depth, Board stateBoard){
		this.identifier = identifier;
		this.depth = depth;
		this.stateBoard = stateBoard;
		this.children = new ArrayList<Node>();
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
}
