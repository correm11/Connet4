
public class MinMaxPlayer implements MinMaxPlayerInterface {

	@Override
	public int getMin(Node n) {
		if(n.isLeaf() || n.getBoard().winner()!=-1){//if a leaf or game ended (terminal node)
			return this.getUtilityValue(n);
		}
		else{
			n.setValue(999);
			for(Node child: n.getChildren()){
				int maxValue = getMin(child);
				if(maxValue < n.getValue()){
					n.setValue(maxValue);
				}
			}
			return n.getValue();
		}
	}

	@Override
	public int getMax(Node n) {
		if(n.isLeaf() || n.getBoard().winner()!=-1){//if a leaf or game ended (terminal node)
			return this.getUtilityValue(n);
		}
		else{
			n.setValue(-999);
			for(Node child: n.getChildren()){
				int minValue = getMin(child);
				if(minValue > n.getValue()){
					n.setValue(minValue);
				}
			}
			return n.getValue();
		}
	}

	@Override
	public int minMaxResult(Node n) {
		
		return 0;
	}

	@Override
	public void minMaxTurn(Node n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUtilityValue(Node n) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
