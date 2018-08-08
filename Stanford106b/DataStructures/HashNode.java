class HashNode {
	int data;
	HashNode next;

	public HashNode() {
		this.data = 0;
		this.next = null;
	}	
	
	public HashNode(int data) {
		this.data = data;
		this.next = null;
	}
	
	public HashNode(HashNode next) {
		this.data = 0;
		this.next = next;
	}

	public HashNode(int data, HashNode next) {
		this.data = data;
		this.next = next;
	}
}
