public class Subset
{
   public static void main(String[] args)
   {
       int k = 3; 
       RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
       for(int i =0;i<9;i++)
       {
    	   randomQueue.enqueue(StdIn.readString());
       }
       for(int i=0;i<k;i++)
       {
    	   System.out.println(randomQueue.dequeue());
       }
   }
}
