#include <iostream>
#include "console.h"
#include "LinkedList.h"

using namespace std;

void testAdd(LinkedList& list);
void testRemove(LinkedList& list);
void testMisc(LinkedList& list);
void testResize(LinkedList& list);
void testAssign(LinkedList& list);
void testException(LinkedList& list);

int main() {
	LinkedList list;

	testAdd(list);
	//testMisc(list);
	//testResize(list);
	testRemove(list);
	//testAssign(list);
	//testException(list);

	cout << "exiting main" << endl;
	return 0;
}

void testAdd(LinkedList& list) {
	cout << "add:" << endl;
        list.add(42);
        list.add(-5);
        list.add(17);
        list.add(28);
        cout << list.printList() << ", size " << list.size() << endl;
	
	cout << "insert:" << endl;
        list.insert(1, 111);
        cout << list.printList() << ", size " << list.size() << endl;
        list.insert(4, 444);
        cout << list.printList() << ", size " << list.size() << endl;
        list.insert(0, 0);
        cout << list.printList() << ", size " << list.size() << endl;
	list.insert(7, 7777);
        cout << list.printList() << ", size " << list.size() << endl;
	cout << endl;	
}

void testRemove(LinkedList& list) {
	cout << "remove:" << endl;
	list.remove(2);
	cout << list.printList() << ", size " << list.size() << endl;
	list.remove(list.size() - 1);
        cout << list.printList() << ", size " << list.size() << endl;
	list.remove(0);
        cout << list.printList() << ", size " << list.size() << endl;
	cout << endl;
}

void testMisc(LinkedList& list) {
	
}

void testResize(LinkedList& list) {
	
}

void testAssign(LinkedList& list) {
	
}

void testException(LinkedList& list) {
	
}
