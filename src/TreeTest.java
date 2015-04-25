public class TreeTest {

	public static void main(String[] args) {
		Tree theTree = new Tree(new Board(1,7,6));
		
		//System.out.println(theTree.getRoot().getDepth());
		System.out.println("Total number of Nodes generated: " + theTree.getNumNodes());
	}

}
