package org.momus.algorithms;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.*;




/**
 * Unit test for simple App.
 */
public class PercolationStatsTest

    extends TestCase
{

	
	public static Field[] length;
	
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
	//PercolationStats percRun = new PercolationStats(4, 10);

	//length = PercolationStats.class.getFields();
	
	//Method deIndexMethod = PercolationStats.getDeclairedMethod(deIndex);
	//length.setAccessible("true");
	//assertEquals("Results array is right size", 100, testStatCase.results.length);
	//assertEquals("Number of trials correct", 100, testStatsCase.trials);
	//assertEquals("N must be n", 10, testStatCase.n);
	//assertFalse( (testStatCase.mean() == 0) );
    }
    
    public static void main( String args[])
    {
    	//for (Field f : length )
    	//System.out.print(f);
    }
}

