
public interface CustomSet<E> {
	//public E[] elements;

	CustomSet<E> union(CustomSet<E> a, CustomSet<E> b);
	
	CustomSet<E> intersection(CustomSet<E> a, CustomSet<E> b);
	
	CustomSet<E> subtraction(CustomSet<E> a, CustomSet<E> b);
	
	void add(E a);
	
	E get(int x);
	
	void remove(E a);
	
	void removeAll();
	
	int size();
	
	int contains(E a);
	
	String toString();
}
