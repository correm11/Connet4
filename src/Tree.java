import java.util.ArrayList;

public class Tree {
	public static final int MAX_DEPTH = 4;
	class Node{
		private int identifier;//in which column the token is thrown
		private int depth;
		//private int value;
		private Board stateBoard;
		private ArrayList<Node> children;
		
		private Node(int identifier, int depth, Board stateBoard){
			this.identifier = identifier;
			this.depth = depth;
			this.stateBoard = stateBoard;
			this.children = new ArrayList<Node>();
			numNodes++;
		}
		
		private void addChild(Node childNode){
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
	
	private Node root;
	private int numNodes = 0;
	private int operationNumber = 1;
	
	public Tree(Board currentStateBoard){
		this.root = new Node(-1, 0, currentStateBoard);
		//System.out.println("("+ operationNumber++ + ") "+"Root:"+
		//"("+-1+","+ this.root.depth+")" + "Node Num = " + this.numNodes);
		this.generateChildren(this.root);
	}

	private void generateChildren(Node parent) {
		for(int i=0;i<7;i++){
			Board currentBoard = new Board(1,7,6);
			currentBoard.copyBoard(parent.getBoard());
			if (currentBoard.getColumn(i)<6){//if the current column is not full (less than 6)
				currentBoard.move(i);
				Node child = new Node(i,parent.getDepth()+1, currentBoard);
				parent.addChild(child);
				//System.out.println("("+ operationNumber++ + ") "+"Add child:"+
				//"("+i+","+ child.depth+")" + "Node Num = " + this.numNodes);
				
				if(child.getDepth()<MAX_DEPTH){
					this.generateChildren(child);
				}
			}
		}
	}
	
	public Node getRoot(){
		return this.root;
	}
	
	public int getNumNodes(){
		return this.numNodes;
	}
	
	

}