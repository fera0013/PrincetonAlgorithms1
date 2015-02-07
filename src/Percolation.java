public class Percolation {
	private WeightedQuickUnionUF unionFindForPercolationTest;
	private WeightedQuickUnionUF unionFindForFullTest;
	private boolean[][] openSites;
	private int N;
	private int XyTo1D(int i, int j) 
	{
		if(!IndicesInBound(i,j))
			throw new IndexOutOfBoundsException();
	    return ((i-1)*N+j);
	}
	private boolean IndicesInBound(int i, int j)
	{
		if(i<1||j<1||i>N||j>N)
		{
			return false;
		}
		return true;
	}
	// create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		if(N<1)
			throw new IllegalArgumentException();
		this.N = N;
		openSites=new boolean[N][N];
		unionFindForPercolationTest = new WeightedQuickUnionUF(N*N+2);
		unionFindForFullTest = new WeightedQuickUnionUF(N*N+1);
		//Connect top and bottom row to virtual tops and bottoms
		for(int i=1;i<=N;i++)
		{
			unionFindForPercolationTest.union( XyTo1D(1,i),0);
			unionFindForPercolationTest.union( XyTo1D(N,i),N*N+1);
			unionFindForFullTest.union(XyTo1D(1,i),0);
		}
	}
	// open site (row i, column j) if it is not open already
	public void open(int i, int j)
	{
		if(!IndicesInBound(i,j))
			throw new IndexOutOfBoundsException();
		if(isOpen(i,j))
			return;
		openSites[i-1][j-1]=true;
		//Connect to open neighbours
		if(IndicesInBound(i-1,j))
		{
			if(isOpen(i-1,j))
			{
				unionFindForPercolationTest.union(XyTo1D(i-1,j),XyTo1D(i,j));
				unionFindForFullTest.union(XyTo1D(i-1,j),XyTo1D(i,j));
			}
		}
		if(IndicesInBound(i+1,j))
		{
			if(isOpen(i+1,j))
			{
				unionFindForPercolationTest.union(XyTo1D(i+1,j),XyTo1D(i,j));
				unionFindForFullTest.union(XyTo1D(i+1,j),XyTo1D(i,j));
			}
		}
		if(IndicesInBound(i,j-1))
		{
			if(isOpen(i,j-1))
			{
				unionFindForPercolationTest.union(XyTo1D(i,j-1),XyTo1D(i,j));
				unionFindForFullTest.union(XyTo1D(i,j-1),XyTo1D(i,j));
			}
		}
		if(IndicesInBound(i,j+1))
		{
			if(isOpen(i,j+1))
			{
				unionFindForPercolationTest.union(XyTo1D(i,j+1),XyTo1D(i,j));
				unionFindForFullTest.union(XyTo1D(i,j+1),XyTo1D(i,j));
			}
		}
		
	}
	 // is site (row i, column j) open?
	public boolean isOpen(int i, int j) 
	{
		if(!IndicesInBound(i,j))
			throw new IndexOutOfBoundsException();
		return openSites[i-1][j-1];
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) 
	{
		if(!isOpen(i,j))
			return false;
		return unionFindForFullTest.connected( XyTo1D(i,j),0);
	}
	// does the system percolate?
	public boolean percolates() 
	{
		if(N>1)
		{
			return unionFindForPercolationTest.connected(0, N*N+1);
		}
		else
		{
			return isOpen(1,1);
		}
	}
	public static void main(String[] args) {
		boolean percolates;
		Percolation perc = new Percolation(2);
		percolates = perc.percolates();
		perc.open(1, 1);
		percolates = perc.percolates();
	};
}
