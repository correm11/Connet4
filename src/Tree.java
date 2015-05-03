public class Tree {
	public static final int MAX_DEPTH = 6;
	
	private Node root;
	private int count = 0;
	//currentStateBoard => root (resultado de la movida del human, nextPlayer = MACHINE)
	public Tree(Board currentStateBoard){
		this.root = new Node(-1, 0, currentStateBoard);
		//System.out.println("Board(root): " + ++count + "\n");
		//System.out.println(this.root.getBoard());
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
				count++;
//				if(child.isLeaf()){
//					System.out.println("Board (leaf): " + ++count + "\n");
//					System.out.println(child.getBoard());
//				}
//				else{
//					System.out.println("Board: " + ++count + "\n");
//					System.out.println(child.getBoard());
//				}

				if(child.getDepth()<MAX_DEPTH){
					this.generateChildren(child);
				}
			}
		}
	}
	
	public Node getRoot(){
		return this.root;
	}
	
	public void iterate(Node parent){
		for(Node child: parent.getChildren()){
			//System.out.println("Value("+ count++ +") = " + child.getMinMaxValue());
			if(child.isLeaf()){
				return;
			}
			else{
				iterate(child);
			}
		}
	}
	
	public int getNumNodes(){
		return this.count;
	}
	

}