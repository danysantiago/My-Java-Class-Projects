import java.io.PrintStream;


public class SimpleArrayListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleArrayList test1 = SimpleArrayList.newInstance();
		System.out.println("Is empty: " + test1.isEmpty());
		// add some values
		test1.add("Joe");
		test1.add("Tom");
		test1.add("Ron");
		System.out.println("Is empty: " + test1.isEmpty());
		System.out.println("Elements: ");
		print(test1, System.out);
		System.out.println("First element: " + test1.first());
		test1.add(0, "Jil");
		System.out.println("Elements: ");
		print(test1, System.out);
		System.out.println("Last element: " + test1.last());
		test1.set(1, "Amy");
		System.out.println("Elements: ");
		print(test1, System.out);
		test1.add(test1.size() -1 , "Meg");
		System.out.println("Elements: ");
		print(test1, System.out);
		test1.add(test1.size() , "Yu");
		System.out.println("Elements: ");
		print(test1, System.out);
	}

	private static void print(SimpleArrayList L, PrintStream out) {
		for (int i=0; i < L.size(); ++i){
			out.println(L.get(i));
		}
		
	}

}
