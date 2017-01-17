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
	Percolation testSoil = new Percolation(sideSize);

	assertEquals("Static openSites: " + String.valueOf(0) , 0, testSoil.numberOfOpenSites());
	assertFalse("Static no perc after "+ String.valueOf(0), testSoil.percolates() );
	testSoil.open( 1, 1);
	assertFalse("Static no perc after "+ String.valueOf(1), testSoil.percolates() );
	assertTrue("Static full after "+ String.valueOf(1), testSoil.isFull(1,1) );
	testSoil.open( 2, 1);
	assertFalse("Static no perc after "+ String.valueOf(2), testSoil.percolates() );
	assertTrue("Static full after "+ String.valueOf(2), testSoil.isFull(2,1) );
	testSoil.open( 3, 1);
	assertFalse("Static no perc after "+ String.valueOf(3), testSoil.percolates() );
	assertTrue("Static full after "+ String.valueOf(3), testSoil.isFull(3,1) );
	testSoil.open( 4, 1);
	assertTrue("Static full after "+ String.valueOf(4), testSoil.isFull(4,1) );
	assertTrue("Perc after "+ String.valueOf(4), testSoil.percolates());
	//testSoil.open( 0, 4);
	assertEquals("Manually openSites: " + String.valueOf(4) , 4, testSoil.numberOfOpenSites());
	assertTrue ("man Open, row "
		    + String.valueOf(4)
		    + ", col " + String.valueOf(1)
		    , testSoil.isOpen( 4, 1) );
	
	assertTrue ("man Full, row "
		    + String.valueOf(4)
		    + ", col " + String.valueOf(1)
		    , testSoil.isFull( 4, 1) ); 
	
	assertTrue("man Percolation after a row is opened" , testSoil.percolates() );

	int loopTestSize = 300; 
	Percolation loopTest = new Percolation(loopTestSize);
	int col = 1;
	int index = 0;
	//loop over the columns
	while (col < (loopTestSize + 1)) {
	    //loop over each row in each column
	    int row = 1;
	    while (row < (loopTestSize + 1)){
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




