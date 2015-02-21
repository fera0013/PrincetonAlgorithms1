/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
	private final int x;                              // x coordinate
	private final int y;                              // y coordinate
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER=new Comparator<Point>()
    		{
		    	public int compare(Point p1,Point p2)
		    	{
		    		if(p1==null||p2==null)
		    		{
		    			throw new NullPointerException();
		    		}
		    		return (int) (slopeTo(p1)-slopeTo(p2));
		    	}
    		};     

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	if(that==null)
    	{
    		throw new NullPointerException();
    	}
    	double deltay = (double)that.y-(double)this.y;
    	double deltax = (double)that.x-(double)this.x;
		return deltay/deltax;
    }
 
    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if(that==null)
    	{
    		throw new NullPointerException();
    	}
    	int deltaY = this.y - that.y;
    	if(deltaY!=0)
    	{
    		return deltaY;
    	}
    	else return this.x-that.x;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
