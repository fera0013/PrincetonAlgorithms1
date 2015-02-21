import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Brute {

	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
		Out out = new Out();
        int numberOfPoints =  in.readInt();
        Point[] points = new Point[numberOfPoints]; 
        for(int i =0;i<numberOfPoints;i++)
        {
        	if(points[i]==null)
        	{
        		points[i]=new Point(in.readInt(),in.readInt());
        	}
        	for(int j=i+1;j<numberOfPoints;j++)
        	{
        		if(points[j]==null)
            	{
            		points[j]=new Point(in.readInt(),in.readInt());
            	}
        		for(int k=j+1;k<numberOfPoints;k++)
        		{
        			if(points[k]==null)
                	{
                		points[k]=new Point(in.readInt(),in.readInt());
                	}
        			for(int l=k+1;l<numberOfPoints;l++)
        			{
        				if(points[l]==null)
                    	{
                    		points[l]=new Point(in.readInt(),in.readInt());
                    	}
        				if(points[i].slopeTo(points[j])==points[j].slopeTo(points[k])
        						&&points[j].slopeTo(points[k])==points[k].slopeTo(points[l]))
        				{
        					out.print(points[i]);
        					out.print("->");
        					out.print(points[j]);
        					out.print("->");
        					out.print(points[k]);
        					out.print("->");
        					out.print(points[l]);
        					out.print("\n");
        				}
        			}
        		}
        	}
        }
	}

}
