import java.util.Arrays;


public class Board {
	private int manhattan =-1;
	private int hamming =-1;
	private final int N;
	private final int tiles[][];
	// construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks)      
	{
		tiles=blocks;
		N=blocks[0].length;
	}
	private Board GetNeighborBoard(int i1,int j1,int i2,int j2)
	{
		int[][] neighborBoard = new int[tiles.length][tiles[0].length];
		for (int i = 0; i < tiles.length; i++)
			neighborBoard[i] = Arrays.copyOf(tiles[i], tiles[i].length);
		int tile1 = neighborBoard[i1][j1];
		neighborBoard[i1][j1] = neighborBoard[i2][j2];
		neighborBoard[i2][j2] = tile1;
		return new Board(neighborBoard);
	}
	// board dimension N
	public int dimension()  
	{
		return N;
	}
	// number of blocks out of place
	public int hamming()       
	{
		if(hamming==-1)
		{
			hamming=0;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(tiles[i][j]==0)
					{
						continue;
					}
					int correctElementAtCurrentPosition= i==(N-1)&&j==(N-1)? 0:(i*N+1)+j;
					int actualElementAtCurrentPosition=tiles[i][j];
					if(correctElementAtCurrentPosition!=actualElementAtCurrentPosition)
					{
						hamming++;
					}
				}
			}
		}
		return hamming;
	}
	// sum of Manhattan distances between blocks and goal
	public int manhattan()
	{
		if(manhattan==-1)
		{
			manhattan=0;
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					int tile=tiles[i][j];
					if(tile==0)
					{
						continue;
					}
					int realI=(tile-1)/N;
					int realJ=(tile-1)%N;
					int horizontalDistance= Math.abs(realI-i);
					int verticalDistance = Math.abs(realJ-j);
					manhattan+=(horizontalDistance+verticalDistance);
				}
			}
		}
		return manhattan;
	}
	// is this board the goal board?
	public boolean isGoal()      
	{
		return manhattan()==0;
	}
	// a board that is obtained by exchanging two adjacent blocks in the same row
	public Board twin()           
	{
		int[][] twinTiles = new int[tiles.length][tiles[0].length];
		for (int i = 0; i < tiles.length; i++)
			twinTiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
		if(twinTiles[N-1][0]!=0&&twinTiles[N-1][1]!=0)
		{
			int firstTile=tiles[N-1][0];
			twinTiles[N-1][0]=tiles[N-1][1];
			twinTiles[N-1][1]=firstTile;
		}
		else
		{
			int firstTile=tiles[0][0];
			twinTiles[0][0]=tiles[0][1];
			twinTiles[0][1]=firstTile;
		}
		return new Board(twinTiles);
	}
	// does this board equal y?
	public boolean equals(Object y)        
	{
		if (y == this) return true;
	    if (y == null) return false;
	    if (y.getClass() != this.getClass()) return false;
		Board that=(Board)y;
		return Arrays.deepEquals(this.tiles, that.tiles);
	}
	// all neighboring boards
	public Iterable<Board> neighbors()     
	{
		Stack<Board> neighbors = new Stack<Board>();
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(tiles[i][j]==0)
				{
					//empty at the top left corner
					if(i==0&&j==0)
					{
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));	
					}
					//empty at the bottom left corner
					if(i==(N-1)&&j==0)
					{
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));	
					}
					//empty at top right corner
					if(i==0&&j==(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));	
					}
					//empty at bottom right corner
					if(i==(N-1)&&j==(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));	
					}
					//empty at top row
					else if(i==0&&j>0&&j<(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));
					}
					//empty at bottom row
					else if(i==(N-1)&&j>0&&j<(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));
					}
					//empty at right column
					else if(j==(N-1)&&i>0&&i<(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
					}
					//empty at left column
					else if(j==0&&i>0&&i<(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
					}
					//movable in all directions
					else if(j>0&&j<(N-1)&&i>0&&i<(N-1))
					{
						neighbors.push(GetNeighborBoard(i,j,i,(j-1)));
						neighbors.push(GetNeighborBoard(i,j,i,(j+1)));
						neighbors.push(GetNeighborBoard(i,j,(i+1),j));
						neighbors.push(GetNeighborBoard(i,j,(i-1),j));
					}
					break;
				}
			}
		}
		return neighbors;
	}
	// string representation of this board (in the output format specified below)
	public String toString()               
	{
		  StringBuilder s = new StringBuilder();
		    s.append(N + "\n");
		    for (int i = 0; i < N; i++) {
		        for (int j = 0; j < N; j++) {
		            s.append(String.format("%2d ", tiles[i][j]));
		        }
		        s.append("\n");
		    }
		    return s.toString();
	}
	public static void main(String[] args)
    {
    	int tiles[][]={ {1, 2} , { 0, 3} };
    	Board board=new Board(tiles);
    	Board twin=board.twin();
    	assert(board!=twin);
    }
}
