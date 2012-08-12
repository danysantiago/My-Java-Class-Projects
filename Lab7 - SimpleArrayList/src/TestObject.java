
public class TestObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create an Integer with value 20
		Object number1 = new Integer(20); 
		// cast number1 to Integer and assign it
		Integer number2 = (Integer) number1; 
		Object string1 = "Pete";
		String string2 = new String((String) string1);
		// create an array of objects
		Object[] elements = new Object[3];
		elements[0] = new Integer(10);
		elements[1] = new Integer(3);
		elements[2] = new Integer(90);


	}

}
