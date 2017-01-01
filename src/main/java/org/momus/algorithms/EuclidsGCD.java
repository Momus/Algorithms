package org.momus.algorithms;

import java.math.BigInteger ; 

public class EuclidsGCD {

    
    public static void main (String[] args) {
	BigInteger p = new BigInteger(args[0]) ;
	BigInteger q = new BigInteger(args[1]) ;
	BigInteger[] WhatAUselessWayToPassArgs =  { p, q } ;

	BigInteger[] thisGCD = gcd(WhatAUselessWayToPassArgs) ;
	BigInteger theAnswer = thisGCD[0] ;
	
	System.out.print( theAnswer.toString() + "\n" ) ;

    }

    
    /**
     * Euclid's <code>GCD</code> algorithm.
     *
     * @param args an <code>int</code> takes the first two ints in an
     * array.
     * @return an <code>int</code> the greatest common denominator.
     */
    private static BigInteger[]  gcd  (BigInteger[] args) {

	BigInteger p = args[0] ;
	BigInteger q = args[1] ;
	
	if (q.equals(BigInteger.ZERO)) {
	    BigInteger[] FinalReturn = { p } ;
		return FinalReturn ;
	    }
	else {
	BigInteger r = p.remainder(q) ;
	BigInteger[ ] newArgs = { q, r, } ;
	return gcd(newArgs) ;
	}
    }

}
