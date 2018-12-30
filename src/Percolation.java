/******************************************************************************
 *  Name: Vishal Khatri
 *  Date: 27 December, 2018
 *  Purpose of the Program: This class is used to check if a given n*n grid
 							system percolates with a given series of 'site open'
 							instructions from the users. It uses the 
 							WeightedQuickUnionUF data structure to achieve the
 							same.

 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private final WeightedQuickUnionUF weightedQuickUnionUF; 
	private boolean[][] openSites = null; 	
	private final int gridSize;
	private int numberOfOpenSites = 0;
	private final int topNode;
	private final int bottomNode;
	private int currOneDimensionIndex = 0;
	
	public Percolation(int n) // create n-by-n grid, with all sites blocked 
	{
		if(n<=0)
		{
			throw new java.lang.IllegalArgumentException("Value of N cannot be less than or equal to zero");  
		}
		gridSize = n;
		weightedQuickUnionUF = new WeightedQuickUnionUF((n*n)+2);
		openSites = new boolean[n][n];
		topNode = (n*n);
		bottomNode = (n*n)+1;
		
		
	}
	   
	public void open(int row, int col)    // open site (row, col) if it is not open already
	{
		
		currOneDimensionIndex = getOneDimensionIndex(row, col);
		if(!isOpen(row, col))
		{
			openSites[row-1][col-1] = true;
			numberOfOpenSites++;
		}
		
		if(row == 1)
		{
			weightedQuickUnionUF.union(currOneDimensionIndex, topNode); 
		}
		
		if(row == gridSize)
		{
			weightedQuickUnionUF.union(currOneDimensionIndex, bottomNode); 
		}
		
		connectIfValid(row-1,col);
		connectIfValid(row, col-1); 
		connectIfValid(row+1, col);
		connectIfValid(row, col+1); 
		
	}   
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		isValidIndex(row, col);
		return (openSites[row-1][col-1] == true);
	}   
	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		isValidIndex(row, col);
		return weightedQuickUnionUF.connected(topNode, getOneDimensionIndex(row, col));
	}   
	public int numberOfOpenSites()       // number of open sites
	{
		return numberOfOpenSites;
	}   
	public boolean percolates()              // does the system percolate?
	{
		return (weightedQuickUnionUF.connected(topNode, bottomNode));
	}
	
	private int getOneDimensionIndex(int row, int col)
	{
		isValidIndex(row, col);
		return ((row-1)*gridSize)+(col-1);
	}
	
	private boolean isValidIndex(int row, int col)
	{
		if(row < 1 || row>gridSize)
		{
			throw new java.lang.IllegalArgumentException("The row index "+row+" is not valid.");
		}
		else if (col < 1 || col > gridSize)
		{
			throw new java.lang.IllegalArgumentException("The column index "+col+" is not valid.");
		}
		else
		{
			return true;
		}
	}
	
	private void connectIfValid(int row, int col)
	{
		try
		{
			if(isValidIndex(row, col))
			{
				if(isOpen(row, col))
				{
					int adjacentOneDimensionIndex = getOneDimensionIndex(row, col);
					if(!weightedQuickUnionUF.connected(currOneDimensionIndex,adjacentOneDimensionIndex))
					{
						weightedQuickUnionUF.union(currOneDimensionIndex, adjacentOneDimensionIndex); 
					}
				}
				
			}
		}
		catch (IllegalArgumentException e)
		{
			
		}
		
		
		
	}
}
