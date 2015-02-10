import java.util.Iterator;
import java.util.NoSuchElementException;

//Implementation of a Deque according to requirements given by http://coursera.cs.princeton.edu/algs4/assignments/queues.html
//Class is tested by a series of unit tests performed during uploading to coursera servers
public class Deque<Item> implements Iterable<Item> {
	// TODO Auto-generated method stub
	private int N;          // size of the stack
	private Node first;     // top of stack
	private Node last;     // top of stack
	// helper doubly linked list class
	private class Node {
		private Item item;
		private Node next;
		private Node previous;
		}
    // check internal invariants
    private boolean check() 
    {
    	if (N == 0) 
    	{
    		if (first != null||last!=null) 
    			return false;
        }
        else if (N == 1) 
        {
            if(first!=last)
            	return false;
        }
        else
        {
            if (first.next == null) 
            	return false;
        }
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
		first = new Node();
		last = new Node();
		first.item = last.item = null;
		first.next=last;
		last.previous=first;
		first.previous=null;
		last.next=null;
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
		//Create new first
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		first.previous = null;
		oldfirst.previous=first;
		N++;
		assert check();
	}
	// insert the item at the end
	public void addLast(Item item)
	{
		validateItem(item);
		//Create new first
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.previous = oldlast;
		oldlast.next=last;
		N++;
		assert check();
	}
	// delete and return the item at the front
	public Item removeFirst()  
	{
		if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        first.previous=null;
        N--;
        assert check();
        return item;                   // return the saved item
	}
	// delete and return the item at the end
	public Item removeLast()                 
	{
		if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;        // save item to return
        last = last.previous;            // delete first node
        last.next=null;
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
        private Node current = first;
        public boolean hasNext()  
        { 
        	return current != last;   
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
		Deque<Integer> deque = new Deque<Integer>();
		deque.addFirst(1);
		int length = deque.size();
		deque.addLast(2);
		length = deque.size();
		int firstItem=deque.removeFirst();
		length = deque.size();
		int lastItem=deque.removeLast();
		length = deque.size();
	}
}
