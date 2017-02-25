import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedLoopIterator<E> implements Iterator<E> {

	//data fields
	int counter;
	int size; 
	LinkedLoop<E> items;
	DblListnode<E> curr;
	DblListnode<E> temp;



	public LinkedLoopIterator(DblListnode<E> curr, LinkedLoop<E> linkedLoop) {
		// TODO Auto-generated constructor stub
		items = linkedLoop;
		counter = 0; 
		size = linkedLoop.size();
		this.curr = new DblListnode<E>(curr.getPrev(), null, curr);

	}

	@Override
	public boolean hasNext() {

		// make sure not at end of list
		return (counter < size);
	}

	@Override
	public E next() throws NoSuchElementException {

		//  make sure not at end of list and return curr item
		if(!hasNext()){
			throw new NoSuchElementException();
		}

		//save data into temp
		temp = curr.getNext();

		//update our current
		curr.setNext(curr.getNext().getNext());
		curr.setPrev(curr.getNext());

		counter++;

		return temp.getData();

	}

	public E prev() throws NoSuchElementException {

		//  make sure not at end of list and return curr item
		if(!hasNext()){
			throw new NoSuchElementException();
		}

		//save data into temp
		temp = curr.getPrev();

		//update our current
		curr.setNext(curr.getPrev());
		curr.setPrev(curr.getPrev().getPrev());

		counter++;

		return temp.getData();

	}


}
