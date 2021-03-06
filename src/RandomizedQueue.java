import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
	// array of items
	private Item[] a; 
	// number of elements on stack
	private int N;      
	// resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    //Check for null item
    private void validateItem(Item item)
    {
    	if(item == null)
    	{
    		throw new java.lang.NullPointerException();
    	}
    }
    // construct an empty randomized queue
	public RandomizedQueue()          
	{
		a = (Item[]) new Object[2];
	}
	// is the queue empty?
	public boolean isEmpty()  
	{
		return N == 0;
	}
	// return the number of items on the queue
	public int size()  
	{
		return N;
	}
	// add the item
	public void enqueue(Item item)           
	{
		validateItem(item);
		if (N == a.length) 
			resize(2*a.length);  // double size of array if necessary
		a[N++] = item;       
	}
	// delete and return a random item
	public Item dequeue() 
	{
		if (isEmpty()) throw new NoSuchElementException();
		int itemNumber = StdRandom.uniform(N);
		Item removedItem = a[itemNumber];
		a[itemNumber]=a[N-1];
		N--;
		return removedItem;
	}
	// return (but do not delete) a random item
	public Item sample()       
	{
		if (isEmpty()) throw new NoSuchElementException();
		int itemNumber = StdRandom.uniform(N);
		return a[itemNumber];
	}
	private class RandomizedQueueIterator implements Iterator<Item> 
	{
        private int i;
        public RandomizedQueueIterator() {
            i = N;
        }
        public boolean hasNext() {
            return N>0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
        	if (isEmpty()) throw new NoSuchElementException();
    		int itemNumber = StdRandom.uniform(N);
    		Item removedItem = a[itemNumber];
    		a[itemNumber]=a[N-1];
    		N--;
    		return removedItem;
        }       
    }
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new  RandomizedQueueIterator();
	}
	public static void main(String[] args)   // unit testing
	{
	}
}
