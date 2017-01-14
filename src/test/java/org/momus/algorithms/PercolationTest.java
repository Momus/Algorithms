package org.momus.algorithms;

//import junit.framework.Test;
import junit.framework.TestCase;
//import junit.framework.TestSuite;

//import edu.princeton.cs.algs4.QuickFindUF;
//import edu.princeton.cs.algs4.StdRandom;

/**
 * Unit test for simple App.
 */
public class PercolationTest extends TestCase {
 
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
	int sideSize = 4; 
	int soilTests = sideSize * sideSize;
	Percolation testSoil = new Percolation(sideSize);

	assertEquals("Static openSites: " + String.valueOf(0) , 0, testSoil.numberOfOpenSites());
	testSoil.open( 0, 0);
	testSoil.open( 1, 0);
	testSoil.open( 2, 0);
	testSoil.open( 3, 0);
	//testSoil.open( 0, 4);
	assertEquals("Manually openSites: " + String.valueOf(4) , 4, testSoil.numberOfOpenSites());
	assertTrue ("man Open, row "
		    + String.valueOf(1)
		    + ", col " + String.valueOf(3)
		    , testSoil.isOpen( 3, 0) );
	
	assertTrue ("man Full, row "
		    + String.valueOf(1)
		    + ", col " + String.valueOf(3)
		    , testSoil.isFull( 3, 0) ); 
	
	assertTrue("man Percolation after a row is opened" , testSoil.percolates() );

	int loopTestSize = 5; 
	Percolation loopTest = new Percolation(loopTestSize);
	int col = 0;
	int index = 0;
	//loop over the columns
	while (col < loopTestSize) {
	    //loop over each row in each column
	    int row = 0;
	    while (row < loopTestSize){
		//Check how many there are
		assertEquals("Looped openSites: " + String.valueOf(index) , index, loopTest.numberOfOpenSites());

		//Then open one, and make sure that it's open and full.
		loopTest.open( row, col);
 		
		assertTrue ("loop Open, row "
			    + String.valueOf(row)
			    + ", col " + String.valueOf(col)
			    , loopTest.isOpen( row, col) );
		
		assertTrue ("loop Full, row "
			    + String.valueOf(row)
			    + ", col " + String.valueOf(col)
			    , loopTest.isFull( row, col) ); 
		row++;
		index++;
	    };
	    

	    assertTrue("loop percolates after row open"
		       + String.valueOf(row)
		       + " is opened" , loopTest.percolates() );

	    col++;
	};


	    
    }
}




