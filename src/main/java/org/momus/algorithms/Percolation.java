
/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation
 *  Dependencies: QuickFindUF.algs4
 *  Data files: none
 *
 *  
 *
 * % java Percolation
 *
 *
 *  public class Percolation {
 *                 // create n-by-n grid, with all sites blocked
 *  public    void open(int row, int col)    // open site (row, col) if it is not open already
 *  public boolean isOpen(int row, int col)  // is site (row, col) open?
 *  public boolean isFull(int row, int col)  // is site (row, col) full?
 *  public     int numberOfOpenSites()       // number of open sites
 *  public boolean percolates()              // does the system percolate?

*  
*
 ******************************************************************************/
package org.momus.algorithms;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;
 


public class Percolation {

    // N is the total elements in the system n^2 = N
    private int N;
    // little n, the length of a single row, is also stored for
    // conveniance for now.
    private int n;

    //These are the two data structures which represent the semi-porous
    //system:
    private boolean[] grid;
    private WeightedQuickUnionUF connections;

    
    /**
     * n-by-n grid, initially with all sites blocked.
     */
    public Percolation(int rowLength)  {
	n = rowLength;
	N = n * n;
	grid = new boolean[N];
	//Set all sites to false explicitly.
	for ( int i = 0; i < N; i++) {
		this.grid[i] = false;
	    }
	
	connections = new WeightedQuickUnionUF(N);
	
    }
    
    
    private int index(int row, int col) {
	return (n * row) + col;
    }

	
    public void open(int row, int col) {
	int target = index(row, col);

	//Open the square
	grid[target] = true;

	// Check to see if any of the adjacent cells are also open, if
	// so, "connect them."
	
	// if target is not in the top row
	if ( target >= n  ) {
	    // get the cell above
	    int below = index( row - 1, col );
	    // connect it if it's open
	    if (grid[below])
		{ connections.union(target, below); }
	};

	
	// if target is not in the bottom row
	if ( target < (N - n) ) {
	    // get the cell below
	    int above = index( row + 1 , col ) ;
	     // connect it if it's open
	    if (grid[above])
		{ connections.union(target, above); };
	};

	// if target isn't in the leftmost column
	if ( target != 0 ) {
	    if ( (N % target) != 0) {
		// get its more woke neighbor to the left
		int left = index( row, col - 1) ;
		if (grid[left])
		    { connections.union(target, left); }
	    };
	    
	    // If not in the regressive rightmost row.
	    if ( N % (target + 1) != 0 ) {
		int right = index( row, col + 1 ) ;
		if (grid[right])
		{ connections.union(target, right); }
	    };
	};
    }

    
    public boolean isOpen(int row, int col){
	return grid[index(row,col)];
    }

    
    public int numberOfOpenSites() {  

	int count = 0;
	 for ( int i = 0; i < N; i++)
	     if (grid[i]) {
	 	    count++;
	 	}
	return count;
    }

    // A full site is an open site that can be connected to an open
    // site in the top row via a chain of neighboring (left, right,
    // up, down) open sites.
    public boolean isFull(int row, int col) {

	int target = index(row, col);
	boolean test = false;
	int i = 0;

	if ( isOpen(row, col) ) {
	    // "Top row" are cells with indices 0 --n -1
	    while (i < n ){
		if ( connections.connected(i, target) ) {
		    test = true; };
		i++;
	    };
	};

	return test;
    }


    // "A system that percolates lets water fill open sites, flowing
    // from top to bottom."
    
    public boolean percolates(){
	
	boolean test = false;
	int i = 0;
	int bottomRow = n - 1;
	
	while (i < n ){
	    if ( isFull(bottomRow, i))
		 { test = true;}
	    i++;
	};
	
	return test;
    }


}
