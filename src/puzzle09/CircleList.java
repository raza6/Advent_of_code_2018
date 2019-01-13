package puzzle09;

public class CircleList<T> {
	public Maillon<T> current;
	
	public CircleList(Maillon<T> current) {
		this.current = current;
	}

	public void ajouterMaillon(Maillon<T> m){
		current.succ.pred = m;
		m.succ = current.succ;
		current.succ = m;
		m.pred = current;
		
		current = current.succ;//On se place sur l'�l�ment ins�r�
	}
	
	public T retirerMaillon() {
		T res = current.value;
		current.pred.succ = current.succ;
		current.succ.pred = current.pred;
		
		current = current.succ;//On se place sur l'�l�ment suivant celui venant d'�tre supprim�
		return res;
	}
}
