
public class PercolationStats {
	// perform T independent experiments on an N-by-N grid
	private double [] fractionOfOpenSites;
	public PercolationStats(int N, int T)    
	{
		if(N<1||T<=0)
			throw new IllegalArgumentException();
		fractionOfOpenSites=new double[T];
		for(int i=0;i<T;i++)
		{ 
			Percolation percolation = new Percolation(N);
			while(!percolation.percolates())
			{
				int j = StdRandom.uniform(1,N+1);
				int k = StdRandom.uniform(1,N+1);
				percolation.open(j, k);
			}
			int numberOfOpenSites=0;
			for(int j=1;j<=N;j++)
			{
				for(int k=1;k<=N;k++)
				{
					if(percolation.isOpen(j, k))
					{
						numberOfOpenSites++;
					}
				}
			}
			fractionOfOpenSites[i]= numberOfOpenSites/((double)N*N);
		}
	}
	// sample mean of percolation threshold
	public double mean() 
	{
		return StdStats.mean(fractionOfOpenSites);
	}
	// sample standard deviation of percolation threshold
	public double stddev() 
	{
		return StdStats.stddev(fractionOfOpenSites);
	}
	// low  endpoint of 95% confidence interval
	public double confidenceLo()
	{
		return mean()-1.96*stddev()/Math.sqrt(fractionOfOpenSites.length);
	}
	// high endpoint of 95% confidence interval
	public double confidenceHi()  
	{
		return mean()+1.96*stddev()/Math.sqrt(fractionOfOpenSites.length);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int N = 200;        // input file
        int T = 100; 
        PercolationStats stats = new PercolationStats(N,T);
        Out out = new Out();
        System.out.println("mean= "+stats.mean());
        System.out.println("mean= "+stats.stddev());
        System.out.println("95% confidence interval= "+stats.confidenceLo()+","+stats.confidenceHi());
	}

}
