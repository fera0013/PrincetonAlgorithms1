public class Subset
{
   public static void main(String[] args)
   {
	   int k = Integer.parseInt(args[0]);
       RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
       while (!StdIn.isEmpty())
       {
    	   randomQueue.enqueue(StdIn.readString());           
       }
       for(int i=0;i<k;i++)
       {
    	   StdOut.println(randomQueue.dequeue());
       }
   }
}
