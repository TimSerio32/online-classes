class AVLTree<T> {
	private int currentSize;
	private TreeNode root;
	
	private class TreeNode {
		public E data;         // data stored at this node
		public int height;
		public TreeNode left;  // reference to left subtree
		public TreeNode right;// reference to right subtree
		public TreeNode parent;
		
		// Constructs a leaf node with the given data.
		public TreeNode(E data) {
			this(data, null, null);
			this.height = 1;
		}

		// Constructs a leaf or branch node with the given data and links.
		public TreeNode(E data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public AVLTree() {
		root = null;
		currentSize = 0;
	}
	
	private TreeNode rightRotate(TreeNode oldParent) {
		// 1. detach left child's right subtree
		TreeNode orphan = oldParent.left.right;
		
		// 2. consider left child to be the new parent
		TreeNode newParent = oldParent.left;
		
		// 3. attach old parent onto right of new parent
		newParent.right = oldParent;
		
		// 4. attach new parent's old right subtree as left subtree of old parent
		oldParent.left = orphan;
		
		oldParent.height = height(oldParent);
		newParent.height = height(newParent);
		
		return newParent;
	}
	
	private TreeNode leftRotate(TreeNode oldParent) {
		// 1. detach right child's left subtree
		TreeNode orphan = oldParent.right.left;
		
		// 2. consider right child to be the new parent
		TreeNode newParent = oldParent.right;
		
		// 3. attach old parent onto the left of new parent
		newParent.left = oldParent;
		
		// 4. attach new parent's old left subtree as right subtree of old parent
		oldParent.right = orphan;
		
		oldParent.height = height(oldParent);
		newParent.height = height(newParent);
		
		return newParent;
	}
	
	private TreeNode leftRightRotate(TreeNode oldParent) {
		oldParent.right = rotateLeft(oldParent.right);
		return rotateRight(oldParent);
	}
	
	private TreeNode rightLeftRotate(TreeNode oldParent) {
		oldParent.left = rotateRight(oldParent.left);
		return rotateLeft(oldParent);
	}
	
	public void booyah() {
		root = rightRotate(root);
	}
	
	public void add(T value) {
		TreeNode node = new TreeNode(value);
		if(root == null) {
			root = node;
			currentSize++;
			return;
		}
		add(root, node);
	}
	
	public void add(TreeNode parent, TreeNode newNode) {
		if((Comparable<T>) newNode.data.compareTo(parent.data) > 0) {
			if(parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				currentSize++;
			} else {
				add(parent.right, newNode);
			}
		} else if((Comparable<T>) newNode.data.compareTo(parent.data) < 0) {
			if(parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				currentSize++;
			} else {
				add(parent.left, newNode);
			}
			checkBalance(newNode);
		}
	}
	
	public void checkBalance(TreeNode node) {
		if((height(node.left) - height(node.right) > 1) || (height(node.left) - height(node.right) < -1)) {
			rebalance(node);
		}
		if(node.parent == null) { return; }
		checkBalance(node.parent);
	}
	
	public void rebalance(TreeNode node) {
		if(height(node.left) - height(node.right) > 1) {
			if(height(node.left.left) > height(node.left.right)) {
				node = rightRotate(node);
			} else {
				node = leftRightRotate(node);
			}
		} else {
			if(height(node.right.left) > height(node.right.right)) {
				node = leftRotate(node);
			} else {
				node = rightLeftRotate(node);
			}
		}
		
		if(node.parent == null) {
			root = node;
		}
	}
	
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(40);
		set.add(70);
		set.add(90);
		set.add(20);
		set.add(30);
		set.print();
		
		set.booyah();
		set.print();
	}
}
