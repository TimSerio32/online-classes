class BinaryTreeClient {
	public static void main(String[] args) {
		/*TreeNode node = new TreeNode(42);
		node.left = new TreeNode(59);
		node.right = new TreeNode(27);
		node.right.right = new TreeNode(86);
		
		BinaryTree tree = new BinaryTree(node);
		tree.print();*/
		
		TreeNode ll = new TreeNode(1);
		TreeNode lr = new TreeNode(3);
		TreeNode l = new TreeNode(2, ll, lr);
		
		TreeNode rl = new TreeNode(5);
		TreeNode rr = new TreeNode(7);
		TreeNode r = new TreeNode(6, rl, rr);
		
		TreeNode root = new TreeNode(4, l, r);
		
		BinaryTree tree = new BinaryTree(root);
		
		tree.print();
		
		System.out.println("contains:");
		
		System.out.println("Contains 1: " + tree.contains(1));
		System.out.println("Contains 7: " + tree.contains(7));
		System.out.println("Contains 0: " + tree.contains(0));
		System.out.println("Contains 10: " + tree.contains(10));
		
		System.out.println("printSideways:");
		
		tree.printSideways();
	}
}
