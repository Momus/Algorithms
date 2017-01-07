package org.momus.algorithms;

//import junit.framework.Test;
import junit.framework.TestCase;
//import junit.framework.TestSuite;

//import edu.princeton.cs.algs4.QuickFindUF;

/**
 * Unit test for simple App.
 */
public class PercolationTest extends TestCase {
 
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
	Percolation testSoil = new Percolation(4);
	assertEquals(0, testSoil.numberOfOpenSites() );
	assertEquals(false, testSoil.percolates() );
	//That (0,0) coordinate causes no end to edgje-case
	//problems. Check it.
	testSoil.open( 0, 0);
	assertEquals(1, testSoil.numberOfOpenSites() );
	assertFalse( testSoil.percolates() );
	testSoil.open( 1, 0);
	assertEquals(2, testSoil.numberOfOpenSites() );
	assertEquals(false, testSoil.percolates() );
	assertFalse( testSoil.percolates() );
	testSoil.open( 2, 0);
	assertTrue("isFull method works", testSoil.isFull(2,0));
	testSoil.open( 3, 0);
	assertTrue( testSoil.isOpen(3,0) );
	assertEquals(4, testSoil.numberOfOpenSites() );
	assertEquals(true, testSoil.percolates() );
	assertTrue("isFull again", testSoil.isFull(3,0));
	assertFalse("not Full", testSoil.isFull(2,2) );
    }
}
