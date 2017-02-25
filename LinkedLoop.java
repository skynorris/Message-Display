import java.util.Iterator;

public class LinkedLoop<E> implements Loop<E> {

	//data fields
	private int numItems;
	private DblListnode<E> curr;

	//constructor
	public LinkedLoop() {

		curr = new DblListnode<E>(null);
		numItems = 0;

	}

	@Override
	public E getCurrent() {
		if(numItems == 0){
			throw new EmptyLoopException();
		}
		return curr.getData();
	}

	@Override
	public void insert(E item) {
		if (item == null){
			throw new IllegalArgumentException();
		}

		//new item
		DblListnode<E> inserting = new DblListnode<E>(item);

		//check
		if(numItems == 0){
			curr = inserting;
			numItems++;
			return;
		}

		if(numItems == 1){
			
			//make circular
			inserting.setNext(curr);

			//set pointers
			curr.setNext(inserting);
			inserting.setPrev(curr);
			curr.setPrev(inserting);

			//curr becomes new node
			curr = inserting;

			numItems++;	
			return;
		}

		//whatever curr was pointing to new node now does
		inserting.setNext(curr);

		//set pointers
		inserting.setPrev(curr.getPrev());
		curr.getPrev().setNext(inserting);
		curr.setPrev(inserting);

		//curr becomes new node
		curr = inserting;

		numItems++;	
	}

	@Override
	public E removeCurrent() {
		// TODO Auto-generated method stub
		if(numItems == 0){
			throw new EmptyLoopException();
		}
		if(numItems ==1){
			DblListnode<E> temp = curr;
			curr = null;
			numItems--;
			return temp.getData();
		}

		if(numItems ==2){

			DblListnode<E> temp = curr;

			curr.getPrev().setNext(null);
			curr.getPrev().setPrev(null);
			numItems --;
			curr = curr.getPrev();
			
			return temp.getData();

		}

		DblListnode<E> temp = curr;

		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		numItems--;
		curr = curr.getNext();
		
		return temp.getData();
	}

	@Override
	public void backward() {
		// TODO Auto-generated method stub
		if(numItems == 0){
			throw new EmptyLoopException();
		}
		
		if(numItems == 1){
			return;
		}
		
		//update curr
		curr = curr.getPrev();

	}

	@Override
	public void forward() {
		if(numItems == 0){
			throw new EmptyLoopException();
		}
		
		if(numItems ==1){
			return;
		}
		
		//update curr
		curr = curr.getNext();

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (numItems == 0);
	}


	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		LinkedLoopIterator<E> itr = new LinkedLoopIterator<E> (curr, this);
		return itr;
	}

}
