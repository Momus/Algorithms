
/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats
 *  Dependencies: StdOut.java
 *  Data files: none
 *
 *  
 *  Monte Carlo simulation. To estimate the percolation threshold:
 *    - Initialize all sites to be blocked.
 *    - Repeat the following until the system percolates:
 *        * Choose a site uniformly at random among all blocked sites.
 *        * Open the site.
 *     - The fraction of sites that are opened when the system percolates 
 *       provides an estimate of the percolation threshold.
 *  
 *
 *  public class PercolationStats {
 *  public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
 *  public double mean()                          // sample mean of percolation threshold
 *  public double stddev()                        // sample standard deviation of percolation threshold
 *  public double confidenceLo()                  // low  endpoint of 95% confidence interval
 *  public double confidenceHi()                  // high endpoint of 95% confidence interval

 *  public static void main(String[] args)        // test client (described below)
 *  }

 *
 * % java PercolationStats
 *  
 *
 ******************************************************************************/
package org.momus.algorithms;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {

    //   private Percolation testCase;
    private double[] results;
    private int trials;
    private int n;
    private double mean;
    private double stddev;
    
    private int N;

    

    
    /**
     * Removes an Integer from positionsRemaining, and returns it in
     * the form of Cartesian coordiatates.
     *
     * @return { row, col }
     */
    private int[] grabASpot(ArrayList<Integer> testList) {
	int spot = StdRandom.uniform( testList.size() );
	int sweetSpot = testList.remove( spot  ) ;
	int[] sweetCartesians = deIndex(sweetSpot);
	return sweetCartesians;
    }

    
    /**
     * Turns an index of a square matrix into its Cartesian
     * coordiantes.
     *
     * @return { int row , int col }
     **/
    private int[] deIndex(int index) {
	int row = ( index / n ); //peculiarities of integer division
	int col =  index == 0 ? 0 : ( index % n ); //don't take the modulus of zero
	int z[] = {row, col};
	return z;
    }

    
    /**
     * perform trials independent experiments on an n-by-n grid
     *
     */
    public PercolationStats(int n, int trials) {
	// both the n of the test and number of trials have to be
	// non-zero
	this.n = n;
	N = n * n;
	this.trials = trials;
	
	if ( n < 1 || trials < 1) {
	    throw new IllegalArgumentException ("One or greater, please.");
	};
	
	
	results = new double[trials];

	/**
	 * Fill the array used to keep track of empty spaces with
	 * consecutive integers. This object will be cloned in the
	 * loop below it.
	 **/
	ArrayList<Integer> listOfClosedPositions = new ArrayList<Integer>(N) ;

	int z = 0;
	while (listOfClosedPositions.size() < N) {
	    Integer zz = z;
	    listOfClosedPositions.add(z, zz);
	    z++;
	};

	//for as many times as specified
	int run = 0;
	while ( run < trials) {

	    Percolation testCase = new Percolation(n);
	    
	    ArrayList<Integer> testList = new ArrayList<Integer>(listOfClosedPositions);

	    int counter = 0;
	
	    do {
		//
		int[] coordinates = grabASpot(testList);
		testCase.open((coordinates[0] + 1), (coordinates[1] + 1));
		counter++ ;
	    } while ( !testCase.percolates() );
	    
	    results[run] = (counter * 1.0) / (N * 1.0 );  
	    run++;
	    
	}
	
	//calculate the mean and stddev
	mean =  StdStats.mean(results) ;
	stddev = StdStats.stddev(results);
    }  

    
    /**
     * return the arithmetic mean of the results matrix.
     *

    public double mean() {
	return mean;
    };
     */


    public double mean() {
	double total = 0;
	for (double run : results) {
	    total += run;
	}

	return total/results.length ;

    };

    
    public double stddev() {

	return stddev;
    }


    public double confidenceLo() {
	
	return (mean - ( 1.96 * stddev / Math.sqrt(trials) ) ) ;
    }

    
    public double confidenceHi() {
	return (mean + ( 1.96 * stddev / Math.sqrt(trials) ) ) ;
    }

    
    public static void main(String[] args) {

	int numberz = Integer.parseInt(args[0]);
	int trialz =  Integer.parseInt(args[1]);
	
	PercolationStats testRun = new PercolationStats(numberz, trialz);
	
	StdOut.print(  "mean                    = " + Double.toString( testRun.mean() ) +"\n"
		     + "stddev                  = " + Double.toString( testRun.stddev() ) + "\n"
		     + "95% confidence interval = " + Double.toString( testRun.confidenceLo() ) + ", "
		     + Double.toString( testRun.confidenceHi() )+ "\n"
		     );
    }
}
