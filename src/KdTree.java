public class KdTree {
	private static class Node {
		   private Point2D p;      // the point
		   private RectHV rect;    // the axis-aligned rectangle corresponding to this node
		   private Node left;        // the left/bottom subtree
		   private Node right;        // the right/top subtree
		}
	private enum orientation {
	    HORIZONTAL, VERTICAL 
	}
	private int numberOfPoints=0;
	private Node root=null;
	// construct an empty set of points 
	public KdTree()
	{
	}
	// is the set empty? 
	public boolean isEmpty()
	{
		return size()==0;
	}
	// number of points in the set 
	public int size()  
	{
		return numberOfPoints;
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p)              
	{
		if(!contains(p))
		{
			RectHV rect= new RectHV(0, 0, 1, 1);
			root = put(root, p,orientation.VERTICAL,rect);
			this.numberOfPoints++;
		}
	}
	private Node put(Node node, Point2D p,orientation o,RectHV rect) {
        if (node == null) 
        {
        	Node newNode = new Node();
        	newNode.p=p;
        	newNode.rect=rect;
        	return newNode;
        }
        if(node.p.equals(p))
        {
        	return node;
        }
        if(o==orientation.VERTICAL)
        {
        	if(p.x()<node.p.x())
        	{
        		node.left = put(node.left,p,orientation.HORIZONTAL,new RectHV(rect.xmin(),rect.ymin(),node.p.x(),rect.ymax()));
        	}
        	else 
        	{
        		node.right = put(node.right,p,orientation.HORIZONTAL,new RectHV(node.p.x(),rect.ymin(),rect.xmax(),rect.ymax()));
        	}
        }
        else
        {

        	if(p.y()<node.p.y())
        	{
        		node.left = put(node.left,p,orientation.VERTICAL,new RectHV(rect.xmin(),rect.ymin(),rect.xmax(),node.p.y()));
        	}
        	else
        	{
        		node.right = put(node.right,p,orientation.VERTICAL,new RectHV(rect.xmin(),node.p.y(),rect.xmax(),rect.ymax()));
        	}
        }
        return node;
	}
	// does the set contain point p? 
	public boolean contains(Point2D p) 
	{
		return get(p,orientation.VERTICAL)!=null;
	}
	private Point2D get(Point2D key,orientation o) 
	{
		return get(root, key,o);
	}
	private Point2D get(Node node, Point2D point,orientation o) 
	{
        if (node == null)
        {
        	return null;
        }
        if(node.p.equals(point))
        {
        	return node.p;
        }
        if(o==orientation.VERTICAL)
        {
        	if(point.x()<node.p.x())
        	{
        		return get(node.left, point,orientation.HORIZONTAL);
        	}
        	else
        	{
        		return get(node.right, point,orientation.HORIZONTAL);
        	}
        }
        else
        {
        	if(point.y()<node.p.y())
        	{
        		return get(node.left, point,orientation.VERTICAL);
        	}
        	else
        	{
        		return get(node.right, point,orientation.VERTICAL);
        	}
        }
	}
	// draw all points to standard draw 
	public void draw()  
	{
		drawHelper(root,orientation.VERTICAL);
	}
	private void drawHelper(Node node,orientation o)
	{
		if(node == null)
		{
			return;
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(.01);
		node.p.draw();
		StdDraw.setPenRadius(0.001);
		if(o==orientation.VERTICAL)
		{
			StdDraw.setPenColor(StdDraw.RED);
			Point2D pointFrom = new Point2D(node.p.x(),node.rect.ymin());
			Point2D pointTo = new Point2D(node.p.x(),node.rect.ymax());
			pointFrom.drawTo(pointTo);
			drawHelper(node.left,orientation.HORIZONTAL);
			drawHelper(node.right,orientation.HORIZONTAL);
		}
		else
		{
			StdDraw.setPenColor(StdDraw.BLUE);
			Point2D pointFrom = new Point2D(node.rect.xmin(),node.p.y());
			Point2D pointTo = new Point2D(node.rect.xmax(),node.p.y());
			pointFrom.drawTo(pointTo);
			drawHelper(node.left,orientation.VERTICAL);
			drawHelper(node.right,orientation.VERTICAL);
		}
	}
	private Stack<Point2D> rangeHelper(Node node, RectHV rect,Stack<Point2D> points )
	{
		if(node==null||!node.rect.intersects(rect))
		{
			return points;
		}
		else
		{
			if(rect.contains(node.p))
			{
				points.push(node.p);
			}
			points = rangeHelper(node.left,rect,points);
			points = rangeHelper(node.right,rect,points);
		}
		return points;
	}
	// all points that are inside the rectangle 
	public Iterable<Point2D> range(RectHV rect)
	{
		Stack<Point2D> points=new Stack<Point2D>();
		return rangeHelper(root, rect,points);
	}
	// a nearest neighbor in the set to point p; null if the set is empty 
	public Point2D nearest(Point2D p)  
	{
		return findNearest(p,root.p,root);
	}
	private Point2D findNearest(Point2D queryPoint,Point2D nearest,Node node)
	{
		if(node==null||nearest.distanceTo(queryPoint)<node.rect.distanceTo(queryPoint))
		{
			return nearest;
		}
		if(node.p.distanceTo(queryPoint)<nearest.distanceTo(queryPoint))
		{
			nearest=node.p;
		}
		nearest=findNearest(queryPoint,nearest,node.left);
		nearest=findNearest(queryPoint,nearest,node.right);
		return nearest;
	}
	// unit testing of the methods (optional)
	public static void main(String[] args)
	{
		 String filename = args[0];
	        In in = new In(filename);
	        // initialize the data structures with N points from standard input
	        KdTree kdtree = new KdTree();
	        while (!in.isEmpty()) {
	            double x = in.readDouble();
	            double y = in.readDouble();
	            Point2D p = new Point2D(x, y);
	            kdtree.insert(p);
	        }
	        Point2D p=new Point2D(0.500000, 1.000000);
	        kdtree.insert(p);
	        Out out = new Out();
	        out.println(kdtree.size());
	        // draw the points
	        kdtree.draw();
	        StdDraw.show(1);
	}
}