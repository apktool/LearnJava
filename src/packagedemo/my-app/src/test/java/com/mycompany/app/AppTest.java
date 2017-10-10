package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
		System.out.println("break1->|" + testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
		System.out.println("break0");
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
		System.out.println("break2");
		Assert.assertEquals(App.getHelloWorld(), "Hello World");
    }

	public void testHello() {
		System.out.println("break3");
		Assert.assertEquals(App.getHelloJava(), "Hello Java");
	}
}
