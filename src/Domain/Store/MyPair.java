package Domain.Store;

public class MyPair<T1, T2> {

	T1 key;
	T2 value;
	
	public MyPair(T1 key, T2 value) {
		this.key = key;
		this.value = value;
		
	}

	public T2 getValue() {
		return value;
	}

	public T1 getKey() {
		return key;
	}

}
