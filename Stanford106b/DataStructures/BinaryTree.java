class BinaryTree {	
	private TreeNode root;
	
	public BinaryTree(TreeNode initialRoot) {
		root = initialRoot;
	}
	
	public void print() {
		print(root);
	}
	
	private void print(TreeNode node) {
		if(node != null) {
			// pre-order: root, left, right; in-order: left, root, right; post-order: left, right, root
			print(node.left);
			System.out.println(node.data);
			print(node.right);
		}
	}
	
	public boolean contains(int value) {
		return contains(root, value);
	}
	
	private boolean contains(TreeNode node, int value) {
		if(node == null) {
			return false;
		} else if(node.data == value) {
			return true; 
		} else {
			return contains(node.left, value) || contains(node.right, value);
		}
	}
	
	public void printSideways() {
		printSideways(root, "");
	}
	
	private void printSideways(TreeNode node, String indent) {
		// right subtree, me, left subtree
		if(node != null) {
			printSideways(node.right, indent + "    ");
			System.out.println(indent + node.data);
			printSideways(node.left, indent + "    ");
		}
	}
}
