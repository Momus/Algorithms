
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
 *  public    void open(int row, int col)    // open site (row, col) if it isn't already
 *  public boolean isOpen(int row, int col)  // is site (row, col) open?
 *  public boolean isFull(int row, int col)  // is site (row, col) full?
 *  public     int numberOfOpenSites()       // number of open sites
 *  public boolean percolates()              // does the system percolate?

*  @author Dmitri G. Brengauz for Sedgwick's Algorithms I
*
 ******************************************************************************/
package org.momus.algorithms;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;
 

public class Percolation {

    /**
     *  Model: the "grid" is composed of two objects: the boolean[]
     *  array grid, which stores the open/closed state of each
     *  element, and the WeightedQuickUnionUF object that stores
     *  connection information.
     **/
    private int n;  //length of a single virtual row.
    private int N;  //total elements in the system == n^2

    private boolean[] grid;
    private WeightedQuickUnionUF connections;

    /**
     * Take a cartesian representation of a cell
     * (row, column) and returns its array index.
     */
    private int index(int row, int col) {
	return (n * row) + col;
    }

    
    /**
     *  Construct a {@code Percolation} object: an n-by-n grid,
     *  initially with all sites blocked.
     *
     *  @param n length of a (virtual) row (or column) of the square
     *  grid.
     */
    public Percolation(int n)  {

	
	this.n = n;
	this.N = n * n;
	
	if (n < 1 ) {
	    throw new IllegalArgumentException ( String.valueOf(n) + " is less than one.");
	};
	
	
	/**
	 * Per Java default, all boolean arrays are initialized with
	 * all values set to "false," which represents the closed
	 * state in this model.
	 **/ 
	this.grid = new boolean[N];

	this.connections = new WeightedQuickUnionUF(N);
	
    }
    
  

    /**
     * Opens a cell. This means marking it as open on the grid, as well
     * as checking if the cells above, below, left and right of the
     * target cell are open, and, if so, connecting them on the
     * WeightedQuickUnionUF object.
     *
     * @param int row, int column
     */
    public void open(int r, int c) {
	//Convert external 1-based row, col represntation to zero
	//based.
	int row = r - 1;
	int col = c - 1;

	if ( (row >=n) || (col >= n) ) {
	    throw new IndexOutOfBoundsException ("Trying to open cell not in the grid: "
						+ "row: " +String.valueOf(row)
						+ ", col: " +String.valueOf(col)
						+ ", n: " + String.valueOf(n)); 
						};
	    

	int cell = index(row, col);

	if (!grid[cell]){
	    
	    grid[cell] = true;
	
	    int above = cell - n;
	    if  ( (row > 0) && grid[above] ) {
		connections.union(cell, above);
		
	    };

	    int below = (cell + n);
	    if ( (row < n) && (below < N) && grid[below]  ) {
		connections.union(cell, below);
		
	    };
	
	    int left = (cell - 1); 
	    if ( (col > 0) && grid[left] ) {
		connections.union(cell, left);
		
	    };
	
	    int right = (cell + 1);
	    if ( (col < (n - 1)) && grid[right]  )  {
		connections.union(cell, right);
		
	    };

	};
    }

    
    /**
     * retrives the value of grid[spot] which indicates if the site is
     * open or closed.
     *
     * @param row, col : the ordered pair representations of a cell
     * address.
     **/
    public boolean isOpen(int r, int c){
	//Convert external 1-based row, col represntation to zero
	//based.
	int row = r - 1;
	int col = c - 1;
	
	int target = index(row,col);
	if (target > (N - 1)){
	    throw new IndexOutOfBoundsException ("Can't check a cell outside matrix: "
						+ "row: " +String.valueOf(row)
						+ ", col: " +String.valueOf(col)
						+ ", n: " + String.valueOf(n)); 
	};
	
	return grid[target];
    }

    
    public int numberOfOpenSites() {  

	int count = 0;
	 for ( int i = 0; i < N; i++)
	     if (grid[i]) {
	 	    count++;
	 	}
	return count;
    }


    /**
     * A full site is an open site that can be connected to an open
     * site in the top row via a chain of neighboring (left, right,
     * up, down) open sites.
    **/
    public boolean isFull(int r, int c) {

	// internal rows and columns are zero based,
	int target = index(r - 1, c - 1);
	int i = 0;

	if ( (0 > target) || (target  > (N - 1))){
	    throw new IndexOutOfBoundsException ("Can't check a cell outside matrix: "
						 + "row: " +String.valueOf(r)
						 + ", col: " +String.valueOf(c)
						+ ", n: " + String.valueOf(n)); 
	};
	

	if ( isOpen(r, c) ) {
	    // "Top row" are cells with indices 0 through n - 1
	    while (i < n ){
		if ( connections.connected(i, target) ) {
		    return true; } else {
		    i++; }
	    };
	};
	return false;
    }


    /**
     *  "A system that percolates lets water fill open sites, flowing
     *  from top to bottom."
     **/
    public boolean percolates(){

	int col = 1; //one-based columns
	int bottomRow = n; //one-based!!!
	
	while (col <= n  ){
	    if ( isFull(bottomRow, col)) {
		return true;
	    } else col++;
	};
	
	return false;
    }

    
}
