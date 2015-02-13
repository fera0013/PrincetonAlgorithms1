import java.util.Iterator;
import java.util.NoSuchElementException;

//Implementation of a Deque according to requirements given by http://coursera.cs.princeton.edu/algs4/assignments/queues.html
//Class is tested by a series of unit tests performed during uploading to coursera servers
public class Deque<Item> implements Iterable<Item> {
	// size of deque
	private int N;       
    // sentinel node front
	private Node pre;   
	// sentinel node back
	private Node post;     
	// helper doubly linked list class
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
		}
    // check internal invariants
    private boolean check() 
    {
    	
    	return true;
    }
    //Check for null item
    private void validateItem(Item item)
    {
    	if(item == null)
    	{
    		throw new java.lang.NullPointerException();
    	}
    }
	// construct an empty deque
	public Deque() 
	{
	    pre  = new Node();
        post = new Node();
        pre.next=post;
        post.previous=pre;
		N = 0;
		assert check();
	}
	// is the deque empty?
	public boolean isEmpty()  
	{
		return N==0;
	}
	// return the number of items on the deque
	public int size()
	{
		return N;
	}
	// insert the item at the front
	public void addFirst(Item item)     
	{
		validateItem(item);
		Node newFirst = new Node();
		newFirst.item = item;
		Node oldFirst = pre.next;
		pre.next=newFirst;
		newFirst.next=oldFirst;
		oldFirst.previous=newFirst;
		newFirst.previous=pre;
		N++;
		assert check();
	}
	// insert the item at the end
	public void addLast(Item item)
	{
		validateItem(item);
		Node newLast = new Node();
		newLast.item = item;
		Node oldLast = post.previous;
		post.previous=newLast;
		newLast.previous=oldLast;
		oldLast.next=newLast;
		newLast.next=post;
		N++;
		assert check();
	}
	// delete and return the item at the front
	public Item removeFirst()  
	{
		if (isEmpty()) throw new NoSuchElementException();
		Node oldFirst = pre.next;
		Item item = oldFirst.item;
		Node newFirst = oldFirst.next;
		newFirst.previous=pre;
		pre.next=newFirst;
		oldFirst=null;
        N--;
        assert check();
        return item;                   // return the saved item
	}
	// delete and return the item at the end
	public Item removeLast()                 
	{
		if (isEmpty()) throw new NoSuchElementException();
		Node oldLast = post.previous;
		Item item = oldLast.item;
		Node newLast = oldLast.previous;
		newLast.next=post;
		post.previous=newLast;
		oldLast=null;
        N--;
        assert check();
        return item;      
	}
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() 
	{
		return new ListIterator();
	}
	 // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = pre.next;
        public boolean hasNext()  
        { 
        	return current != post;   
        }
        public void remove()     
        {
        	throw new UnsupportedOperationException();  
        }
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }	
 
	public static void main
	(String[] args) 
	{
		int first=1;
		int second=2;
		int third=3;
		int fourth4;
		int size;
		int removedItem;
		Deque<Integer> intDeque=new Deque<Integer>();
		intDeque.addFirst(first);
		intDeque.addFirst(second);
		intDeque.addFirst(third);
		size = intDeque.size();
	    removedItem = intDeque.removeFirst();
	    removedItem = intDeque.removeFirst();
	    removedItem = intDeque.removeFirst();
	    intDeque.addFirst(first);
		intDeque.addFirst(second);
		intDeque.addFirst(third);
		size = intDeque.size();
	    removedItem = intDeque.removeFirst();
	    removedItem = intDeque.removeFirst();
	    removedItem = intDeque.removeFirst();
	}
}
