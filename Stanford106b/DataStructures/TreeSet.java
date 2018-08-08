class TreeSet<E extends Comparable<E>> implements Set<E> {
	private TreeNode root;
	
	public TreeSet() {
		root = null;
	}

	private class TreeNode {
        	private E data;
        	private TreeNode left;
        	private TreeNode right;

        	public TreeNode(E data) {
                	this.data = data;
                	this.left = null;
                	this.right = null;
        	}

        	public TreeNode(E data, TreeNode left, TreeNode right) {
                	this.data = data;
                	this.left = left;
                	this.right = right;
        	}
	}
	
	public void add(E value) {
		root = add(root, value);
	}
	
	private TreeNode add(TreeNode node, E value) {
		if(node == null) {
			node = new TreeNode(value);  
		} else {
			int comp = node.data.compareTo(value);
			if(comp > 0) {
				node.left = add(node.left, value);
			} else if(comp < 0) {
				node.right = add(node.right, value);
			}
		}
		return node;
	}
	
	public boolean isEmpty() {
		return root == null;
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
	
	public boolean contains(E value) {
		return contains(root, value);
	}
	
	private boolean contains(TreeNode node, E value) {
		if(node == null) {
			return false;			// base case: not found here
		} else {
			int comp = node.data.compareTo(value);
			if(comp == 0) {
				return true;		// base case: found here
			} else if(comp > 0) {
				return contains(node.left, value);
			} else {
				return contains(node.right, value);
			}
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
	
	public E getMax() {
		if(root == null) {
			return null;
		}
		return getMin(root);
	}
	
	private E getMax(TreeNode node) {
		if(node.right == null) {
			return node.data;
		} else {
			return getMin(node.right);
		}
	}
	
	public E getMin() {
		if(root == null) {
			return null;
		}
		return getMin(root);
	}
	
	private E getMin(TreeNode node) {
		if(node.left == null) {
			return node.data;
		} else {
			return getMin(node.left);
		}
	}
	
	public TreeNode getMinNode() {
		if(root == null) {
			return null;
		}
		return getMinNode(root);
	}
	
	private TreeNode getMinNode(TreeNode node) {
		if(node.left == null) {
			return node;
		} else {
			return getMinNode(node.left);
		}
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private TreeNode deleteMin(TreeNode node) {
		if(node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		return node;
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(TreeNode node) {
		if(node == null) {
			return 0;
		}
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public int size() {
		return size(root);
	}
	
	public int size(TreeNode node) {
		if(node == null) { 
			return 0; 
		} else {
			return size(node.left) + size(node.right) + 1;
		}
		
	}
	
	public void clear() {
		root = null;
	}
	
	public void remove(E value) {
		root = remove(root, value);
	}
	
	private TreeNode remove(TreeNode node, E value) {
		if(node == null) {
			return null;
		} else {
			int comp = node.data.compareTo(value);
			if(comp > 0) {
				node.left = remove(node.left, value);
			} else if(comp < 0) {
				node.right = remove(node.right, value);
			} else {
				if(node.right == null) {
					return node.left;
				} else if(node.left == null) {
					return node.right;
				} else {
					// both children; replace w/ min from right
					node.data = getMin(node.right);
					node.right = remove(node.right, node.data);
				}
			} 
		}
		return node;
	}
}
