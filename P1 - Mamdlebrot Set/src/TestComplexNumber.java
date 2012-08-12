import junit.framework.TestCase;


public class TestComplexNumber extends TestCase {

	public void testComplexNumber() {
		ComplexNumber C = new ComplexNumber(new RealNumber(2), new RealNumber(-4));
		assertTrue(C.getRealPart().equals(new RealNumber(2)) && C.getImagnaryPart().equals(new RealNumber(-4)));
		
	}

	public void testGetRealPart() {
		ComplexNumber C = new ComplexNumber(new RealNumber(2), new RealNumber(-4));
		assertTrue(C.getRealPart().equals(new RealNumber(2)));
	}

	public void testGetImagnaryPart() {
		ComplexNumber C = new ComplexNumber(new RealNumber(2), new RealNumber(-4));
		assertTrue(C.getImagnaryPart().equals(new RealNumber(-4)));

	}

	public void testAdd() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(-2), new RealNumber(7));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(9), new RealNumber(-4));
		//System.out.println(C1.add(C2));
		assertTrue(C1.add(C2).equals(new ComplexNumber(new RealNumber(7), new RealNumber(3))));

		C1 = new ComplexNumber(new RealNumber(2), new RealNumber(-5));
		C2 = new ComplexNumber(new RealNumber(-3), new RealNumber(-4));
		//System.out.println(C1.add(C2));
		assertTrue(C1.add(C2).equals(new ComplexNumber(new RealNumber(-1), new RealNumber(-9))));

	}

	public void testSubstract() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(-2), new RealNumber(7));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(9), new RealNumber(-4));
		//System.out.println(C1.substract(C2));
		assertTrue(C1.subtract(C2).equals(new ComplexNumber(new RealNumber(-11), new RealNumber(11))));

		C1 = new ComplexNumber(new RealNumber(-2), new RealNumber(-4));
		C2 = new ComplexNumber(new RealNumber(2), new RealNumber(4));
		//System.out.println(C1.add(C2));
		assertTrue(C1.subtract(C2).equals(new ComplexNumber(new RealNumber(-4), new RealNumber(-8))));
		
	}

	public void testMultiply() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(-2), new RealNumber(5));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(9), new RealNumber(-4));
		//System.out.println(C1.multiply(C2));
		assertTrue(C1.multiply(C2).equals(new ComplexNumber(new RealNumber(2), new RealNumber(53))));

		C1 = new ComplexNumber(new RealNumber(4), new RealNumber(2));
		C2 = new ComplexNumber(new RealNumber(3), new RealNumber(2));
		//System.out.println(C1.add(C2));
		assertTrue(C1.multiply(C2).equals(new ComplexNumber(new RealNumber(8), new RealNumber(14))));
	
	}

	public void testDivide() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(16), new RealNumber(-2));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(3), new RealNumber(-2));
		System.out.println(C1.divide(C2));
		assertTrue(C1.divide(C2).equals(new ComplexNumber(new RealNumber(4), new RealNumber(2))));

		C1 = new ComplexNumber(new RealNumber(-8), new RealNumber(27));
		C2 = new ComplexNumber(new RealNumber(2), new RealNumber(3));
		System.out.println(C1.add(C2));
		assertTrue(C1.divide(C2).equals(new ComplexNumber(new RealNumber(5), new RealNumber(6))));

	}

	public void testNorm() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(3), new RealNumber(4));
		assertTrue(C1.norm().equals(new RealNumber(5)));
		
		ComplexNumber C2 = new ComplexNumber(new RealNumber(5), new RealNumber(-12));
		assertTrue(C2.norm().equals(new RealNumber(13)));
	}

	public void testEqualsComplexNumber() {
		ComplexNumber C1 = new ComplexNumber(new RealNumber(-2), new RealNumber(7));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(-2.0), new RealNumber(7.0));
		assertTrue(C1.equals(C2));
	
	}

	public void testToString() {
		ComplexNumber C = new ComplexNumber(new RealNumber(2), new RealNumber(-4));
		//System.out.println(C.toString());
		assertTrue(C.toString().equals("2.0 + -4.0i"));

	}

}
