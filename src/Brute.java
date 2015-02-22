import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Brute {

	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
		Out out = new Out();
        int numberOfPoints =  in.readInt();
        Point[] points = new Point[numberOfPoints]; 
        Point[] lineSequences = new Point[4];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768); 
        for(int i =0;i<numberOfPoints;i++)
        {
        	if(points[i]==null)
        	{
        		points[i]=new Point(in.readInt(),in.readInt());
        		points[i].draw();
        	}
        	for(int j=i+1;j<numberOfPoints;j++)
        	{
        		if(points[j]==null)
            	{
            		points[j]=new Point(in.readInt(),in.readInt());
            		points[j].draw();
            	}
        		for(int k=j+1;k<numberOfPoints;k++)
        		{
        			if(points[k]==null)
                	{
                		points[k]=new Point(in.readInt(),in.readInt());
                		points[k].draw();
                	}
        			for(int l=k+1;l<numberOfPoints;l++)
        			{
        				if(points[l]==null)
                    	{
                    		points[l]=new Point(in.readInt(),in.readInt());
                    		points[l].draw();
                    	}
        				if(points[i].slopeTo(points[j])==points[j].slopeTo(points[k])
        						&&points[j].slopeTo(points[k])==points[k].slopeTo(points[l]))
        				{
        					lineSequences[0] = points[i];
            				lineSequences[1]=points[j];
            				lineSequences[2]=points[k];
            				lineSequences[3]=points[l];
        					Arrays.sort(lineSequences);
        					Point first=lineSequences[0];
        					Point last=lineSequences[3];
        					first.drawTo(last);
        					out.print(lineSequences[0]);
        					out.print("->");
        					out.print(lineSequences[1]);
        					out.print("->");
        					out.print(lineSequences[2]);
        					out.print("->");
        					out.print(lineSequences[3]);
        					out.print("\n");
        				}
        			}
        		}
        	}
        }
	}

}
