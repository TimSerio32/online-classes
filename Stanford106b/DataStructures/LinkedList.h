#ifndef _linkedlist_h
#define _linkedlist_h

#include <iostream>
#include <string>
using namespace std;

// The internal structure representing a single node
struct ListNode {
	int data;		// element stored in each node
	ListNode* next;		// pointer to the next

	ListNode(int d = 0; ListNode* n = NULL) {
		data = d;
		next = n;
	}
};

class LinkedList {
public:
	LinkedList();
	~LinkedList();

	void add(int value);
	void clear();
	int get(int index) const;
	void insert(int index, int value);
	bool isEmpty() const;
	void remove(int index);
	void set(int index, int value);
	int size() const;
	void printList(ListNode* list) const;	
	
	friend ostream& operator <<(ostream out, LinkedList& list);

private:
	ListNode* front;

#endif
