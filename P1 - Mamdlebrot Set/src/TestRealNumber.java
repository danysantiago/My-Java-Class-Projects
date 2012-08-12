import junit.framework.TestCase;


public class TestRealNumber extends TestCase {

	public void testRealNumberDouble() {
		RealNumber N = new RealNumber(19.23);
		double n = 19.23;
		assertTrue(n == N.getValue());
	}

	public void testRealNumberRealNumber() {
		RealNumber N1 = new RealNumber(89.23);
		RealNumber N2 = new RealNumber(89.23);
		assertTrue(N1.getValue() == N2.getValue());
	}

	public void testGetValue() {
		RealNumber N = new RealNumber(19.2323);
		double n = 19.2323;
		assertTrue(n == N.getValue());

	}

	public void testAbsoluteValue() {
		RealNumber N = new RealNumber(-1219.2323);
		double n = 1219.2323;
		assertTrue(n == N.absoluteValue().getValue());
	}

	public void testAdd() {
		RealNumber N1 = new RealNumber(9238);
		RealNumber N2 = new RealNumber(123.3943);
		double n1 = 9238;
		double n2 = 123.3943;
		assertTrue((n1+n2) == N1.add(N2).getValue());
		
	}

	public void testMultiply() {
		RealNumber N1 = new RealNumber(9238);
		RealNumber N2 = new RealNumber(123.3943);
		double n1 = 9238;
		double n2 = 123.3943;
		assertTrue((n1*n2) == N1.multiply(N2).getValue());

		N1 = new RealNumber(-218);
		N2 = new RealNumber(0.11);
		n1 = -218;
		n2 = 0.11;
		assertTrue((n1*n2) == N1.multiply(N2).getValue());

	}

	public void testSubstract() {
		RealNumber N1 = new RealNumber(9238);
		RealNumber N2 = new RealNumber(-123.3943);
		double n1 = 9238;
		double n2 = -123.3943;
		assertTrue((n1-n2) == N1.subtract(N2).getValue());

		N1 = new RealNumber(238123);
		N2 = new RealNumber(23);
		n1 = 238123;
		n2 = 23;
		assertTrue((n1-n2) == N1.subtract(N2).getValue());

	}

	public void testDivide() {
		RealNumber N1 = new RealNumber(823.2);
		RealNumber N2 = new RealNumber(0.3943);
		double n1 = 823.2;
		double n2 = 0.3943;
		assertTrue((n1/n2) == N1.divide(N2).getValue());

		N1 = new RealNumber(200);
		N2 = new RealNumber(0.003);
		n1 = 200;
		n2 = 0.003;
		assertTrue((n1/n2) == N1.divide(N2).getValue());

	}

	public void testSqrt() {
		RealNumber N = new RealNumber(23712);
		double n = 23712;
		assertTrue(Math.sqrt(n) == N.sqrt().getValue());
	}

	public void testSqr() {
		RealNumber N = new RealNumber(-0.2312);
		double n = -0.2312;
		assertTrue(n*n == N.sqr().getValue());
}

	public void testEqualsRealNumber() {
		RealNumber N1 = new RealNumber(Math.sqrt(4)*Math.sqrt(4));
		RealNumber N2 = new RealNumber(4);
		assertTrue(N1.equals(N2));
	}

	public void testCompareTo() {
		RealNumber N1 = new RealNumber(Math.sqrt(4)*Math.sqrt(4));
		RealNumber N2 = new RealNumber(4);

		assertTrue(N1.compareTo(N2) == 0);
		
		RealNumber N3 = new RealNumber(-0.0032);
		assertTrue(N1.compareTo(N3) > 0);
		
		RealNumber N4 = new RealNumber(892323.23);
		assertTrue(N1.compareTo(N4) < 0);
	}

	public void testToString() {
		RealNumber N1 = new RealNumber(9823.2);
		assertTrue(N1.toString().equals("9823.2"));

		 N1 = new RealNumber(-823.2);
		assertTrue(N1.toString().equals("-823.2"));
		
		 N1 = new RealNumber(-0.022);
		 assertTrue(N1.toString().equals("-0.022"));


	}

}
