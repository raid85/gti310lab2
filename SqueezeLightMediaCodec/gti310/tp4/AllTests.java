package gti310.tp4;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestDCT.class);
		suite.addTestSuite(TestDecoupage8x8.class);
		suite.addTestSuite(TestZigZag.class);
		suite.addTestSuite(TestQuantification.class);
		suite.addTestSuite(TestConvertRGB2YUV.class);
		suite.addTestSuite(TestRLC.class);
		suite.addTestSuite(TestDPCM.class);
		//$JUnit-END$
		return suite;
	}

}
