
public class MinMaxPlayer implements MinMaxPlayerInterface {

	private Tree currentTree;
	
	public MinMaxPlayer(Board gameBoard) {
		this.currentTree = new Tree(gameBoard);
	}

	@Override
	public int getMin(Node n) {
		if(n.isLeaf() || n.getBoard().hasFinished()){//if a leaf or game ended
			return this.getUtilityValue(n);
		}
		else{
			n.setMinMaxValue(Integer.MAX_VALUE);
			for(Node child: n.getChildren()){
				int maxValue = getMax(child);
				if(maxValue < n.getMinMaxValue()){
					n.setMinMaxValue(maxValue);
				}
			}
			return n.getMinMaxValue();
		}
	}

	@Override
	public int getMax(Node n) {
		if(n.isLeaf() || n.getBoard().hasFinished()){//if a leaf or game ended
			return this.getUtilityValue(n);
		}
		else{
			n.setMinMaxValue(Integer.MIN_VALUE);
			for(Node child: n.getChildren()){
				int minValue = getMin(child);
				if(minValue > n.getMinMaxValue()){
					n.setMinMaxValue(minValue);
				}
			}
			return n.getMinMaxValue();
		}
	}

	@Override
	public int minMaxResult(Node n) {
		int nMinMaxValue = 0;
		if(n.getBoard().nextPlayer()==0){
			nMinMaxValue = this.getMin(n);
		}
		else{
			nMinMaxValue = this.getMax(n);
		}
		int moveTo = 0;
		for(Node child: n.getChildren()){
			if(child.getMinMaxValue()==nMinMaxValue){
				//move to the node with that minimax value
				moveTo = child.getIdentifier();
				break;
			}
		}
		return moveTo;
	}

	@Override
	public void minMaxTurn(Board currentGameBoard) {
		//Board newBoard = new Board(1,7,6);
		//newBoard.copyBoard(currentGameBoard);
		this.currentTree = new Tree(currentGameBoard);//creates a tree with its children (4 levels) and the root node that contains the newBoard
		int moveTo = this.minMaxResult(this.currentTree.getRoot());
		currentGameBoard.move(moveTo);
	}


	@Override
	public int getUtilityValue(Node n) {
		// TODO Auto-generated method stub
		
		
		
		return 0;
	}	

}
