package org.momus.algorithms;

//import junit.framework.Test;
import junit.framework.TestCase;
//import junit.framework.TestSuite;

//import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Unit test for simple App.
 */
public class PercolationTest extends TestCase {
 
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
	
	int soilTests = 10;
	//int soilTestSquare = soilTests * soilTests;
	Percolation testSoil = new Percolation(soilTests);

	int j = 0;
	int index = 0;
	while (j < soilTests) {
	    int i = 0;
	    
	    while (i < soilTests){
		//assertEquals("testing " + i, i +, testSoil.numberOfOpenSites() );
		//index = (soilTests * i) + j;
		assertEquals("openSites: " + String.valueOf(index) , index, testSoil.numberOfOpenSites());
		testSoil.open( i, j);
		//That (0,0) coordinate causes no end to edge-case 
		//problems. Check it.
		assertTrue ("isOpen" + String.valueOf(i), testSoil.isOpen( i, j) );
		assertTrue ("isFull" + String.valueOf(i), testSoil.isFull( i, j) );
		i++;
		
		index++;
	    };
	    
	    j++;
	    
	};

	assertEquals(true, testSoil.percolates() );


	// testSoil.open( 0, 0);
	// assertEquals(1, testSoil.numberOfOpenSites() );
	// assertFalse( testSoil.percolates() );
	// assertTrue("Zero box should be full", testSoil.isFull(0,0));
	// testSoil.open( 1, 0);
	// assertEquals(2, testSoil.numberOfOpenSites() );
	// assertFalse( testSoil.percolates() );
	// assertTrue("Four box is open" , testSoil.isOpen(1,0) );
	// assertTrue("Four box should be full", testSoil.isFull(1,0));
	// testSoil.open( 2, 0);
	// assertEquals(3, testSoil.numberOfOpenSites() );
	// assertTrue("Four box should be full", testSoil.isFull(1,0));
	// assertTrue("Fourth box connects to zero box", testSoil.connections.connected(4,0));
	// assertTrue("Fourth box connects to zero box", testSoil.connections.connected(4,0));
	// assertTrue("Eight box connects to zero box", testSoil.connections.connected(8,0));
	// assertTrue("Eight box connects to four box", testSoil.connections.connected(8,4));
	// assertTrue("Third box should be full", testSoil.isFull(2,0));
	// testSoil.open( 3, 0);
	// assertTrue( testSoil.isOpen(3,0) );
	// assertEquals(4, testSoil.numberOfOpenSites() );
	// assertTrue("percolates!", testSoil.percolates() );
	// assertTrue("isFull again", testSoil.isFull(3,0));
	// assertFalse("empty box is  not Full", testSoil.isFull(2,2) );
	// assertTrue("percolates", testSoil.percolates());

	//Open all the sites, see if they open.
	Percolation testOpen = new Percolation(4);
	testOpen.open(0,0);
	assertTrue ("isOpen--" + String.valueOf(0), testOpen.isOpen( 0, 0) );
	assertEquals("openSites: " + String.valueOf(1) , 1, testOpen.numberOfOpenSites());

	testOpen.open(1,0);
	assertTrue ("isOpen" + String.valueOf(1), testOpen.isOpen( 1, 0) );
	assertEquals("openSites: " + String.valueOf(2) , 2, testOpen.numberOfOpenSites());

	testOpen.open(2,0);
	assertTrue ("isOpen" + String.valueOf(2), testOpen.isOpen( 2, 0) );
	assertEquals("openSites: " + String.valueOf(3) , 3, testOpen.numberOfOpenSites());

	testOpen.open(3,0);
	assertTrue ("isOpen" + String.valueOf(3), testOpen.isOpen( 3, 0) );
	assertEquals("openSites: " + String.valueOf(4) , 4, testOpen.numberOfOpenSites());
	
	testOpen.open(0,1);
	assertTrue ("isOpen" + String.valueOf(4), testOpen.isOpen( 0, 1) );
	assertEquals("openSites: " + String.valueOf(5) , 5, testOpen.numberOfOpenSites());
	
	testOpen.open(1,1);
	assertTrue ("isOpen" + String.valueOf(5), testOpen.isOpen( 1, 1) );
	testOpen.open(2,1);
	assertTrue ("isOpen" + String.valueOf(6), testOpen.isOpen( 2, 1) );
	testOpen.open(3,1);
	assertTrue ("isOpen" + String.valueOf(7), testOpen.isOpen( 3, 1) );
	testOpen.open(0,2);
	assertTrue ("isOpen" + String.valueOf(8), testOpen.isOpen( 0, 2) );
	testOpen.open(1,2);
	testOpen.open(2,2);
	testOpen.open(3,2);
	testOpen.open(0,3);
	testOpen.open(1,3);
	testOpen.open(2,3);
	assertTrue("better percolate", testOpen.percolates());
	testOpen.open(3,3);


	
	//16 sites, more of a crooked opening.
	Percolation testFour = new Percolation(4);
	assertEquals(0, testFour.numberOfOpenSites() );
	assertEquals(false, testFour.percolates() );
	testFour.open( 0, 0);
	testFour.open( 1, 1);
	assertEquals("open two sites", 2, testFour.numberOfOpenSites() );
	assertFalse("sideways site won't fill", testFour.isFull(1,1));
	assertFalse( testFour.percolates() );
	testFour.open( 1, 0);
	assertEquals(3, testFour.numberOfOpenSites() );
	assertEquals(false, testFour.percolates() );
	assertFalse( testFour.percolates() );
	testFour.open( 2, 0);
	assertTrue("isFull method works", testFour.isFull(2,0));
	testFour.open( 3, 0);
	assertTrue( testFour.isOpen(3,0) );
	assertEquals(5, testFour.numberOfOpenSites() );
	assertEquals("percolates" , true, testFour.percolates() );
	//assertTrue("isFull again", testFour.isFull(3,0));
	assertFalse("not Full", testFour.isFull(2,2) );
	assertTrue("percolates", testFour.percolates());


	
	int zz = 10;
	Percolation simStats = new Percolation(zz);
	int row = StdRandom.uniform(zz);
	int col =  StdRandom.uniform(zz);
	simStats.open(row, col); //1
	assertEquals("first open", 1, simStats.numberOfOpenSites() );
	assertFalse("one site", simStats.percolates() );

	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //2
	//assertEquals("second open", 2, simStats.numberOfOpenSites() );
	assertFalse("two sites", simStats.percolates() );
	//
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //3
	assertEquals("third open", 3, simStats.numberOfOpenSites() );
	//assertFalse("three sites", simStats.percolates() );
	//
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //4
	assertEquals("foutrth open", 4, simStats.numberOfOpenSites() );
	//assertFalse("four sites", simStats.percolates() );
	//
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //5
	//assertEquals("fifth open", 5, simStats.numberOfOpenSites() );
	//assertFalse("five sites", simStats.percolates() );
	//
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //6
	//assertFalse("six sites", simStats.percolates() );

	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //7
	//assertFalse("seven sites", simStats.percolates() );
	
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //8
	assertFalse("eight sites", simStats.percolates() );


	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //9
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //10
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //11
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //12
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //13
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //14
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //15
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //16
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //17
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //18
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //19
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //20
	//assertFalse("eight sites", simStats.percolates() );

	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //21
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //22
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //23
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //24
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //25
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //26
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //27
	//assertFalse("eight sites", simStats.percolates() );
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //28
	//assertFalse("eight sites", simStats.percolates() );

	
	row = StdRandom.uniform(zz);
	col =  StdRandom.uniform(zz);
	simStats.open(row, col); //29
	//assertTrue("tons of sites", simStats.percolates() );
	//assertEquals("lots open", 9, simStats.numberOfOpenSites() );


	    
    }
}
