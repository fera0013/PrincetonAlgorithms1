import java.util.Arrays;

public class Fast {
	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
		Out out = new Out();
        int numberOfPoints =  in.readInt();
        Point[] points = new Point[numberOfPoints]; 
        Point[] lineSequences = new Point[numberOfPoints];
        SET<Double> uniqueLineIdentifiers=new SET<Double>();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768); 
        for(int i=0;i<numberOfPoints;i++)
        {
        	points[i]=new Point(in.readInt(),in.readInt());	
        	points[i].draw();
        }
        for(int i=0;i<numberOfPoints;i++)
        {
        	lineSequences=Arrays.copyOf(points, points.length);
        	Point ithPoint=lineSequences[i];
        	lineSequences[i]=lineSequences[0];
        	lineSequences[0]=ithPoint;
        	Arrays.sort(lineSequences,1,numberOfPoints,ithPoint.SLOPE_ORDER);
        	for(int j=1;j<numberOfPoints-2;j++)
        	{
        		if(lineSequences[j].slopeTo(lineSequences[j+1])==lineSequences[j].slopeTo(lineSequences[j+2]))
        		{
        			if(ithPoint.slopeTo(lineSequences[j])==ithPoint.slopeTo(lineSequences[j+1]))
        			{
        				double slope=ithPoint.slopeTo(lineSequences[j]);
        				String[] split= ithPoint.toString().split("(\\()|(\\))|(,)|( )");
    					double x=Double.parseDouble(split[1]);
    					double uniqueLineIdentifier;
    					if(Double.isInfinite(slope))
    					{
    						//use the x-intercept to differenciate between vertical lines
    						uniqueLineIdentifier=x;
    					}
    					else
    					{
    						//use the y intercept to differenciate between non-vertical lines with the same slope
    						double y=Double.parseDouble(split[3]);
    						uniqueLineIdentifier=-(slope*x)+y+slope;
    					}
        				if(uniqueLineIdentifiers.contains(uniqueLineIdentifier))
        				{
        					continue;
        				}
        				else
        				{
        					uniqueLineIdentifiers.add(uniqueLineIdentifier);
        				}
        				lineSequences[j-1]=ithPoint;
        				Arrays.sort(lineSequences,j-1,j+3);
        				out.print(lineSequences[j-1]);
    					out.print("->");
    					out.print(lineSequences[j]);
    					out.print("->");
    					out.print(lineSequences[j+1]);
    					out.print("->");
    					out.print(lineSequences[j+2]);
    					out.print("\n");
    					Point last=lineSequences[j+2];
    					lineSequences[j-1].drawTo(last);
        			}
        		}
        	}
        }
	}    
}
