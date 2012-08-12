
public class SimpleArrayList {
	
	//Constant used for when creating a SimpleArrayList with a default size
	public static final int DEFAULT_SIZE = 10;
	//Instance fields
	private int size;
	private Object[] element;
	
	//Default constructor
	public SimpleArrayList(){
		this.size = 0;
		this.element = new Object[DEFAULT_SIZE];
	}
	//Constructor based on the array size
	public SimpleArrayList(int size){
		if(size > 0){
			this.size = 0;
			this.element = new Object[size];
		}
	}
	
	//This method creates a new instance of this class with the default constructor
	public static SimpleArrayList newInstance(){
		return new SimpleArrayList();
	}
	
	//This method creates a new instance of this class with an assigned size
	public static SimpleArrayList newInstanceWithSize(int size){
		return new SimpleArrayList(size);
	}
	
	//Returns the size (number of elements in use)
	public int size(){
		return size;
	}
	
	//Gets the object and desired position
	public Object get(int index){
		if(index >= 0 && index < size)
			return element[index];
		else
			return null;
	}
	
	//Method to check if the array is empty or not
	public boolean isEmpty(){
		if(size == 0)
			return true;
		else
			return false;
	}
	
	//Gets the first element in the array, or null if array empty
	public Object first(){
		if(size != 0)
			return element[0];
		else
			return null;
	}
	
	//Gets the last element in the array, or null if array empty
	public Object last(){
		if(size != 0)
			return element[size - 1];
		else
			return null;
		
	}
	
	//Sets the the desired object in the desired position of the array
	public void set(int index, Object obj){
		if(index >= 0 && index < size)
			element[index] = obj;
	}
	
	//Adds an object to the array, allocated memory as needed
	public void add(Object obj){
		if(size == element.length)
			realloc();
			
			element[size] = obj;
			size++;
		
	}
	
	//Adds an object to the array to desired index pushing all other objects forward
	public void add(int index, Object obj){
		if(size == element.length)
			realloc();
		
		for(int i = size-1; i >= index; i--){
			element[i+1] = element[i];
		}
		
		element[index] = obj;
		size++;
	}
	
	//Removes an object at the desired index then reorganizes the array to keep it continuous
	public Object remove(int index){
		if(index >= 0 && index < size){
			Object temp = element[index];
			for(int i = index; i < size; i++){
				element[i] = element[i+1];
			}
			size--;
			return temp;
		}
		
		return null;
	}

	//Push a new object into the first position of the array moving all the others
	public void push(Object obj){
		if(size != element.length){
			for(int i = size-1; i < 0; i--){
				element[i+1] = element[i];
			}
			element[0] = obj;
			size++;
		}
	}
	
	//Reallocate memory method, this is used when the arrays needs more space
	private void realloc(){
		Object[] temp = element;
		element = new Object[2*size];
		for (int i = 0; i < size; i++){
			element[i] = temp[i];
		}
		
		
	}
}
