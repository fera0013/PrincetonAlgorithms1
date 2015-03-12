public class PointSET {
private SET<Point2D> pointSet = new SET<Point2D>();
	// construct an empty set of points 
	public PointSET()
	{
	}
	// is the set empty? 
	public boolean isEmpty() 
	{
		return pointSet.size() == 0;
	}
	// number of points in the set 
	public int size() 
	{
		return pointSet.size();
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p)  
	{
		if(!pointSet.contains(p))
		{
			pointSet.add(p);
		}
	}
	// does the set contain point p? 
	public boolean contains(Point2D p) 
	{
		return pointSet.contains(p);
	}
	// draw all points to standard draw 
	public void draw()   
	{
		for (Point2D point : pointSet)
		{
			point.draw();
		}
	}
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect) 
	{
		Stack<Point2D> points=new Stack<Point2D>();
		while(pointSet.iterator().hasNext())
		{
			Point2D point = pointSet.iterator().next();
			if(rect.contains(point))
			{
				points.push(point);
			}
		}
		return points;
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p)
	{
		if(this.isEmpty())
		{
			return null;
		}
		Point2D nearest=null;
		double smallestDistance=Double.MAX_VALUE;
		for(Point2D point : pointSet)
		{
			if(point.distanceTo(p)<smallestDistance)
			{
				nearest = point;
				smallestDistance=point.distanceTo(p);
			}
		}
		return nearest;
	}
	// unit testing of the methods (optional) 
	public static void main(String[] args)  
	{
	}
}