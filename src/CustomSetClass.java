import java.lang.reflect.Array;
import java.util.*;

public class CustomSetClass<T> implements CustomSet<T>{
	private int index;
	private T[] elements;
	private int capacity = 100;
	
	public CustomSetClass(){
		elements = (T[]) new Object[capacity];
		index = 0;	
	}

	@Override
	public CustomSet<T> union(CustomSet<T> a, CustomSet<T> b) {
		CustomSetClass<T> returnSet = new CustomSetClass<T>();
		for(int i = 0; i < a.size(); i++) {
				returnSet.add(a.get(i));
		}
		for(int i = 0; i < b.size(); i++) {
			returnSet.add(b.get(i));
		}
		return returnSet;
	}

	@Override
	public CustomSet<T> intersection(CustomSet<T> a, CustomSet<T> b) {
		return subtraction(a, subtraction(a, b));
	}

	@Override
	public CustomSet<T> subtraction(CustomSet<T> a, CustomSet<T> b) {
		CustomSetClass<T> returnSet = new CustomSetClass<T>();
		for(int i = 0; i < a.size(); i++) {
			if(b.contains(a.get(i)) == 0) {
				returnSet.add(a.get(i));
			} 
		}
		return returnSet;
	}

	@Override
	public void add(T a) {
		if(contains(a) == 0) {
			elements[index] = a;
			index++;
		}		
	}

	/*
	 * @Override public void remove(T a) { int x = contains(a); if(x != 0) {
	 * elements[x-1] = null; update(); } }
	 */
	
	@Override
	public void remove(T a) {
		int x = contains(a);
		if(x != 0) {
			update(x - 1);
			index--;
		}
	}
	
	@Override
	public void removeAll() {
		for(int i = 0; i < index; i++) {
			remove(get(i));
		}
		index = 0;
	}

	@Override
	public int size() {
		return index;
	}

	@Override
	public int contains(Object a) {
		for(int i = 0; i < index; i++) {
			if(elements[i].equals(a)) {
				return i + 1;
			}
		}
		return 0;
	}
	
	/*
	 * public void update() { for(int i = 0; i < index; i++) {
	 * if(elements[i].equals(null)) { elements[i] = elements[i + 1]; for(int j = i +
	 * 1; j < index; j++) { if(elements[j + 1].equals(null)) { elements[j] = null;
	 * index --; return; } elements[j] = elements[j + 1]; } } } }
	 */
	public void update(int startIndex) {
		for(int i = startIndex; i < index - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[index - 1] = null;
	}

	@Override
	public T get(int x) {
		return elements[x];
	}
	
	@Override
	public String toString() {
		String r = "";
		for(int i = 0; i < index; i++) {
			r += elements[i] + ",";
		}
		String result = "{";
		if(r.length() > 0) {
			result += r.substring(0, r.length() - 1); // to remove last commas
		}
		result += "}";
		return result;
	}

}
