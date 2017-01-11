package org.momus.algorithms;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class PercolationStatsTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public  PercolationStatsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testStats()
    {
	PercolationStats testStatCase = new PercolationStats(10,10);
	//assertEquals("Results array is right size", 100, testStatCase.results.length);
	//assertEquals("Number of trials correct", 100, testStatCase.trials);
	//assertEquals("N must be n", 10, testStatCase.n);
	assertFalse( (testStatCase.mean() == 0) );
    }
}
