public class Subset
{
   public static void main(String[] args)
   {
	   In in = new In(args[0]);     
       int k= in.readInt(); 
       RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
       String currentString="";
       while((currentString=StdIn.readString())!=null)
    	   randomQueue.enqueue(currentString);
       for(int i=0;i<k;i++)
       {
    	   StdOut.println(randomQueue.dequeue());
       }
   }
}
