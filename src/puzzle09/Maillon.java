package puzzle09;

public class Maillon<T> {
	public T value;
	public Maillon<T> pred;
	public Maillon<T> succ;
	
	public Maillon(T value) {
		this.value = value;
	}
	
}
