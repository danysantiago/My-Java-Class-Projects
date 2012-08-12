import junit.framework.TestCase;


public class TestMandelbrot extends TestCase {

	public void testDiverges() {
		ComplexNumber N = new ComplexNumber(new RealNumber(3), new RealNumber(4));
		System.out.println(N.norm());
		assertTrue(Mandelbrot.diverges(N));
	}

	public void testDivergenceIndex() {
		ComplexNumber N = new ComplexNumber(new RealNumber(-0.4), new RealNumber(-0.5));
		System.out.println(Mandelbrot.divergenceIndex(N));
		assertTrue(Mandelbrot.divergenceIndex(N) == -1);

		N = new ComplexNumber(new RealNumber(-0.4), new RealNumber(-0.6));
		System.out.println(Mandelbrot.divergenceIndex(N));
		assertTrue(Mandelbrot.divergenceIndex(N) == 25);
		

	}

}
