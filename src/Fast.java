import java.util.Arrays;

public class Fast {
	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
		Out out = new Out();
        int numberOfPoints =  in.readInt();
        Point[] points = new Point[numberOfPoints]; 
        Point[] lineSequences = new Point[4];
        SET<Double> slopes=new SET<Double>();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768); 
        for(int i=0;i<numberOfPoints;i++)
        {
        	points[i]=new Point(in.readInt(),in.readInt());	
        	points[i].draw();
        }
        for(int i=0;i<numberOfPoints;i++)
        {
        	Point ithPoint=points[i];
        	points[i]=points[0];
        	points[0]=ithPoint;
        	Arrays.sort(points,1,numberOfPoints,ithPoint.SLOPE_ORDER);
        	for(int j=1;j<numberOfPoints-4;j++)
        	{
        		if(points[j].slopeTo(points[j+1])==points[j].slopeTo(points[j+2]))
        		{
        			if(ithPoint.slopeTo(points[j])==ithPoint.slopeTo(points[j+1]))
        			{
        				if(slopes.contains(ithPoint.slopeTo(points[j])))
        				{
        					continue;
        				}
        				else
        				{
        					slopes.add(ithPoint.slopeTo(points[j]));
        				}
        				lineSequences[0] = ithPoint;
        				lineSequences[1]=points[j];
        				lineSequences[2]=points[j+1];
        				lineSequences[3]=points[j+2];
        				Arrays.sort(lineSequences);
        				out.print(lineSequences[0]);
    					out.print("->");
    					out.print(lineSequences[1]);
    					out.print("->");
    					out.print(lineSequences[2]);
    					out.print("->");
    					out.print(lineSequences[3]);
    					out.print("\n");
    					Point first=lineSequences[0];
    					Point last=lineSequences[3];
    					first.drawTo(last);
        			}
        		}
        	}
        }
	}    
}
