package org.momus.algorithms;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
	testSoil.open( 0, 0);
	assertEquals(1, testSoil.numberOfOpenSites() );
	

    }
}
