import java.util.Arrays;


public class Fast {

	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
		Out out = new Out();
        int numberOfPoints =  in.readInt();
        Point[] points = new Point[numberOfPoints]; 
        for(int i=0;i<numberOfPoints;i++)
        {
        	points[i]=new Point(in.readInt(),in.readInt());	
        }
        for(int i=0;i<numberOfPoints;i++)
        {
        	Point ithPoint=points[i];
        	points[i]=points[0];
        	points[0]=ithPoint;
        	Arrays.sort(points,1,numberOfPoints-1,ithPoint.SLOPE_ORDER);
        	for(int j=1;j<numberOfPoints-4;j++)
        	{
        		if(points[j].slopeTo(points[j+1])==points[j].slopeTo(points[j+2]))
        		{
        			if(ithPoint.slopeTo(points[j])==ithPoint.slopeTo(points[j+1]))
        			{
        				out.print(ithPoint);
    					out.print("->");
    					out.print(points[j]);
    					out.print("->");
    					out.print(points[j+1]);
    					out.print("->");
    					out.print(points[j+2]);
    					out.print("\n");
        			}
        		}
        	}
        }
	}    
}
