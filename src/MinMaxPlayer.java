
public class MinMaxPlayer implements MinMaxPlayerInterface {
	private Tree currentTree;

	public MinMaxPlayer() {
		//this.currentTree = new Tree(new Board(Play.HUMAN,7,6));
		//this.currentTree = new Tree(gameBoard);
	}

	@Override
	public int getMin(Node n) {
		if(n.isLeaf() || n.getBoard().hasFinished()){//if a leaf or game ended
			//System.out.println("DONE!!!!!!: getMin from: \n" + n.getBoard().toString());
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
			//System.out.println("DONE!!!!!!: getMax from: \n" + n.getBoard().toString());
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
	//n=> root node of the current tree
	public int minMaxResult(Node n) {
		int nMinMaxValue = this.getMax(n);
		//this.currentTree.iterate(n);
		//System.out.println("MINMAX VALUE = " + nMinMaxValue);
		int moveTo = 0;
		//System.out.println("Children: " + n.getChildren().size());
		//System.out.println("MINMAX VALUE = " + nMinMaxValue);
		for(Node child: n.getChildren()){
			//System.out.println("Iterando...");
			//System.out.println("Identifier = " + child.getIdentifier());
			//System.out.println("child min max value = " + child.getMinMaxValue());
			if(child.getMinMaxValue()==nMinMaxValue){
				//move to the node with that minimax value
				moveTo = child.getIdentifier();
				System.out.println("Machine throwing token into column  " + moveTo);
				break;
			}
		}
		//		while(n.getBoard().getColumn(moveTo) >= 6){
		//			moveTo++;
		//		}
		return moveTo;
	}

	@Override
	//currentGameBoard => un board que hiso el HUMAN (resultado de la movida del humano) & el nextPlayer es el MACHINE.
	public void minMaxTurn(Board currentGameBoard) {
		//Board newBoard = new Board(Play.MACHINE,7,6);
		Board newBoard = new Board(-1,7,6);
		newBoard.copyBoard(currentGameBoard);
		this.currentTree = new Tree(newBoard);//creates a tree with its children (4 levels) and the root node that contains a copy of currentBoard
		//System.out.println("Total number of nodes generated: " + this.currentTree.getNumNodes());
		int moveTo = this.minMaxResult(this.currentTree.getRoot());
		currentGameBoard.move(moveTo);
	}


	@Override
	public int getUtilityValue(Node n) {
		int h = 0;
		int[][] s = n.getBoard().getBoardState();
		int col = n.getBoard().getMoveCol();
		int row = n.getBoard().getMoveRow();
		int ma = Play.MACHINE;
		int hu = Play.HUMAN;

		if(!n.getBoard().hasFinished()){ //game is not over, calculate value of h.
			/////Horizontal/////
			//Contando 4 fichas (winning turn)
			if(col>=3 && s[row][col-1] == ma && s[row][col-2] == ma && s[row][col-3] == ma ){
				h+=400;
			}
			if(col>=2 && col<=5 && s[row][col-1] == ma && s[row][col-2] == ma && s[row][col+1] == ma ){
				h+=400;
			}
			if(col>=1 && col<=4 && s[row][col-1] == ma && s[row][col+1] == ma && s[row][col+2] == ma ){
				h+=400;
			}
			if(col<=3 && s[row][col+1] == ma && s[row][col+2] == ma && s[row][col+3] == ma ){
				h+=400;
			}

			//Contando 3 fichas
			if(col>=2 && s[row][col-1] == ma && s[row][col-2] == ma ){
				h+=300;
			}
			if(col<=4 && s[row][col+1] == ma && s[row][col+2] == ma){
				h+=300;
			}
			if(col>=1 && col<=5 && s[row][col-1] == ma && s[row][col+1] == ma){
				h+=300;
			}

			//Contando 2 fichas
			if(col>=1 && s[row][col-1] == ma){
				h+=200;
			}
			if(col<=5 && s[row][col+1] == ma){
				h+=200;
			}

			/////Vertical/////
			//Contando 4 fichas (winning turn)
			if(row>=3 && s[row-1][col] == ma && s[row-2][col] == ma && s[row-3][col] == ma ){
				h+=400;
			}
			if(row>=1 && row<=3 && s[row-1][col] == ma && s[row+1][col] == ma && s[row+2][col] == ma ){
				h+=400;
			}
			if(row>=2 && row<=4 && s[row-1][col] == ma && s[row-2][col] == ma && s[row+1][col] == ma ){
				h+=400;
			}
			if(row<=2 && s[row+1][col] == ma && s[row+2][col] == ma && s[row+3][col] == ma ){
				h+=400;
			}

			//Contando 3 fichas
			if(row>=2 && s[row-1][col] == ma && s[row-2][col] == ma){
				h+=300;
			}
			if(row>=1 && row<=4 && s[row-1][col] == ma && s[row+1][col] == ma){
				h+=300;
			}
			if(row<=3 && s[row+1][col] == ma && s[row+2][col] == ma){
				h+=300;
			}

			//Contando 2 fichas
			if(row>=1 && s[row-1][col] == ma){
				h+=200;
			}
			if(row<=4 &&  s[row+1][col] == ma){
				h+=200;
			}

			/////Diagonals//////

			//Contando 4 fichas (\)
			if(col>=3 && row>=3 && s[row-1][col-1] == ma && s[row-2][col-2] == ma && s[row-3][col-3] == ma ){
				h+=400;
			}
			if(col>=2 && col<=5 && row>=2 && row<=4 && s[row-1][col-1] == ma && s[row-2][col-2] == ma && s[row+1][col+1] == ma ){
				h+=400;
			}
			if(col>=1 && col<=4 && row>=1 && row<=3 && s[row-1][col-1] == ma && s[row+1][col+1] == ma && s[row+2][col+2] == ma ){
				h+=400;
			}
			if(col<=3 && row<=2 && s[row+1][col+1] == ma && s[row+2][col+2] == ma && s[row+3][col+3] == ma ){
				h+=400;
			}

			//Contando 3 fichas
			if(col>=2 && row>=2 && s[row-1][col-1] == ma && s[row-2][col-2] == ma){
				h+=300;
			}
			if(col>=1 && col<=5 && row>=1 && row<=4 && s[row-1][col-1] == ma && s[row+1][col+1] == ma){
				h+=300;
			}
			if(col<=4 && row<=3 && s[row+1][col+1] == ma && s[row+2][col+2] == ma){
				h+=300;
			}

			//Contando 2 fichas
			if(col>=1 && row>=1 && s[row-1][col-1] == ma){
				h+=200;
			}
			if(col<=5 && row<=4 && s[row+1][col+1] == ma){
				h+=200;
			}

			//Contando 4 fichas(/)
			if(col<=3 && row>=3 && s[row-1][col+1] == ma && s[row-2][col+2] == ma && s[row-3][col+3] == ma ){
				h+=400;
			}
			if(col>=1 && col<=4 && row>=2 && row<=4 && s[row-1][col+1] == ma && s[row-2][col+2] == ma && s[row+1][col-1] == ma ){
				h+=400;
			}
			if(col>=2 && col<=5 && row>=1 && row<=3 && s[row-1][col+1] == ma && s[row+1][col-1] == ma && s[row+2][col-2] == ma ){
				h+=400;
			}
			if(col>=3 && row<=2 && s[row+1][col-1] == ma && s[row+2][col-2] == ma && s[row+3][col-3] == ma ){
				h+=400;
			}

			//Contando 3 fichas
			if(col<=4 && row>=2 && s[row-1][col+1] == ma && s[row-2][col+2] == ma){
				h+=300;
			}
			if(col>=1 && col<=5 && row>=1 && row<=4 && s[row-1][col+1] == ma && s[row+1][col-1] == ma){
				h+=300;
			}
			if(col>=2 && row<=3 && s[row+1][col-1] == ma && s[row+2][col-2] == ma){
				h+=300;
			}

			//Contando 2 fichas
			if(col<=5 && row>=1 && s[row-1][col+1] == ma){
				h+=200;
			}
			if(col>=1 && row<=4 && s[row+1][col-1] == ma){
				h+=200;
			}


			////OPONENTE (HUMAN)

			/////Horizontal/////
			//Contando 4 fichas (winning turn)
			if(col>=3 && s[row][col-1] == hu && s[row][col-2] == hu && s[row][col-3] == hu ){
				h-=400;
			}
			if(col>=2 && col<=5 && s[row][col-1] == hu && s[row][col-2] == hu && s[row][col+1] == hu ){
				h-=400;
			}
			if(col>=1 && col<=4 && s[row][col-1] == hu && s[row][col+1] == hu && s[row][col+2] == hu ){
				h-=400;
			}
			if(col<=3 && s[row][col+1] == hu && s[row][col+2] == hu && s[row][col+3] == hu ){
				h-=400;
			}

			//Contando 3 fichas
			if(col>=2 && s[row][col-1] == hu && s[row][col-2] == hu ){
				h-=300;
			}
			if(col<=4 && s[row][col+1] == hu && s[row][col+2] == hu){
				h-=300;
			}
			if(col>=1 && col<=5 && s[row][col-1] == hu && s[row][col+1] == hu){
				h-=300;
			}

			//Contando 2 fichas
			if(col>=1 && s[row][col-1] == hu){
				h-=200;
			}
			if(col<=5 && s[row][col+1] == hu){
				h-=200;
			}

			/////Vertical/////
			//Contando 4 fichas (winning turn)
			if(row>=3 && s[row-1][col] == hu && s[row-2][col] == hu && s[row-3][col] == hu ){
				h-=400;
			}
			if(row>=1 && row<=3 && s[row-1][col] == hu && s[row+1][col] == hu && s[row+2][col] == hu ){
				h-=400;
			}
			if(row>=2 && row<=4 && s[row-1][col] == hu && s[row-2][col] == hu && s[row+1][col] == hu ){
				h-=400;
			}
			if(row<=2 && s[row+1][col] == hu && s[row+2][col] == hu && s[row+3][col] == hu ){
				h-=400;
			}

			//Contando 3 fichas
			if(row>=2 && s[row-1][col] == hu && s[row-2][col] == hu){
				h-=300;
			}
			if(row>=1 && row<=4 && s[row-1][col] == hu && s[row+1][col] == hu){
				h-=300;
			}
			if(row<=3 && s[row+1][col] == hu && s[row+2][col] == hu){
				h-=300;
			}

			//Contando 2 fichas
			if(row>=1 && s[row-1][col] == hu){
				h-=200;
			}
			if(row<=4 &&  s[row+1][col] == hu){
				h-=200;
			}

			/////Diagonals//////

			//Contando 4 fichas (\)
			if(col>=3 && row>=3 && s[row-1][col-1] == hu && s[row-2][col-2] == hu && s[row-3][col-3] == hu ){
				h-=400;
			}
			if(col>=2 && col<=5 && row>=2 && row<=4 && s[row-1][col-1] == hu && s[row-2][col-2] == hu && s[row+1][col+1] == hu ){
				h-=400;
			}
			if(col>=1 && col<=4 && row>=1 && row<=3 && s[row-1][col-1] == hu && s[row+1][col+1] == hu && s[row+2][col+2] == hu ){
				h-=400;
			}
			if(col<=3 && row<=2 && s[row+1][col+1] == hu && s[row+2][col+2] == hu && s[row+3][col+3] == hu ){
				h-=400;
			}

			//Contando 3 fichas
			if(col>=2 && row>=2 && s[row-1][col-1] == hu && s[row-2][col-2] == hu){
				h-=300;
			}
			if(col>=1 && col<=5 && row>=1 && row<=4 && s[row-1][col-1] == hu && s[row+1][col+1] == hu){
				h-=300;
			}
			if(col<=4 && row<=3 && s[row+1][col+1] == hu && s[row+2][col+2] == hu){
				h-=300;
			}

			//Contando 2 fichas
			if(col>=1 && row>=1 && s[row-1][col-1] == hu){
				h-=200;
			}
			if(col<=5 && row<=4 && s[row+1][col+1] == hu){
				h-=200;
			}

			//Contando 4 fichas(/)
			if(col<=3 && row>=3 && s[row-1][col+1] == hu && s[row-2][col+2] == hu && s[row-3][col+3] == hu ){
				h-=400;
			}
			if(col>=1 && col<=4 && row>=2 && row<=4 && s[row-1][col+1] == hu && s[row-2][col+2] == hu && s[row+1][col-1] == hu ){
				h-=400;
			}
			if(col>=2 && col<=5 && row>=1 && row<=3 && s[row-1][col+1] == hu && s[row+1][col-1] == hu && s[row+2][col-2] == hu ){
				h-=400;
			}
			if(col>=3 && row<=2 && s[row+1][col-1] == hu && s[row+2][col-2] == hu && s[row+3][col-3] == hu ){
				h-=400;
			}

			//Contando 3 fichas
			if(col<=4 && row>=2 && s[row-1][col+1] == hu && s[row-2][col+2] == hu){
				h-=300;
			}
			if(col>=1 && col<=5 && row>=1 && row<=4 && s[row-1][col+1] == hu && s[row+1][col-1] == hu){
				h-=300;
			}
			if(col>=2 && row<=3 && s[row+1][col-1] == hu && s[row+2][col-2] == hu){
				h-=300;
			}

			//Contando 2 fichas
			if(col<=5 && row>=1 && s[row-1][col+1] == hu){
				h-=200;
			}
			if(col>=1 && row<=4 && s[row+1][col-1] == hu){
				h-=200;
			}
		}
		else{ //Game is over (some one won or tie)
			//System.out.println("Finishing board: \n" + n.getBoard().toString());

			if(n.getBoard().getWinner() == Play.HUMAN){//human
				//				System.out.println("HUMAN WON! \n");
				//				System.out.println(n.getBoard());
				h = Integer.MIN_VALUE;
			}
			else if(n.getBoard().getWinner() == Play.MACHINE){//machine
				//				System.out.println("MACHINE WON!");
				//				System.out.println(n.getBoard());
				h = Integer.MAX_VALUE;
			}
			else{//tie
				//				System.out.println("TIEEEEEEEEEEE!");
				h = 0;	
			}
		}

		//System.out.println("returning h = " + h + " for s["+ row +"]["+col+"]");

		n.setMinMaxValue(h);
		return h;
	}



}
