class AVLTreeSet<E extends Comparable<E>> implements Set<E> {
	private TreeNode root;
	private int size;
	
	public AVLTreeSet() {
		root = null;
		size = 0;
	}

	private class TreeNode {
        	private E data;
        	private int height;
        	private TreeNode left;
        	private TreeNode right;
        	private TreeNode parent;

        	public TreeNode(E data) {
                	this.data = data;
                	this.height = 0;
                	this.left = null;
                	this.right = null;
        	}

        	public TreeNode(E data, TreeNode left, TreeNode right) {
                	this.data = data;
                	this.height = 0;
                	this.left = left;
                	this.right = right;
        	}
	}
	
	public void add(E value) {
		root = add(root, value);
	}
	
	private TreeNode add(TreeNode node, E value) {
		// check balance factor(height(right subtree) - height(left subtree))
		// if unbalanced, check 4 unbalanced cases:
		//  - LL, LR, RL, RR
		// 	- fix with rightRotate, leftRotate, leftRightRotate and rightLeftRotate
		
		if(node == null) {
			node = new TreeNode(value); 
			size++;
		} else {
			int comp = node.data.compareTo(value);
			if(comp > 0) {
				node.left = add(node.left, value);
				height(node);
			} else if(comp < 0) {
				node.right = add(node.right, value);
				height(node);
			}
			checkBalance(node);
		}
		return node;
	}
	
	public void checkBalance(TreeNode node) {
		if(height(node.left) - height(node.right) > 1 || (height(node.left) - height(node.right) < -1)) {
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
	
	public void booyah() {
		root = rightRotate(root);
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void print() {
		print(root);
	}
	
	private void print(TreeNode node) {
		if(node != null) {
			// pre-order: root, left, right; in-order: left, root, right; post-order: left, right, root
			print(node.left);
			System.out.println(node.data + " " + node.height);
			print(node.right);
		}
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
		
		
		height(oldParent);
		height(newParent);
		//oldParent.height = height(oldParent);
		//newParent.height = height(newParent);
		
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
		
		height(oldParent);
		height(newParent);
		//oldParent.height = height(oldParent);
		//newParent.height = height(newParent);
		
		return newParent;
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
	
	private int height(TreeNode node) {
		if(node != null) {
			// larger of left / right + 1
			int left = 0;
			if(node.left != null) {
				left = node.left.height;
			}
			int right = 0;
			if(node.right != null) {
				right = node.right.height;
			}
			node.height = Math.max(left, right) + 1;
		}
		return node.height;
	}
	
	public int size() {
		return size;
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
				size--;
				if(node.right == null && node.left == null) {
					return null;
				} else if(node.right == null) {
					node = node.left;
				} else if(node.left == null) {
					node = node.right;
				} else {
					// both children; replace w/ min from right
					E rightMin = getMin(node.right);
					node.right = remove(node.right, rightMin);
					node.data = rightMin;
				}
			} 
		}
		return node;
	}
	
	public static void main(String[] args) {
		AVLTreeSet<Integer> set = new AVLTreeSet<Integer>();
		set.add(40);
		set.add(70);
		set.add(90);
		set.add(99);
		set.add(20);
		set.add(30);
		set.print();
		set.printSideways();
		System.out.println();
		//set.booyah();
		set.print();
		set.printSideways();
	}
}
