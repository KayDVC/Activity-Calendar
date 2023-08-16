import java.util.Objects;

import types.SkipNode;
import util.FakeRandHeight;

public class SkipList {

	SkipNode head;
	SkipNode tail;
	Integer NEG_INFINITY = Integer.MIN_VALUE;
	Integer POS_INFINITY = Integer.MAX_VALUE;

	FakeRandHeight rand = new FakeRandHeight();

	Integer listHeight = 0;

	public SkipList(){

		// sentinel head/tail approach.
		head = new SkipNode(null, NEG_INFINITY);
		tail = new SkipNode(null, POS_INFINITY);

		head.setNext(tail);
		tail.setPrev(head);
	}

	public SkipNode searchList(Integer dateTime){

		SkipNode node = head;

		while(node.getLower()!= null){

			// Skip sentinel node.
			node = node.getLower();

			while(node.getNext().getDateTime() <= dateTime){
				node = node.getNext();
			}
		}

		return node;
	}


	public void insertNode(SkipNode node){

		SkipNode positionToInsert = searchList(node.getDateTime());
		SkipNode positionHolder;

		// Will be a value ranging from 0-4; Skip list will never have a
		// maximum height of more than 6 levels in this application
		int levelToInsert = rand.get();

		for (int temp = 0; temp <=levelToInsert; temp++){

			// Check if there are sufficient levels for insertion.
			if (listHeight<=temp){
				addLevel();
				listHeight++;
			}
			if (temp == 0){
				node.setPrev(positionToInsert);
				node.setNext(positionToInsert.getNext());

				positionToInsert.getNext().setPrev(node);
				positionToInsert.setNext(node);
			}
			else {
				// Ensures base node is updated to reflect the correct level
				positionHolder = positionToInsert.getNext();

				// Ensure the upper level get connected properly in the event that
				// the new node gets inserted into multiple levels.
				while (positionToInsert.getUpper() == null) {
					positionToInsert = positionToInsert.getPrev();
				}
				positionToInsert = positionToInsert.getUpper();

				SkipNode newNode = new SkipNode(node.getActivity(),
						node.getDateTime());

				newNode.setPrev(positionToInsert);
				newNode.setNext(positionToInsert.getNext());
				newNode.setLower(positionHolder);

				positionHolder.setUpper(newNode);
				positionToInsert.getNext().setPrev(newNode);
				positionToInsert.setNext(newNode);

			}
		}
	}

	void addLevel(){
		SkipNode tempHead = new SkipNode(null, NEG_INFINITY);
		SkipNode tempTail = new SkipNode(null,POS_INFINITY);
		tempHead.setNext(tempTail);
		tempTail.setPrev(tempHead);

		// connect temp head/tail to current head/tail nodes
		tempHead.setLower(head);
		tempTail.setLower(tail);
		head.setUpper(tempHead);
		tail.setUpper(tempTail);
		head = tempHead;
		tail = tempTail;
	}

	void removeLevel(){
		head = head.getLower();
		tail = tail.getLower();
	}

	public void removeNode (Integer dateTime){
		SkipNode toRemove = searchList(dateTime);

		do {
			SkipNode temp = toRemove.getUpper();

			if (toRemove.getLower()!=null){
				toRemove.setLower(null);
			}

			toRemove.getPrev().setNext(toRemove.getNext());
			toRemove.getNext().setPrev(toRemove.getPrev());
			toRemove.setUpper(null);

			toRemove = temp;

		}
		while (toRemove!=null);

		// remove level if there are more than 1 empty levels after calling
		// removeNode
		if (Objects.equals(head.getLower().getNext().getDateTime(), POS_INFINITY)){
			 removeLevel();
		}
	}

	public void printList(){

		SkipNode currNode = head.getLower();
		SkipNode nextLevel = currNode.getLower();

 		// Skip empty level when printing list.
		Integer printHeight = listHeight-1;

		System.out.println("\nResponse:");

		while (currNode!=null){
			// Exclude sentinel nodes.
			currNode = currNode.getNext();

			System.out.print("\t(L"+ printHeight + ")  ");

			while (!Objects.equals(currNode.getDateTime(), POS_INFINITY)){
				System.out.print(String.format( "%08d", currNode.getDateTime()) + " : " + currNode.getActivity()+ (currNode.getNext().getDateTime() == POS_INFINITY ? "" : ",  "));
				currNode = currNode.getNext();
			}
			currNode = nextLevel;

			printHeight--;
			System.out.println("");

			if (currNode != null){
				nextLevel = currNode.getLower();
			}
		}
	}

	public void subMap(Integer timeDate1, Integer timeDate2){
		SkipNode beginning = searchList(timeDate1);
		SkipNode end = searchList(timeDate2);

		System.out.println("\nResponse:");

		// Check the beginning and end values to make sure
		// they are within the time frame specified.
		if(beginning.getDateTime() < timeDate1){
			beginning = beginning.getNext();
		}

		while (true){
			String string = "\t" + String.format( "%08d" , beginning.getDateTime()) + " : " + beginning.getActivity() + " ";
			System.out.println(string);
			if (beginning == end){
				break;
			}
			beginning = beginning.getNext();
		}
	}
}
