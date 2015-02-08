import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

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
		 if (N == a.length) 
			 resize(2*a.length);  // double size of array if necessary
		 a[N++] = item;       
	}
	// delete and return a random item
	public Item dequeue() 
	{
		int itemNumber = StdRandom.uniform(N);
		Item removedItem = a[itemNumber];
		a[itemNumber]=a[N-1];
		N--;
		return removedItem;
	}
	// return (but do not delete) a random item
	public Item sample()       
	{
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
            if (!hasNext()) throw new NoSuchElementException();
            int itemNumber = StdRandom.uniform(N);
    		Item removedItem = a[itemNumber];
    		for(int i=itemNumber;i<N;i++)
    		{
    			a[i]=a[i+1];
    		}
    		N--;
    		return removedItem;
        }
		@Override
		public void forEachRemaining(Consumer<? super Item> arg0) {
			// TODO Auto-generated method stub
			
		}         
    }
	@Override
	public void forEach(Consumer<? super Item> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new  RandomizedQueueIterator();
	}
	@Override
	public Spliterator<Item> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args)   // unit testing
	{
	}
}
