import java.util.ArrayList;

public class Tree {
	public static final int MAX_DEPTH = 4;
	
	private Node root;
	private int count = 0;
	//currentStateBoard => root (resultado de la movida del human, nextPlayer = MACHINE)
	public Tree(Board currentStateBoard){
		this.root = new Node(-1, 0, currentStateBoard);
		this.generateChildren(this.root);
	}

	private void generateChildren(Node parent) {
		for(int i=0;i<7;i++){
			Board currentBoard = new Board(-1,7,6);
			currentBoard.copyBoard(parent.getBoard());
			//currentBoard.changePlayer();
			if (currentBoard.getColumn(i)<6){//if the current column is not full (less than 6)
				currentBoard.move(i);//se hace la movida & se invierte el nextPlayer.
				Node child = new Node(i,parent.getDepth()+1, currentBoard);
				parent.addChild(child);
				//System.out.println("Board: " + ++count + "\n");
				//System.out.println("Parent:\n" + parent.getBoard() + "Child:\n" + currentBoard);
				if(child.getDepth()<MAX_DEPTH){
					this.generateChildren(child);
				}
			}
		}
	}
	
	public Node getRoot(){
		return this.root;
	}
	

}