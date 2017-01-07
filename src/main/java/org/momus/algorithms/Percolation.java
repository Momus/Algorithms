
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


import edu.princeton.cs.algs4.QuickFindUF;



public class Percolation {

    // N is the total elements in the system n^2 = N
    public int N = 0;
    // little n, the length of a single row, is also stored for
    // conveniance for now.
    public int n = 0;

    //These are the two data structures which represent the semi-porus
    //system:
    public boolean[] grid;
    public QuickFindUF connections;

    
    /**
     * n-by-n grid, initially with all sites blocked.
     */
    public Percolation(int rowLength)  {
	this.n = rowLength;
	this.N = n * n;
	this.grid = new boolean[N];
	//Set all sites to false explicitly.
	for ( int i = 0; i < N; i++) {
		this.grid[i] = false;
	    }
	
	this.connections = new QuickFindUF(N);
	
    }
    
    
    private int index(int row, int col) {
	return (n * row) + col;
    }

	
    public void open(int row, int col) {
	int target = index(row, col);
	this.grid[target] = true;

	// Check to see if any of the adjacent cells are also open, if
	// so, "connect them." Check to see if the cells are 
	
	// if target is not in the top row
	if ( target > (n -1) ) {
	    // get the cell below
	    int below = index( row - 1, col );
	    // connect it if it's open
	    if (this.grid[below])
		{ this.connections.union(target, below); }
	};

	
	// if target is not in the bottom row
	if ( target > (N - n) ) {
	    // get the cell above
	    int above = index( row + 1 , col ) ;
	    // connect it if it's open
	    if (this.grid[above])
		{ this.connections.union(target, above); };
	};

	// if target isn't in the leftmost column
	if ( target != 0 ) {
	    if ( (N % target) != 0) {
		// get its less woke neighbor.
		int left = index( row, col + 1) ;
		if (this.grid[left])
		    { this.connections.union(target, left); }
	    };
	    
	    // If not in the regressive rightmost row.
	    if ( N % (target + 1) == 0 ) {
		int right = index( row, col - 1 ) ;
		if (this.grid[right])
		{ this.connections.union(target, right); }
	    };
	};
    }

    
    public boolean isOpen(int row, int col){
	return this.grid[index(row,col)];
    }

    
    public int numberOfOpenSites(){
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
	    while (i < (n-1) ){
		if ( this.connections.connected(i, target) ) {
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
