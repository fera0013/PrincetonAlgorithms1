public class Solver {
	private Board initialBoard;
	private SearchNode currentNode;
	private SearchNode currentTwinNode;
	private Boolean isSolvable=null;
	private class SearchNode implements Comparable<SearchNode> 
	{
		Board board;
		SearchNode previous;
		int numberOfMoves;
		int priority;
		int manhattan;
		@Override
		public int compareTo(SearchNode that) {
			// TODO Auto-generated method stub
			return this.priority- that.priority;
		}
	};
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) 
    {
    	if(initial==null)
    	{
    		throw new java.lang.NullPointerException();
    	}
    	this.initialBoard = initial;
    }
 // is the initial board solvable?
    public boolean isSolvable()         
    { 
    	if(isSolvable!=null)
    	{
    		return isSolvable;
    	}
    	MinPQ<SearchNode> boardQueue = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();
    	
    	SearchNode node = new SearchNode();
    	node.board=this.initialBoard;
    	node.previous=null;
    	node.numberOfMoves=0;
    	node.manhattan=this.initialBoard.manhattan();
    	boardQueue.insert(node);
    	
    	SearchNode twinNode = new SearchNode();
    	Board twinBoard = this.initialBoard.twin();
    	twinNode.board=twinBoard;
    	twinNode.previous=null;
    	twinNode.numberOfMoves=0;
    	twinNode.manhattan=twinBoard.manhattan();
    	twinQueue.insert(twinNode);
    	
    	currentNode=boardQueue.delMin();
    	currentTwinNode= twinQueue.delMin();
    	while(!currentNode.board.isGoal()&&!currentTwinNode.board.isGoal())
    	{
    		for(Board neighbor:currentNode.board.neighbors())
    		{
    			SearchNode previousNode = currentNode.previous;
    			
    			if(previousNode!=null)
    			{
    				if(previousNode.board.equals(neighbor))
    				{
    					continue;
    				}
    			}
    			SearchNode newNode = new SearchNode();
    			newNode.numberOfMoves =currentNode.numberOfMoves+1;
        		newNode.previous=currentNode;
        		newNode.board=neighbor;
        		newNode.priority=newNode.board.manhattan()+newNode.numberOfMoves;
        		newNode.manhattan=newNode.board.manhattan();
        		boardQueue.insert(newNode);
    		}
    		currentNode=boardQueue.delMin();
    		for(Board neighbor:currentTwinNode.board.neighbors())
    		{
    			SearchNode previousNode = currentTwinNode.previous;
    			
    			if(previousNode!=null)
    			{
    				if(previousNode.board.equals(neighbor))
    				{
    					continue;
    				}
    			}
    			SearchNode newNode = new SearchNode();
    			newNode.numberOfMoves =currentTwinNode.numberOfMoves+1;
        		newNode.previous=currentTwinNode;
        		newNode.board=neighbor;
        		newNode.priority=newNode.board.manhattan()+newNode.numberOfMoves;
        		newNode.manhattan=newNode.board.manhattan();
        		twinQueue.insert(newNode);
    		}
    		currentTwinNode=twinQueue.delMin();
	   }
    	isSolvable= currentNode.board.isGoal();
    	return isSolvable;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()     
    {
    	if(!isSolvable())
    	{
    		return -1;
    	}
    	else
    	{
    		return currentNode.numberOfMoves;
    	}
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()      
    {
    	if(!this.isSolvable())
    	{
    		return null;
    	}
    	else
    	{
	    	Stack<Board> solutionTrace=new Stack<Board>();
	    	solutionTrace.push(currentNode.board);
	    	while(currentNode.previous!=null)
	    	{
	    		solutionTrace.push(currentNode.previous.board);
	    		currentNode=currentNode.previous;
	    	}
	    	return solutionTrace;
    	}
    }
    public static void main(String[] args)
    {
    	  // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }
}

