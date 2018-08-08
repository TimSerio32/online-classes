class TreeNode {
	private int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
		
	public TreeNode(int data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
