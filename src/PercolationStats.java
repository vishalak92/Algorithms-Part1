/******************************************************************************
 *  Name: Vishal Khatri
 *  Date: 27 December, 2018
 *  Purpose of the Program: This class is used to calculate statistics by 
 							performing a series of T experiments on n*n grid 
 							for percolation.

 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
		
		
		private final double [] trialResults;
		private int numberOfTrials = 0;
		
		private double mean = 0;
		private double stdDev = 0;
		public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
		{
			if(n<=0 || trials<=0)
			{
				throw new java.lang.IllegalArgumentException("Gridsize and number of trails cannot be less than 1");
			}
			
			trialResults = new double[trials];
			numberOfTrials = trials;
			int currentTrial = 0;
			int rowIndex = 0;
			int colIndex = 0;
			while (currentTrial < trials)
			{
				Percolation percolation = new Percolation(n);
				while(!percolation.percolates())
				{
					rowIndex = StdRandom.uniform(1, n+1);
					colIndex = StdRandom.uniform(1, n+1);
					percolation.open(rowIndex, colIndex);
					
				}
				trialResults[currentTrial] = (double)percolation.numberOfOpenSites()/(n*n);
				currentTrial++;
				
			}
			
		}   
		public double mean()                          // sample mean of percolation threshold
		{
			mean = StdStats.mean(trialResults);
			return mean;
			 
		}   
		public double stddev()                        // sample standard deviation of percolation threshold
		{
			stdDev = StdStats.stddev(trialResults);
			return stdDev;
			
		}   
		public double confidenceLo()                  // low  endpoint of 95% confidence interval
		{
			return (mean() - ((1.96 * stddev())/Math.sqrt(numberOfTrials)));
		}   
		public double confidenceHi()                  // high endpoint of 95% confidence interval
		{
			return (mean() + ((1.96 * stddev())/Math.sqrt(numberOfTrials)));
		}
		   
		public static void main(String[] args)        // test client (described below)
		{
			int gridSize = Integer.parseInt(args[0]);
			int trials = Integer.parseInt(args[1]);
			PercolationStats percolationStats = new PercolationStats(gridSize, trials);
			
			
			StdOut.println("mean                    = "+percolationStats.mean());
			StdOut.println("stddev                  = "+percolationStats.stddev());
			StdOut.println("95% confidence interval = ["+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi()+"]");
			
			
		}
		

}
