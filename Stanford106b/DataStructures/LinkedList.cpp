#include "LinkedList.h"

// Creates a new empty list
LinkedList::LinkedList() {
	front = NULL;
}

// This destructor frees the memory that was allocated in the constructor
LinkedList::~LinkedList() {
	
}

// Appends the given value to the end of the list
void LinkedList::add(int value) {
	if(front == NULL) {
		front = new ListNode(value);
	} else {
		ListNode* current = front;
		while(current->next != NULL) {
			current = current->next;
		}
		current->next = new ListNode(value);
	}	
}

// Removes all values from the list
void LinkedList::clear() {
	
}

// Returns the data as an int from the node at the given index
int LinkedList::get(int index) const {
	ListNode* current = front;
	for(int i = 0; i < index; i++) {
		current = current->next;
	}
	// current is pointing at the node of the ith index in the list
	return current->data;
}

// Inserts a node with data = value at the given index
void LinkedList::insert(int index, int value) {
	if(index == 0) {
		ListNode* oldFront = front;
		front = new ListNode(value);
		front->next = oldFront;
	} else {
		ListNode* current = front;
		for(int i = 0; i < index - 1; i++) {
			current = current->next;
		}
		// current is pointing at the (index - 1)st node
		// insert just after current
		ListNode* newNode = new ListNode(value);	
		newNode->next = current->next;
		current->next = newNode;
	}
}

// Returns true if there are no elements
bool LinkedList::isEmpty() const {
	return false;	
}

// Removes the node at the given index
void LinkedList::remove(int index) {
	if(index == 0) {
		front = front.next;
	} else {
		ListNode* current = front;
		for(int i = 0; i < index - 1; i++) {
			current = current->next;
		}
		// remove the node
		current->next = current->next->next;
	}
}

// Sets a node with data = value at the given index
void LinkedList::set(int index, int value) {
	ListNode* current = front;
        for(int i = 0; i < index; i++) {
                current = current->next;
        }
        // current is pointing at the node of the ith index in the list
        current->data = value;
}

// Returns the number of elements in the list
int LinkedList::size() const {
	int res = 0;
	ListNode* current = front;
	while(current != null) {
		res++;
		current = current->next;
	}
	return res;
}

void LinkedList::printList(ListNode* list) {
    for (ListNode* n = list; n != NULL; n = n->next) {
        cout << n->value << endl;
    }
}

friend ostream& operator <<(ostream out, LinkedList& list) {
	
}
