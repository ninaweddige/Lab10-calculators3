
public class CalcEngineSet<T> extends CalcEngine{
	
	private CustomSetClass<String> c;
	
	//Constructor
	CalcEngineSet(){
		c = new CustomSetClass<>();
	}

	public int size(CustomSetClass<String> set) {
		return set.size();
	}
	
	public CustomSet<String> subtract(CustomSetClass<String> a, CustomSetClass<String> b){
		return c.subtraction(a, b);
	}
	
	public CustomSet<String> union(CustomSetClass<String> a, CustomSetClass<String> b){
		return c.union(a, b);
	}
	
	public CustomSet<String> intersection(CustomSetClass<String> a, CustomSetClass<String> b){
		return c.intersection(a, b);
	}
	
}
